package generator;

// Remaining Java internal library classes
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

// Apache Commons CSV external library classes
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

// Google Guava external library classes
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

// Generator class performs the dataset generation which has been adapted from the client's code (available on the Additional materials section on Moodle)
public class Generator {

   // input: Data table for the input configurations
   private Table<Integer, Integer, String> input;
   // output: Data table for the output configurations
   private Table<Integer, Integer, String> output;
   // labOutputs: HashMap for the lab configurations
   private LinkedHashMap<String, Table<Integer, Integer, String>> labOutputs;
   // state: Data table for the state configurations
   private Table<Integer, Integer, String> state;
   // data: Data table for the final dataset
   private Table<Integer, Integer, String> data;
   // dyn: Data table for the temporary dynamic inputs
   private Table<Integer, Integer, String> dyn;
   // startDate: Date at which the final dataset starts at
   String startDate;
   // processPeriod: Process period time
   int processPeriod;
   // labPeriod: Lab period time
   int labPeriod;
   // labOffset;
   int labOffset;
   // pulpeyePeriod: PulpEye period time
   int pulpeyePeriod;
   // qcsPeriod: QCS period time
   int qcsPeriod;
   // lastRow: The last row in the dataset
   int finalRow;
   // numInputs: The number of input variables
   int numInputs;
   // numOutputs: The number of output variables
   int numOutputs;
   // numState: The number of state variables
   int numState;
   // inputSettle: The inputSettle value
   double inputSettle;
   // coupledMoves: The number of coupled (input validation) moves
   int coupledMoves;
   // uncoupledMoves: The number of uncoupled moves
   int uncoupledMoves;
   // isolatedMoves: The number of isolated moves
   int isolatedMoves;
   // trim: The trim value
   double trim;
   // draw: The draw value
   double draw;
   // lastInputCol: The last column for input/state variables
   int lastInputCol;
   // firstVal: The column number of the first input variable used in calculations
   int firstVal;
   // dynRow: The final row before dynamics are applied
   int dynRow;

   // This constructor initialises all variables and applies some calculation from
   // the client's Excel tool (available on OneDrive) and some of the client's code
   // (available on the Additional materials section on Moodle)
   public Generator(Table<Integer, Integer, String> input, Table<Integer, Integer, String> output,
         LinkedHashMap<String, Table<Integer, Integer, String>> labOutputs,
         Table<Integer, Integer, String> state, HashMap<String, Double> process, String startDate) {
      this.input = input;
      this.output = output;
      this.labOutputs = labOutputs;
      this.state = state;
      this.startDate = startDate;
      data = TreeBasedTable.create();
      firstVal = 2;
      /*
       * Below code assigns variables that refer to some rows (2, 4 - 8, 17 - 22) of
       * the 'Process_Config' sheet from the client's Excel tool (available on
       * OneDrive)
       * Instead of being retrieved from an Excel row, the values are retrieved from
       * the parameters through the constructor
       */
      processPeriod = process.get("Process").intValue();
      qcsPeriod = process.get("QCS").intValue();
      labPeriod = process.get("Lab").intValue();
      labOffset = process.get("LabOffset").intValue();
      pulpeyePeriod = process.get("Pulpeye").intValue();
      uncoupledMoves = process.get("Uncoupled").intValue();
      trim = StateCalculator.TRIM_AMOUNT;
	  draw = StateCalculator.DRAW_FACTOR;
      coupledMoves = process.get("Coupled").intValue();
      numInputs = input.columnKeySet().size() - 1;
      numOutputs = labOutputs.keySet().size();
      numState = state.columnKeySet().size() - 1;
      // End of code reference
      // This variable is used from 'DynamicInputs.bas' from the client's code
      // (available on the Additional materials section on Moodle)
      lastInputCol = numInputs + numState + 1;
      // End of code reference
      /*
       * Below code assigns variables that refer to some rows (9 - 11, 14, 16) of the
       * 'Process_Config' sheet from the client's Excel tool (available on OneDrive)
       * The Excel formulas have been adapted into calculations that work in Java
       */
      // Following three lines use a method (shown after the constructor) created by
      // myself to perform the calculations
      int deadtime = max(3);
      int lag1 = max(4);
      int lag2 = max(5);
      int maxSettle = process.get("Settle").intValue() + deadtime + lag1 + lag2;
      inputSettle = Math.max(maxSettle, labPeriod);
      // End of code reference
      /*
       * Below code prepares the final dataset which is adapted from parts of
       * 'CreateSheets.bas' from the client's code (available on the Additional
       * materials section on Moodle)
       * Some lines are adapted from VB to Java, important justifications are
       * mentioned below
       */
      data.put(1, 1, "TIME");
      // Empty string line has been added since the dataset needs to write an empty
      // value to the CSV file
      data.put(2, 1, "");
      for (int i = 2; i <= numInputs + 1; i++) {
         data.put(1, i, input.get(1, i));
         data.put(2, i, input.get(2, i));
      }
      // lastCol variable has been added and adjusted by myself to track the columns
      // in the final dataset
      int lastCol;
      for (int i = 2; i <= numState + 1; i++) {
         lastCol = i + numInputs;
         data.put(1, lastCol, state.get(1, i));
         data.put(2, lastCol, state.get(2, i));
      }
      for (int i = 2; i <= numOutputs + 1; i++) {
         lastCol = i + numInputs + numState;
         data.put(1, lastCol, output.get(1, i));
         data.put(2, lastCol, output.get(2, i));
      }
      // End of code reference
      // Preparing the temporary dynamics table
      dyn = TreeBasedTable.create();
      for (int i = 2; i <= lastInputCol + 1; i++) {
         dyn.put(1, i, data.get(1, i));
      }
      // Below line of code is translated from line 15 of 'DynamicInputs.bas' from the
      // client's code (available on the Additional materials section on Moodle)
      dynRow = Math.round(maxSettle / processPeriod) + 3;
   }

   // The following set of methods are helper methods created by myself with any
   // client code references mentioned

   /*
    * max: Method that calculates the maximum value from all input variables from a
    * given column
    * The formula is retrieved from this Excel calculation:
    * 'MAX(In_Config!B[row]]:AZ[row])*60' in the Process_Config' sheet from the
    * client's Excel tool (available on OneDrive)
    */
   private int max(int col) {
      int max = 0;
      for (int i = 2; i < numInputs + 2; i++) {
         max = (int) Math.max(max, Double.parseDouble(input.get(col, i)) * 60);
      }
      return max;
   }

   /*
    * calcNoise: Method that calculates a random noise value from a given value
    * The formula is retrieved from this VB calculation: '2 * Rnd * Noise - Noise'
    * in 'SubRoutines.bas' from the client's code (available on the Additional
    * materials section on Moodle)
    * A method was created for this calculation to avoid repetition
    */
   private double calcNoise(double noise) {
      return 2 * Math.random() * noise - noise;
   }

   /*
    * calcSine: Method that calculates a random noise value from a given value
    * Client provided the algorithm desired which I translated to Java, adpations
    * by myself are mentioned
    */
   private double calcSine(double period, double amplitude, int row) {
      double value = 360 * (row * (processPeriod / period));
      // Below two lines were required to ensure consistency
      double degrees = value % 360;
      double radians = Math.toRadians(degrees);
      return Math.sin(radians) * amplitude;
   }

   /*
    * searchCol: Method returns the column number of a given name from a given
    * table staring from a given start number
    * This was created by myself as the 'Range' function in VB was not present in
    * Java
    */
   private int searchCol(String name, Table<Integer, Integer, String> t) {
      for (int i = 2; i < lastInputCol + 3; i++) {
         String var = t.get(1, i);
         if (var.equals(name))
            return i;
      }
      return 0;
   }

   /*
    * calcList: Method that adds variables to a given list of a given column size
    * if the variables are present in lab configurations
    * This was created by myself as a performance improvement to only use the
    * variables that are required
    */
   private void calcList(List<Integer> list, int size, Table<Integer, Integer, String> table) {
      for (String i : labOutputs.keySet()) {
         for (int j = 2; j <= labOutputs.get(i).rowKeySet().size(); j++) {
            for (int c = 2; c <= size + 1; c++) {
               if (labOutputs.get(i).get(j, 1).equals(table.get(1, c))) {
                  if (!list.contains(c))
                     list.add(c);
               }
            }
         }
      }
   }

   // End of helper methods

   /*
    * write: Method for writing to a CSV file given the file name and whether the
    * table should be final
    * This was not required in VB as tables in Excel were directly written to
    */
   private void write(boolean table, String name) {
      Table<Integer, Integer, String> t;
      if (!table) {
         t = TreeBasedTable.create();
         for (int i = 1; i <= finalRow; i++) {
            // When writing to the temporary dataset, not all columns are written to save
            // memory
            for (int j = 1; j < firstVal; j++) {
               t.put(i, j, data.get(i, j));
            }
         }
      } else
         t = data;
      try {
         // Ensure the /data folder exists before writing
         File dataFolder = new File("data");
         if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // create the folder
         }
         BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + name + ".csv"));
         /*
          * Below code was adapted from the question in this website:
          * https://stackoverflow.com/questions/38524942/guava-table-to-csv
          * The table variable was changed
          */
         CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
         printer.printRecords(t.rowMap().values().stream().map(x -> x.values()).collect(Collectors.toList()));
         // End of code reference
         printer.flush();
         writer.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /*
    * read: Method for reading the temporary CSV file into a data table given the
    * column limit
    * This was not required in VB as tables in Excel were directly written to
    */
   private void read(int last) {
      try {
         /*
          * Below code was adapted from the first answer in this website:
          * https://stackoverflow.com/questions/42170837/how-to-read-a-csv-file-into-an-
          * array-list-in-java
          * Adapted lines are explained below
          */
         // A File object was used instead of a String from the answer, the path was also
         // changed
         File file = new File("data/data.csv");
         // The same list variable was used, but renamed by myself
         List<String[]> csv = new ArrayList<>();
         // The BufferedReader line was re-arranged
         BufferedReader br = new BufferedReader(new FileReader(file));
         // The line String didn't need to be initialised, so I removed the assignment
         String line;
         // The next three lines were re-used and the variable name for the list was
         // adapted
         while ((line = br.readLine()) != null) {
            csv.add(line.split(","));
         }
         // End of code reference
         br.close();
         int r = 0;
         int c = 0;
         for (String[] row : csv) {
            r++;
            for (String cell : row) {
               c++;
               if (c > last)
                  break;
               // Without the toString() call, the value would not be correct
               data.put(r, c, cell.toString());

            }
            c = 0;
         }
         // File is no longer necessary so it can be deleted
         file.delete();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /*
    * createInputs: Method that is translated and adapted from 'CreateInputs.bas'
    * and 'SubRoutines.bas' from the client's code (available on the Additional
    * materials section on Moodle)
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   public void createInputs() {
      double rowsPerMove = inputSettle / processPeriod;
      int rowsPerProcess = 1;
      int firstRow = 3;
      int lastInCol = numInputs + 2;
      // SteadyState routine from 'SubRoutines.bas'
      int row = 0;
      int lastRow = 0;
      for (int i = 2; i < lastInCol; i++) {
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         double avg = min + (max - min) / 2;
         double noise = Double.parseDouble(input.get(6, i));
         // The below two lines were added by myself as part of the DG-6 requirement
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));
         for (int j = 1; j <= (rowsPerMove / rowsPerProcess); j++) {
            double noiseVal = calcNoise(noise);
            row = firstRow - 1 + rowsPerProcess * j;
            // The below line was added by myself as part of the DG-6 requirement
            double sineVal = calcSine(sinePeriod, amplitude, row);
            // The below line was adjusted to include the sine value
            data.put(row, i, String.valueOf((avg + noiseVal + sineVal)));
         }
         lastRow = row;
      }
      int lastSteadyStateRow = lastRow;
      // End of SteadyState routine
      // MV_UCMove routine from 'SubRoutines.bas'
      for (int i = 2; i < lastInCol; i++) {
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         int order = Integer.parseInt(input.get(12, i));
         // The below two lines were added by myself as part of the DG-6 requirement
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));
         double stepSize;
         /*
          * The stepSize formula could not exist by itself unlike in the VB code
          * A condition was created by myself to check if uncoupledMoves was 0, so an
          * infinite value would not be produced
          */
         if (uncoupledMoves != 0)
            stepSize = (max - min) / uncoupledMoves;
         else
            stepSize = max - min;
         double mvLag = Double.parseDouble(input.get(7, i));

         double filter;
         double mvFilter;
         if (mvLag <= 0)
            filter = 1;
         else
            filter = 0.63 / (mvLag / (rowsPerProcess * processPeriod));
         if (filter > 1)
            mvFilter = 1;
         else
            mvFilter = filter;

         lastRow = lastSteadyStateRow;
         double noise = Double.parseDouble(input.get(6, i));
         double lastMove = min;
         for (int j = 0; j <= uncoupledMoves; j++) {
            double move = min + stepSize * j;
            for (int id = 1; id <= numInputs; id++) {
               double next;
               if (order == id) {
                  next = move;
                  lastMove = move;
               } else
                  next = lastMove;
               for (int x = 1; x <= (rowsPerMove / rowsPerProcess); x++) {
                  row = lastRow + rowsPerProcess * x;
                  double noiseVal = calcNoise(noise);
                  // The below line was added by myself as part of the DG-6 requirement
                  double sineVal = calcSine(sinePeriod, amplitude, row);
                  double priorVal = Double.parseDouble(data.get(row - 1, i));
                  double newVal = priorVal * (1 - mvFilter) + next * mvFilter;
                  if (newVal < min)
                     newVal = min;
                  else if (newVal > max)
                     newVal = max;
                  // The below line was adjusted to include the sine value
                  data.put(row, i, String.valueOf((newVal + noiseVal + sineVal)));
               }
               lastRow = row;
            }
            lastMove = move;
         }
      }
      int lastInRow = lastRow;
      // End of MV_UCMove routine
      // MV_IsolatedMove routine for isolated moves
      if (isolatedMoves > 0) {
         for (int inputIdx = 2; inputIdx < lastInCol; inputIdx++) {
            double min = Double.parseDouble(input.get(9, inputIdx));
            double max = Double.parseDouble(input.get(8, inputIdx));
            double noise = Double.parseDouble(input.get(6, inputIdx));
            double sinePeriod = Double.parseDouble(input.get(10, inputIdx));
            double amplitude = Double.parseDouble(input.get(11, inputIdx));
            double mvLag = Double.parseDouble(input.get(7, inputIdx));

            double stepSize;
            if (isolatedMoves != 0)
               stepSize = (max - min) / isolatedMoves;
            else
               stepSize = max - min;

            double filter;
            double mvFilter;
            if (mvLag <= 0)
               filter = 1;
            else
               filter = 0.63 / (mvLag / (rowsPerProcess * processPeriod));
            if (filter > 1)
               mvFilter = 1;
            else
               mvFilter = filter;

            // Move this input from min to max
            for (int j = 0; j <= isolatedMoves; j++) {
               double move = min + stepSize * j;
               for (int x = 1; x <= (rowsPerMove / rowsPerProcess); x++) {
                  row = lastInRow + rowsPerProcess * x;
                  double noiseVal = calcNoise(noise);
                  double sineVal = calcSine(sinePeriod, amplitude, row);
                  double priorVal = Double.parseDouble(data.get(row - 1, inputIdx));
                  double newVal = priorVal * (1 - mvFilter) + move * mvFilter;
                  if (newVal < min)
                     newVal = min;
                  else if (newVal > max)
                     newVal = max;
                  data.put(row, inputIdx, String.valueOf((newVal + noiseVal + sineVal)));
               }
               lastInRow = row;
            }

            // Settle period: keep all inputs at their current values
            for (int i = 2; i < lastInCol; i++) {
               double currentNoise = Double.parseDouble(input.get(6, i));
               double currentSinePeriod = Double.parseDouble(input.get(10, i));
               double currentAmplitude = Double.parseDouble(input.get(11, i));
               double settleValue = Double.parseDouble(data.get(lastInRow, i));

               for (int x = 1; x <= (rowsPerMove / rowsPerProcess); x++) {
                  row = lastInRow + rowsPerProcess * x;
                  double noiseVal = calcNoise(currentNoise);
                  double sineVal = calcSine(currentSinePeriod, currentAmplitude, row);
                  data.put(row, i, String.valueOf((settleValue + noiseVal + sineVal)));
               }
            }
            lastInRow = row;
         }
      }
      // End of MV_IsolatedMove routine
      // Validation moves
      int firstValidationRow = 13;
      int lastValidationRow = 13 + coupledMoves;
      int dataRow = 0;
      for (int i = firstValidationRow; i <= lastValidationRow; i++) {
         for (int j = 2; j < lastInCol; j++) {
            double validationValue;
            double noise = Double.parseDouble(input.get(6, j));
            // The below two lines were added by myself as part of the DG-6 requirement
            double sinePeriod = Double.parseDouble(input.get(10, j));
            double amplitude = Double.parseDouble(input.get(11, j));
            if (input.get(i, j) == null || input.get(i, j).equals(""))
               validationValue = Double.parseDouble(data.get(lastInRow, j));
            else
               validationValue = Double.parseDouble(input.get(i, j));
            for (dataRow = lastInRow + 1; dataRow <= (lastInRow + rowsPerMove); dataRow++) {
               double noiseVal = calcNoise(noise);
               // The below line was added by myself as part of the DG-6 requirement
               double sineVal = calcSine(sinePeriod, amplitude, dataRow);
               // The below line was adjusted to include the sine value
               data.put(dataRow, j, String.valueOf((validationValue + noiseVal + sineVal)));
            }
         }
         lastInRow = dataRow - 1;
      }
      // VB code ends here, the rest of the method includes code written by myself
      finalRow = lastInRow;
      // Empty values are required for the CSV to skip values accurately
      for (int i = 3; i <= finalRow; i++) {
         data.put(i, 1, "");
      }

      // List is used to note essential variables
      List<Integer> inputNames = new ArrayList<>();
      calcList(inputNames, numInputs, input);
      // Input variables that are required in QCS variable calculations are added
      inputNames.add(searchCol("MV_ThinStockFlow", input));
      inputNames.add(searchCol("MV_ThinStockConsistency", input));
      inputNames.add(searchCol("MV_PressLoad", input));
      inputNames.add(searchCol("MV_SteamPressure", input));
      /*
       * Below code was adapted from this website:
       * https://www.freecodecamp.org/news/how-to-sort-a-list-in-java/
       * The variable name was changed
       */
      Collections.sort(inputNames);
      // End of code reference
      // The first essential input variable column is recorded, so the rest don't have
      // to be written
      firstVal = inputNames.get(0);
      write(false, "data");
   }

   /*
    * stateSetup: Method for calculating a given state column given its input
    * column and state table column
    * This method condenses the first three set of loops in
    * 'CalcStateVariables.bas' from the client's code (available on the Additional
    * materials section on Moodle)
    * This was moved and adapted to a separate method by myself for efficiency
    * improvements
    */
   private void stateSetup(int col, int inCol, int stateCol) {
      // Since the calculations were specific, the values could be hard-coded
      double intercept = 1000;
      double asymptote = 300;
      double slope = 0.5;
      // This block of code contains parameter values to make the algorithm generic
      double noise = Double.parseDouble(state.get(6, col));
      for (int i = 3; i <= finalRow; i++) {
         double noiseVal = calcNoise(noise);
         double inputVal = Double.parseDouble(data.get(i, inCol));
         double val = intercept - (intercept - asymptote) * (1 - 1 / Math.exp(slope * inputVal)) + noiseVal;
         data.put(i, stateCol, String.valueOf(val));
      }
   }

   /*
    * calcState: Method that is translated and adapted from
    * 'CalcStateVariables.bas' from the client's code available (available on the
    * Additional materials section on Moodle)
    * The purpose of this method is to apply specific calculations to some state
    * variables
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   public void calcState() {
	    new StateCalculator(data, state, finalRow, lastInputCol)
	            .calcState();
	}


   /*
    * dynamicValues: Method that is translated and adapted from 'SecondOrder.bas'
    * from the client's code (available on the Additional materials section on
    * Moodle)
    * This method was more adapted than other methods due to the Excel file for
    * 'SecondOrder.bas' having a mush smaller scale
    * The purpose of this method is to calculate a dynamically moved input or state
    * value for a given row and column
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   private void dynamicValues(int row, int col, boolean isInput) {
      /*
       * Table is assigned based on if the variable is an input or state variable
       * 'SecondOrder.bas' does not deal with state variables, only input variables
       */
      Table<Integer, Integer, String> table;
      if (isInput)
         table = input;
      else
         table = state;
      /*
       * The below line was translated from line 340 in 'SubRoutines.bas' from the
       * client's code available (available on the Additional materials section on
       * Moodle)
       * I discovered the line from 'SecondOrder.bas' would not work as the deadtime
       * was required to be a whole number
       */
      int deadTime = (int) (Double.parseDouble(table.get(3, col)) * 60 / processPeriod);
      double lag1 = Double.parseDouble(table.get(4, col));
      double lag2 = Double.parseDouble(table.get(5, col));
      // State variable columns need to be adjusted since the final dataset has input
      // variables first
      if (!isInput)
         col = col + numInputs;
      /*
       * Below lines were translated from 'SubRoutines.bas' from the client's code
       * (available on the Additional materials section on Moodle)
       * The old method of applying dynamics called the 'Get_InputDynamics'
       * subroutine, which included this code
       * Without this block of code (like in 'SecondOrder.bas'), I discovered the
       * values would be infinite
       */
      double filterVal;
      if (lag1 <= 0)
         filterVal = 1;
      else
         filterVal = 0.63 / (lag1 * 60 / processPeriod);
      if (filterVal > 1)
         lag1 = 1;
      else
         lag1 = filterVal;
      if (lag2 <= 0)
         filterVal = 1;
      else
         filterVal = 0.63 / (lag2 * 60 / processPeriod);
      if (filterVal > 1)
         lag2 = 1;
      else
         lag2 = filterVal;
      // End of 'Get_InputDynamics' reference
      double inputDeadtime = Double.parseDouble(data.get(row - deadTime, col));
      double inLag1;
      double inLag2;
      // The dynRow + 1 row does not have historic data in the dynamics table
      if (row == dynRow + 1) {
         inLag1 = Double.parseDouble(data.get(row - deadTime - 1, col));
         inLag2 = Double.parseDouble(data.get(row - deadTime - 2, col));
      } else {
         inLag1 = Double.parseDouble(dyn.get(3, col));
         inLag2 = Double.parseDouble(dyn.get(4, col));
      }
      double result;
      if (lag2 <= 0)
         result = inputDeadtime * lag1 + inLag1 * (1 - lag1);
      else {
         result = secondOrder(inputDeadtime, inLag1, inLag2, lag1, lag2);
      }
      dyn.put(2, col, String.valueOf(inputDeadtime));
      dyn.put(3, col, String.valueOf(result));
      dyn.put(4, col, String.valueOf(inLag1));
   }

   /*
    * secondOrder: Method that is translated from the 'SecondOrder' routine in
    * 'SubRoutines.bas' from the client's code (available on the Additional
    * materials section on Moodle)
    * The purpose of this method is to calculate a result given lag and data values
    */
   private double secondOrder(double newOut, double out1, double out2, double lag1, double lag2) {
      double firstPrior = (out1 - out2 * (1 - lag2)) / lag2;
      double firstCurrent = newOut * lag1 + firstPrior * (1 - lag1);
      return firstCurrent * lag2 + out1 * (1 - lag2);
   }

   /*
    * calcStateDyn: Method that is translated and adapted from 'calcStateDyn.bas'
    * from the client's code (available on the Additional materials section on
    * Moodle)
    * The purpose of this method is to calculate the QCS variable values
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   public void calcStateDyn() {

	    calcStateDyn qcs = new calcStateDyn(
	            data,
	            state,
	            input,
	            dyn,
	            finalRow,
	            lastInputCol,
	            firstVal,
	            dynRow,
	            this::dynamicValues
	    );

	    qcs.calcStateDyn();
	}


   /*
    * calcLab: Method that is translated and adapted from 'CalcLab.bas' from the
    * client's code (available on the Additional materials section on Moodle)
    * The purpose of this method is to calculate the output variables from the lab
    * configurations
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   public void calcLab() {
      /*
       * The VB code could store dynamic inputs in a separate spreadsheet, however
       * that would not be possible in Java due to memory issues
       * I adapted this code so that any variables that require dynamics have their
       * column numbers stored below and the list can be looped through to call the
       * dynamicValues() method
       * Inputs and state variables require dynamic values
       */
      List<Integer> inputNames = new ArrayList<>();
      calcList(inputNames, numInputs, input);

      List<Integer> stateNames = new ArrayList<>();
      calcList(stateNames, numState, state);

      int lastLab = lastInputCol + numOutputs;
      int firstLab = lastInputCol + 1;
      int stateRow = numInputs + 2;
      /*
       * The client made a change where the lab rows can be created only if needed
       * instead of removing everything at createDataset()
       * In VB, the loop below could be stepped to go through every lab period row
       * However, since I am using temporary dynamic inputs, the loop must go through
       * every row so the dynamics could be calculated cumulatively
       * Instead, line () uses the modulus operator to only do the value calculation
       * for every lab row
       */
      
      for (int i = firstLab; i < lastLab + 1; i++) {
         String name = data.get(1, i);
         
         // Randomize LAB period for each lab
         int randomOffset = (int) ((Math.random() * 2 - 1) * labOffset);
         int numRows = (labPeriod + randomOffset) / processPeriod;
         
         for (int j = 3; j <= finalRow; j++) {
            // Only a specific set of initial rows don't require dynamics
            if (j > dynRow) {
               // I added this code to loop through the lists and apply dynamics
               for (int input : inputNames) {
                  dynamicValues(j, input, true);
               }
               for (int state : stateNames) {
                  dynamicValues(j, state, false);
               }
            }
            // In Java, I had to use the modulus operator so the row numbers would be
            // correct
            if ((j - 3) % numRows == 0)
               data.put(j, i, String.valueOf(gainModel(name, stateRow, j)));
         }
      }
   }

   /*
    * gainModel: Method that is translated and adapted from the 'GainModelRows'
    * routine in 'SubRoutines.bas' from the client's code (available on the
    * Additional materials section on Moodle)
    * The purpose of this method is to retrieve the lab configurations and
    * calculate the final value
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   private double gainModel(String name, int sRow, int row) {
      double weightedInput = 0;
      for (int i : labOutputs.get(name).rowKeySet()) {
         // First row in a labOutputs table is ignored since it does not contain a
         // variable
         if (i == 1)
            continue;
         String varName = labOutputs.get(name).get(i, 1);
         double weight = Double.parseDouble(labOutputs.get(name).get(i, 2));
         String asymptote = labOutputs.get(name).get(i, 3);
         String order = labOutputs.get(name).get(i, 4);
         String slope = labOutputs.get(name).get(i, 5);
         double model = Double.parseDouble(labOutputs.get(name).get(i, 6));
         double direction = Double.parseDouble(labOutputs.get(name).get(i, 7));
         double shape = Double.parseDouble(labOutputs.get(name).get(i, 8));

         int col;
         double max;
         double min;
         /*
          * In VB, the conditional statement below checks the state row against the loop
          * index
          * Since the variable rows in Java don't relate to the column rows in the lab
          * output tables, the columns are searched instead
          */
         if (searchCol(varName, data) < sRow) {
            col = searchCol(varName, input);
            max = Double.parseDouble(input.get(8, col));
            min = Double.parseDouble(input.get(9, col));
         } else {
            col = searchCol(varName, state);
            max = Double.parseDouble(state.get(7, col));
            min = Double.parseDouble(state.get(8, col));
         }
         double inVal;
         // With temporary dynamic inputs, the table from which the value is retrieved
         // depends on if dynamics are required
         if (row > dynRow)
            inVal = Double.parseDouble(dyn.get(3, searchCol(varName, dyn)));
         else
            inVal = Double.parseDouble(data.get(row, searchCol(varName, data)));

         weightedInput = gainFunction(inVal, max, min, asymptote, order, slope, model, direction, shape) * weight / 100
               + weightedInput;

      }
      int labCol = searchCol(name, output);
      double labMax = Double.parseDouble(output.get(4, labCol));
      // The below line was added by myself as part of the DG-7 requirement
      double labNoise = calcNoise(Double.parseDouble(output.get(3, labCol)));
      double labMin = Double.parseDouble(output.get(5, labCol));
      // The below line was adjusted to include the noise value
      return (labMin + (labMax - labMin) * weightedInput) + labNoise;
   }

   /*
    * gainFunction: Method that is translated from the 'GainModelRows' routine in
    * 'SubRoutines.bas' from the client's code available on OneDrive
    * The purpose of this method is to calculate graph values from the lab
    * configurations
    */
   private double gainFunction(double inVal, double max, double min, String asymptote, String order, String slope,
         double model, double direction, double shape) {
      if (inVal > max)
         inVal = max;
      else if (inVal < min)
         inVal = min;
      double range = max - min;
      double gainInput = (inVal - min) / range;

      double gainAsymptote;
      if (asymptote.equals(""))
         gainAsymptote = 0.5;
      else if (Double.parseDouble(asymptote) > max)
         gainAsymptote = 1;
      else if (Double.parseDouble(asymptote) < min)
         gainAsymptote = 0;
      else
         gainAsymptote = (Double.parseDouble(asymptote) - min) / range;
      // Polynomial
      if (model == 0) {
         double g1;
         double g2;
         double g0;
         if (Double.parseDouble(order) == 2) {
            if (shape == 0) {
               if (direction == 0) {
                  g2 = 1;
                  g1 = -2;
                  g0 = 1;
               } else {
                  g2 = -1;
                  g1 = 2;
                  g0 = 0;
               }
            } else if (shape == 1) {
               if (direction == 0) {
                  g2 = -0.5;
                  g1 = -0.5;
                  g0 = 1;
               } else {
                  g2 = 0.5;
                  g1 = 0.5;
                  g0 = 0;
               }
            } else {
               g2 = 2 * (0.5 - direction) / Math.pow(0.5 + Math.sqrt(Math.pow(0.5 - gainAsymptote, 2)), 2);
               g1 = -2 * g2 * gainAsymptote;
               g0 = g2 * Math.pow(gainAsymptote, 2) + direction;
            }
         } else {
            g2 = 0;
            if (direction == 0) {
               g1 = -1;
               g0 = 1;
            } else {
               g1 = 1;
               g0 = 0;
            }
         }
         return g2 * Math.pow(gainInput, 2) + g1 * gainInput + g0;
      }
      // Exponential
      else if (model == 1) {
         double slopeSign;
         double gainDirection;
         if (Double.parseDouble(order) == 1) {
            gainAsymptote = 0;
            if (shape == 0) {
               slopeSign = -1;
               if (direction == 0)
                  gainDirection = 1;
               else
                  gainDirection = 0;
            } else {
               slopeSign = 1;
               if (direction == 0)
                  gainDirection = 1;
               else
                  gainDirection = 0;
            }
         } else {
            slopeSign = -1;
            if (shape == 0) {
               gainAsymptote = 0;
               if (direction == 0)
                  gainDirection = 1;
               else
                  gainDirection = 0;
            } else if (shape == 1) {
               gainAsymptote = 1;
               if (direction == 0)
                  gainDirection = 0;
               else
                  gainDirection = 1;
            } else
               gainDirection = direction;
         }
         double expNumerator = Math.exp(
               Double.parseDouble(slope) * slopeSign * Math.pow((gainInput - gainAsymptote), Double.parseDouble(order)))
               - 1;
         double expDenominator = Math.exp(Double.parseDouble(slope) * slopeSign) - 1;
         return gainDirection - (2 * gainDirection - 1) * (expNumerator / expDenominator);
      }
      // Sigmoid
      else {
         double sigDenominator = 1 + Math.exp(-1 * Double.parseDouble(slope) * (gainInput - gainAsymptote));
         return 1 - (direction - (2 * direction - 1) / sigDenominator);
      }
   }

   /*
    * createDataset: Method that is translated and adapted from 'CreateDataSet.bas'
    * from the client's code (available on the Additional materials section on
    * Moodle)
    * The purpose of this method is to prepare the final dataset, so it is in the
    * correct format to be written as a CSV file
    * Comments below signify important changes made by myself (redundant lines were
    * removed by myself)
    */
   public void createDataset() {
      // The lab period calculation and clearing of rows occurred in calcLab(), so it
      // was not required here
      pulpeyePeriod = pulpeyePeriod / processPeriod;
      qcsPeriod = qcsPeriod / processPeriod;
      /*
       * The dataset is missing input columns, which have not been read yet
       * Instead, the columns in the below loop only go through the available data
       */
      for (int col = firstVal; col <= lastInputCol; col++) {
         String name = data.get(1, col);
         int numRows;
         if (name.contains("QCS")) {
            numRows = qcsPeriod;
         } else if (name.contains("PulpEye")) {
            numRows = pulpeyePeriod;
         } else
            numRows = 1;
         clear(numRows, col);
      }
      // After rows had been cleared, there is more Java heap memory available to read
      // the remaining rows
      read(firstVal - 1);
      /*
       * The condition is repeated to account of PulpEye variables that could have
       * been missed
       * In the test configurations/Excel file, the input variables are sorted in a
       * way that would not have PulpEye variables removed
       * This loop is only to account for general purpose, if the user enters a
       * different order
       */
      for (int col = 2; col < firstVal; col++) {
         int numRows;
         String name = data.get(1, col);
         if (name.contains("PulpEye")) {
            numRows = pulpeyePeriod;
         } else
            numRows = 1;
         clear(numRows, col);
      }
      /*
       * In VB, the time operation (adding 5 seconds for each row) was simple to
       * implement by making a calculation for every row shown at the end of
       * 'CreateDataSet.bas'
       * In Java, this was not possible so the remainder of this method has been
       * changed to ensure it arrived at the same result
       */
      startDate = startDate + " 00:00:00";
      data.put(3, 1, startDate);
      /*
       * Parts of the below code was adapted from this tutorial (under sections 3, 4,
       * 5):
       * https://howtodoinjava.com/java/date-time/java-localdatetime-class/#3-parsing-
       * a-string-to-localdatetime
       * Variables, text and calculations were adapted to perform the desired
       * functionality from 'CreateDataSet.bas'
       * The DateTimeFormatter and LocalDateTime classes and plusSeconds() method was
       * used from the tutorial
       */
      // This date format is what is shown 'Dataset' sheet from the client's Excel
      // tool (available on OneDrive)
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
      for (int i = 4; i <= finalRow; i++) {
         LocalDateTime date = LocalDateTime.parse(data.get(i - 1, 1), formatter);
         data.put(i, 1, date.plusSeconds(processPeriod).format(formatter));
      }
      // This date format was chosen by myself to create a unique name for the final
      // dataset
      DateTimeFormatter current = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss-SSS");
      String time = LocalDateTime.now().format(current);
      // End of code reference
      write(true, time);
   }

   /*
    * clear: Method that clears data from rows given a row number to operate
    * modulus on and a column
    * This method (created by myself) was used to condense repetitive code in
    * createDataset()
    * In 'CreateDataSet.bas' from the client's code (available on the Additional
    * materials section on Moodle), the data is copied from one spreadsheet to
    * another selectively
    * In Java, there is only one data table, so it needs to be cleared
    * (conditionally through modulus) instead with empty values so the loop cannot
    * be stepped like in VB
    */
   private void clear(int row, int col) {
      for (int i = 4; i <= finalRow; i++) {
         if ((i - 3) % row != 0)
            data.put(i, col, "");
      }
   }

}
