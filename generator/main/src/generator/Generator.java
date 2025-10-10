package generator;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

// Generator class performs the dataset generation 1
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
   // dyn: Data table for the temporary dynamic values
   private Table<Integer, Integer, String> dyn;
   // startDate: Date at which the final dataset starts at
   String startDate;
   // processPeriod: Process period time
   int processPeriod;
   // labPeriod: Lab period time
   int labPeriod;
   // pulpeyePeriod: PulpEye period time
   int pulpeyePeriod;
   // qcsPeriod: QCS period time
   int qcsPeriod;
   // lastRow: The last row in the dataset
   int finalRow;
   // 2/18/25 lastSteadyStateRow: The last row in the dataset
   int lastSteadyStateRow;
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
   // 2/18/25 isolatedMoves: The number of isolated moves
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

   public Generator(Table<Integer, Integer, String> input, Table<Integer, Integer, String> output, LinkedHashMap<String, Table<Integer, Integer, String>> labOutputs,
                    Table<Integer, Integer, String> state, HashMap<String, Double> process, String startDate){
      this.input = input;
      this.output = output;
      this.labOutputs = labOutputs;
      this.state = state;
      this.startDate = startDate;
      data = TreeBasedTable.create();
      firstVal = 2;

      processPeriod = process.get("Process").intValue();
      qcsPeriod = process.get("QCS").intValue();
      labPeriod = process.get("Lab").intValue();
      pulpeyePeriod = process.get("Pulpeye").intValue();
      uncoupledMoves = process.get("Uncoupled").intValue();
	// 2/18/25 add isolated moves
      isolatedMoves = 5; // process.get("Isolated").intValue();
      trim = process.get("Trim");
      draw = process.get("Draw");
      coupledMoves = process.get("Coupled").intValue();
      numInputs = input.columnKeySet().size() - 1;
      numOutputs = labOutputs.keySet().size();
      numState = state.columnKeySet().size() - 1;
      lastInputCol = numInputs + numState + 1;

      int deadtime = max(3);
      int lag1 = max(4);
      int lag2 = max(5);
      int maxSettle = process.get("Settle").intValue() + deadtime + lag1 + lag2;
      inputSettle = Math.max(maxSettle, labPeriod);
      // Creating the dataset heading names
      data.put(1, 1, "TIME");
      data.put(2, 1, "");
      for (int i = 2; i <= numInputs + 1; i++){
         data.put(1, i, input.get(1, i));
         data.put(2, i, input.get(2, i));
      }
      int lastCol;
      for (int i = 2; i <= numState + 1; i++){
         lastCol = i + numInputs;
         data.put(1, lastCol, state.get(1, i));
         data.put(2, lastCol, state.get(2, i));
      }
      for (int i = 2; i <= numOutputs + 1; i++){
         lastCol = i + numInputs + numState;
         data.put(1, lastCol, output.get(1, i));
         data.put(2, lastCol, output.get(2, i));
      }

      dyn = TreeBasedTable.create();
      for (int i = 2; i <= lastInputCol + 1; i++){
         dyn.put(1, i, data.get(1, i));
      }
      dynRow = Math.round(maxSettle / processPeriod) + 3;
   }

   /*
   * max: Method that calculates the maximum value from all input variables from a given column
   */
   private int max(int col){
      int max = 0;
      for (int i = 2; i < numInputs + 2; i++){
         max = (int) Math.max(max, Double.parseDouble(input.get(col, i)) * 60);
      }
      return max;
   }

   /*
    * calcNoise: Method that calculates a random noise value from a given value
    */
   private double calcNoise (double noise){
      return 2 * Math.random() * noise - noise;
   }

   /*
    * calcSine: Method that calculates a random sine value from a given value
    */
   private double calcSine (double period, double amplitude, int row){
      double value = 360 * (row * (processPeriod / period));
      double degrees = value % 360;
      double radians = Math.toRadians(degrees);
      return Math.sin(radians) * amplitude;
   }

   /*
    * searchCol: Method returns the column number of a given name from a given table
    */
   private int searchCol (String name, Table<Integer, Integer, String> t){
      for (int i = 2; i < lastInputCol + 3; i++){
         String var = t.get(1, i);
         if (var.equals(name))
            return i;
      }
      return 0;
   }

   /*
    * calcList: Method that adds variables to a given list of a given column size if the variables are present in lab configurations
    */
   private void calcList(List<Integer> list, int size, Table<Integer, Integer, String> table){
      for (String i: labOutputs.keySet()){
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

   /*
    * write: Method for writing to a CSV file given the file name and whether the table should be final
    */
   private void write(boolean table, String name){
      Table<Integer, Integer, String> t;
      if (!table) {
         t = TreeBasedTable.create();
         for (int i = 1; i <= finalRow; i++){
            // When writing to the temporary dataset, not all columns are written to save memory
            for (int j = 1; j < firstVal; j++){
               t.put(i, j, data.get(i, j));
            }
         }
      }
      else
         t = data;
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + name + ".csv"));
         /*
          * Below code was adapted from the question in this website: https://stackoverflow.com/questions/38524942/guava-table-to-csv
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
    * read: Method for reading the temporary CSV file into a data table given the column limit
    */
   private void read(int last){
      try {
         /*
          * Below code was adapted from the first answer in this website: https://stackoverflow.com/questions/42170837/how-to-read-a-csv-file-into-an-array-list-in-java
          */
         File file = new File("data/data.csv");
         List<String[]> csv = new ArrayList<>();
         BufferedReader br = new BufferedReader(new FileReader(file));
         String line;
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
    * createInputs: Method that creates the input variable data
    * Determines the number of rows to represent each move, it needs to be the longer of the MV settling time or the lab sample period
    */
   public void createInputs(){

	System.out.println("createInputs");

      double rowsPerMove = inputSettle / processPeriod;
      int rowsPerProcess = 1;
      int firstRow = 3;
      int lastInCol = numInputs + 2;
      int row = 0;
      int lastRow = 0;

      // Steady state rows
      // Fill in steady state rows with the average value of input
      for (int i = 2; i < lastInCol; i++) {
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         double avg = min + (max - min) / 2;
         double noise = Double.parseDouble(input.get(6, i));
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));
         for (int j = 1; j <= (rowsPerMove / rowsPerProcess); j++) {
            double noiseVal = calcNoise(noise);
            row = firstRow - 1 + rowsPerProcess * j;
            double sineVal = calcSine(sinePeriod, amplitude, row);
            data.put(row, i, String.valueOf((avg + noiseVal + sineVal)));
         }
         lastRow = row;
      }
      lastSteadyStateRow = lastRow;

      // Uncoupled moves rows
      // Moves inputs uncoupled (independently)
      for (int i = 2; i < lastInCol; i++) {
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         int order = Integer.parseInt(input.get(12, i));
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));
         double stepSize;

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
               }
               else
                  next = lastMove;
               for (int x = 1; x <= (rowsPerMove / rowsPerProcess); x++) {
                  row = lastRow + rowsPerProcess * x;
                  double noiseVal = calcNoise(noise);
                  double sineVal = calcSine(sinePeriod, amplitude, row);
                  double priorVal = Double.parseDouble(data.get(row-1, i));
                  double newVal = priorVal * (1 - mvFilter) + next * mvFilter;
                  if (newVal < min)
                     newVal = min;
                  else if (newVal > max)
                     newVal = max;
                  data.put(row, i, String.valueOf((newVal + noiseVal + sineVal)));
               }
               lastRow = row;
            }
            lastMove = move;
         }
      }

	System.out.println("createInputs row " + row + " lastRow " + lastRow );
      int lastInRow = lastRow;



      // Set all inputs to average for settling time
      for (int i = 2; i < lastInCol; i++) {
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         double avg = min + (max - min) / 2;
         double noise = Double.parseDouble(input.get(6, i));
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));

	System.out.print("createInputs settle 1 input " + i + " avg " + avg + " lastRow " + lastRow);
	System.out.println();

	// 12/18/25 start at lastInRow
         for (int j = 1; j <= (rowsPerMove / rowsPerProcess); j++) {
            double noiseVal = calcNoise(noise);
            row = lastRow - 1 + rowsPerProcess * j;
            double sineVal = calcSine(sinePeriod, amplitude, row);
            data.put(row, i, String.valueOf((avg + noiseVal + sineVal)));
	System.out.println("createInputs settle 1 input " + i + " avg " + avg + " lastRow " + lastRow  + " row " + row + " data " + String.valueOf((avg + noiseVal + sineVal)) );
         }
//         lastRow = row;
	System.out.print("createInputs settle 1 input " + i + " avg " + avg + " lastRow " + lastRow  + " row " + row);
	System.out.println();
      }
      lastRow = row;
      lastSteadyStateRow = lastRow;
	System.out.println("createInputs settle 1 complete lastRow " + lastRow);

      // Start isolated moves
      // 3/3/24 Isolated moves rows
      for (int i = 2; i < lastInCol; i++) 
	{
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         double avg = min + (max - min) / 2;
         int order = Integer.parseInt(input.get(12, i));
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));
         double stepSize;
         double move;
	double priorVal;
	int moveInc;

	// 2/18/25 Use isolated move count

	System.out.print("createInputs isolated moves input " + i + " avg " + avg + " lastRow " + lastRow + " isolatedMoves " + isolatedMoves);
	System.out.println();

         if (isolatedMoves != 0)
            stepSize = (max - min) / isolatedMoves;
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

	// Start from last uncoupled row
         double noise = Double.parseDouble(input.get(6, i));
         double lastMove = min;
	// 2/18/25 Use isolatedMoves

        for (int inputCount = 2; inputCount <= lastInCol; inputCount++) 
	{
         	for (int j = 0; j <= isolatedMoves; j++) 
		{
			if (order == inputCount) 
                   		move = min + stepSize * j;
                	else
                  		move = avg;

			System.out.flush();

			moveInc = (int) Math.round(rowsPerMove / rowsPerProcess);
			System.out.print("TEST ");
			System.out.println("TEST LN ");
			System.out.println("createInputs isolated moves rowsPerMove " + rowsPerMove);
			System.out.println("createInputs isolated moves rowsPerProcess " + rowsPerProcess);
			System.out.println("createInputs isolated moves moveInc " + moveInc);
			System.out.println("createInputs isolated moves input " + i + " avg " + avg + " lastRow " + lastRow + " isolated move j " + j + " moveInc " + moveInc);

           
               		for (int x = 1; x <= moveInc; x++) 
		  	{
                  		row = lastRow + x + j * moveInc + (inputCount-2)*isolatedMoves*moveInc;

System.out.println("createInputs isolated moves input " + i + " inputCount " + inputCount + " move " + move + " lastRow " + lastRow + " isolated move j " + j + " row x " + x + " row " + row);

                  		double noiseVal = calcNoise(noise);
//				System.out.println("createInputs isolated moves noiseVal ");
                  		double sineVal = calcSine(sinePeriod, amplitude, row);
//				System.out.println("createInputs isolated moves sineVal ");
                  		priorVal = Double.parseDouble(data.get(246151, i));
//				System.out.println("createInputs isolated moves priorVal-2 " + data.get(row-1, i));
                  		priorVal = Double.parseDouble(data.get(row-1, i));
//				System.out.println("createInputs isolated moves priorVal " + priorVal);
                  		double newVal = priorVal * (1 - mvFilter) + move * mvFilter;
//				System.out.println("createInputs isolated moves newVal " + newVal);
                  		if (newVal < min)
                     			newVal = min;
                  		else if (newVal > max)
                     			newVal = max;

				System.out.println("createInputs isolated moves before data.put input " + i + " inputCount " + inputCount + " moveInc " + moveInc + " newVal " + newVal + " lastRow " + lastRow + " isolated move j " + j + " row x " + x + " row " + row);

                  		data.put(row, i, String.valueOf((newVal + noiseVal + sineVal)));

//				System.out.println("createInputs isolated moves input " + i + " newVal " + newVal + " lastRow " + lastRow + " isolated move j " + j + " row x " + x + " row " + row);
               	  	}
		}

//		System.out.print("createInputs isolated moves after data.put input " + i + " lastRow " + lastRow + " row " + row);
		System.out.println();

	}
//        	lastRow = row;
      }
	lastRow = row;
      	lastInRow = lastRow;

	// End of isolated moves

      // Set all inputs to average for settling time
      for (int i = 2; i < lastInCol; i++) {
         double min = Double.parseDouble(input.get(9, i));
         double max = Double.parseDouble(input.get(8, i));
         double avg = min + (max - min) / 2;
         double noise = Double.parseDouble(input.get(6, i));
         double sinePeriod = Double.parseDouble(input.get(10, i));
         double amplitude = Double.parseDouble(input.get(11, i));

	System.out.print("createInputs settle 2 input " + i + " avg " + avg + "  lastRow " + lastRow);
	System.out.println();

	// 2/18/25 use lastInRow
         for (int j = 1; j <= (rowsPerMove / rowsPerProcess); j++) {
            double noiseVal = calcNoise(noise);
            row = lastRow - 1 + rowsPerProcess * j;
            double sineVal = calcSine(sinePeriod, amplitude, row);
            data.put(row, i, String.valueOf((avg + noiseVal + sineVal)));
         }

	System.out.print("createInputs settle 2 input " + i + " avg " + avg + " lastRow " + lastRow + " row " + row);
	System.out.println();
      }
         lastRow = row;
      lastSteadyStateRow = lastRow;

	System.out.println("createInputs settle 2 complete lastRow " + lastRow);

      // Validation move rows
      int firstValidationRow = 13;
      int lastValidationRow = 13 + coupledMoves;
      int dataRow = 0;
      for (int i = firstValidationRow; i <= lastValidationRow; i++) {
         for (int j = 2; j < lastInCol; j++) {
            double validationValue;
            double noise = Double.parseDouble(input.get(6, j));
            double sinePeriod = Double.parseDouble(input.get(10, j));
            double amplitude = Double.parseDouble(input.get(11, j));
            if (input.get(i, j) == null || input.get(i, j).equals(""))
               validationValue = Double.parseDouble(data.get(lastInRow, j));
            else
               validationValue = Double.parseDouble(input.get(i, j));
		// 2/18/25 Use lastSteadyStateRow
            for (dataRow = lastSteadyStateRow + 1; dataRow <= (lastSteadyStateRow + rowsPerMove); dataRow++) {
               double noiseVal = calcNoise(noise);
               double sineVal = calcSine(sinePeriod, amplitude, dataRow);
               data.put(dataRow, j, String.valueOf((validationValue + noiseVal + sineVal)));
            }
         }
         lastInRow = dataRow - 1;
      }
      finalRow = lastInRow;
      // Empty values are required for the CSV to skip values accurately
      for (int i = 3; i <= finalRow; i++){
         data.put(i, 1, "");
      }

      // List is used to note essentail variables
      List<Integer> inputNames = new ArrayList<>();
      calcList(inputNames, numInputs, input);
      // Input variables that are required in QCS variable calculations are added
      inputNames.add(searchCol("MV_ThinStockFlow", input));
      inputNames.add(searchCol("MV_ThinStockConsistency", input));
      inputNames.add(searchCol("MV_PressLoad", input));
      inputNames.add(searchCol("MV_SteamPressure", input));
      /*
       * Below code was adapted from this website: https://www.freecodecamp.org/news/how-to-sort-a-list-in-java/
       */
      Collections.sort(inputNames);
      // End of code reference
      // The first essential input variable column is recorded, so the rest don't have to be written
      firstVal = inputNames.get(0);
      write(false, "data");
   }

   /*
    * stateSetup: Method for calculating a given state column given its input column and state table column
    */
   private void stateSetup(int col, int inCol, int stateCol){
      // Since the state calculations were specific, the values could be hard-coded
      double intercept = 1000;
      double asymptote = 300;
      double slope = 0.5;
      double noise = Double.parseDouble(state.get(6, col));
      for (int i = 3; i <= finalRow; i++){
         double noiseVal = calcNoise(noise);
         double inputVal = Double.parseDouble(data.get(i, inCol));
         double val = intercept - (intercept - asymptote) * (1 - 1 / Math.exp(slope * inputVal)) + noiseVal;
         data.put(i, stateCol, String.valueOf(val));
      }
   }

   /*
    * calcState: Method that applies specific calculations to some state variables
    */
   public void calcState(){
	System.out.println("calcState");
      stateSetup(searchCol("MV_SWFreeness", state), searchCol("MV_SWSpecificEnergy", data), searchCol("MV_SWFreeness", data));
      stateSetup(searchCol("MV_HWFreeness", state), searchCol("MV_HWSpecificEnergy", data), searchCol("MV_HWFreeness", data));
      stateSetup(searchCol("MV_OCCFreeness", state), searchCol("MV_OCCSpecificEnergy", data), searchCol("MV_OCCFreeness", data));
      for (int i = 3; i <= finalRow; i++){
         double wireSpeed = Double.parseDouble(data.get(i, searchCol("MV_WireSpeed", data)));
         if (wireSpeed <= 1){
            data.put(i, searchCol("MV_HeadboxPressure", data), "0");
            data.put(i, searchCol("MV_SliceOpening", data), "0.2");
            data.put(i, searchCol("MV_MachineSpeed", data), "0");
         }
         else {
            double jetVelocity = Double.parseDouble(data.get(i, searchCol("MV_JettoWire", data))) * wireSpeed;
            data.put(i, searchCol("MV_HeadboxPressure", data), String.valueOf(Math.pow(jetVelocity, 2) / (2 * 115920)));
            double sliceOpening = Double.parseDouble(data.get(i, searchCol("MV_ThinStockFlow", data))) * 12 / (7.48 * jetVelocity * trim);
            data.put(i, searchCol("MV_SliceOpening", data), String.valueOf(sliceOpening));
            data.put(i, searchCol("MV_MachineSpeed", data), String.valueOf(wireSpeed * draw));
         }

         double swFlow = Double.parseDouble(data.get(i, searchCol("MV_SWFlow", data)));
         double hwFlow = Double.parseDouble(data.get(i, searchCol("MV_HWFlow", data)));
         double occFlow = Double.parseDouble(data.get(i, searchCol("MV_OCCFlow", data)));
         double swCrill = Double.parseDouble(data.get(i, searchCol("PulpEye_SWCrill", data)));
         double hwCrill = Double.parseDouble(data.get(i, searchCol("PulpEye_HWCrill", data)));
         double occCrill = Double.parseDouble(data.get(i, searchCol("PulpEye_OCCCrill", data)));
         double totalFlow = swFlow + hwFlow + occFlow;
         double swFreeness = Double.parseDouble(data.get(i, searchCol("MV_SWFreeness", data)));
         double hwFreeness = Double.parseDouble(data.get(i, searchCol("MV_HWFreeness", data)));
         double occFreeness = Double.parseDouble(data.get(i, searchCol("MV_OCCFreeness", data)));
         if (totalFlow <= 100){
            data.put(i, searchCol("MV_SWPct", data), "0");
            data.put(i, searchCol("MV_HWPct", data), "0");
            data.put(i, searchCol("MV_OCCPct", data), "0");
            data.put(i, searchCol("PulpEye_BlendFreeness", data), "0");
            data.put(i, searchCol("PulpEye_BlendCrill", data), "0");
         }
         else {
            data.put(i, searchCol("MV_SWPct", data), String.valueOf(100 * swFlow / totalFlow));
            data.put(i, searchCol("MV_HWPct", data), String.valueOf(100 * hwFlow / totalFlow));
            data.put(i, searchCol("MV_OCCPct", data), String.valueOf(100 * occFlow / totalFlow));
            data.put(i, searchCol("PulpEye_BlendFreeness", data), String.valueOf((swFreeness * swFlow + hwFreeness * hwFlow + occFreeness * occFlow) / totalFlow));
            data.put(i, searchCol("PulpEye_BlendCrill", data), String.valueOf((swCrill * swFlow + hwCrill * hwFlow + occCrill * occFlow) / totalFlow));
         }
      }
   }

   /*
    * dynamicValues: Method that calculates a dynamically moved input or state value for a given row and column
    * These values are stored in a separate table ('dyn') so the original values can still be accessed from the 'data' table
    */
   private void dynamicValues(int row, int col, boolean isInput){
      // Table is assigned based on if the variable is an input or state variable
      Table<Integer, Integer, String> table;
      if (isInput)
         table = input;
      else
         table = state;
      int deadTime = (int) (Double.parseDouble(table.get(3, col)) * 60 / processPeriod);
      double lag1 = Double.parseDouble(table.get(4, col));
      double lag2 = Double.parseDouble(table.get(5, col));
      if (!isInput)
         col = col + numInputs;

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

      double inputDeadtime = Double.parseDouble(data.get(row - deadTime, col));
      double inLag1;
      double inLag2;
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
    * secondOrder: Method that calculates a result given lag and data values
    */
   private double secondOrder(double newOut, double out1, double out2, double lag1, double lag2){
      double firstPrior = (out1 - out2 * (1 - lag2)) / lag2;
      double firstCurrent = newOut * lag1 + firstPrior * (1 - lag1);
      return firstCurrent * lag2 + out1 * (1-lag2);
   }

   /*
    * calcQCS: Method that calculates the QCS variable values
    */
   public void calcQCS(){
	System.out.println("calcQCS");
      // The input variables not required for the remaining methods have been written to a CSV file, therefore those input columns were cleared
      for (int i = 2; i < firstVal; i++){
         for (int j = 3; j < finalRow + 1; j++){
            data.put(j, i, "");
         }
      }

      int col = searchCol("QCS_Caliper", state);
      double caliperMax = Double.parseDouble(state.get(7,col));
      double caliperSlope = 0.02;
      double caliperNoise = Double.parseDouble(state.get(6,col));
      for (int i = 3; i <= finalRow; i++){
         double thinStockFlow;
         double thinStockConsistency;
         double pressLoad;
         double steamPressure;
         double machineSpeed;
         double blendFreeness;
	// 3/3/24 was not declared
	 String blendFreeness_str;

         if (i > dynRow) {
            dynamicValues(i, searchCol("MV_ThinStockFlow", input), true);
            dynamicValues(i, searchCol("MV_ThinStockConsistency", input), true);
            dynamicValues(i, searchCol("MV_PressLoad", input), true);
            dynamicValues(i, searchCol("MV_SteamPressure", input), true);
            dynamicValues(i, searchCol("MV_MachineSpeed", state), false);
            dynamicValues(i, searchCol("PulpEye_BlendFreeness", state), false);
            thinStockFlow = Double.parseDouble(dyn.get(3, searchCol("MV_ThinStockFlow", dyn)));
            thinStockConsistency = Double.parseDouble(dyn.get(3, searchCol("MV_ThinStockConsistency", dyn)));
            pressLoad = Double.parseDouble(dyn.get(3, searchCol("MV_PressLoad", dyn)));
            steamPressure = Double.parseDouble(dyn.get(3, searchCol("MV_SteamPressure", dyn)));
            machineSpeed = Double.parseDouble(dyn.get(3, searchCol("MV_MachineSpeed", dyn)));
            blendFreeness = Double.parseDouble(dyn.get(3, searchCol("PulpEye_BlendFreeness", dyn)));
         }
         else{
            thinStockFlow = Double.parseDouble(data.get(i, searchCol("MV_ThinStockFlow", data)));
            thinStockConsistency = Double.parseDouble(data.get(i, searchCol("MV_ThinStockConsistency", data)));
            pressLoad = Double.parseDouble(data.get(i, searchCol("MV_PressLoad", data)));
            steamPressure = Double.parseDouble(data.get(i, searchCol("MV_SteamPressure", data)));
            machineSpeed = Double.parseDouble(data.get(i, searchCol("MV_MachineSpeed", data)));
		// 11/29/23 if freeness blank set to 0
		blendFreeness_str =data.get(i, searchCol("PulpEye_BlendFreeness", data));
		if (blendFreeness_str == "")	
		{
		blendFreeness = 0;
		}	
		else
		{
            	blendFreeness = Double.parseDouble(blendFreeness_str);
		}
         }

         double boneDryWeight;
         double fiberToHeadbox = thinStockFlow * thinStockConsistency * 8.3 / 100;
         double waterToHeadbox = thinStockFlow * 8.3 - fiberToHeadbox;
         double wireDrainage = 5 + 90 * (1 - 1 / Math.exp(blendFreeness));
         double waterToPress = waterToHeadbox * wireDrainage / 100;
         double pressDrainage = 80 * (1 - 1 / Math.exp(pressLoad / 200));
         double waterToDryers = waterToPress * pressDrainage / 100;
         double moistureToDryers = waterToDryers / fiberToHeadbox;
         double moistureAsymptote = 2.5 + machineSpeed / 500;
         data.put(i, searchCol("QCS_Moisture", data), String.valueOf(moistureAsymptote + (moistureToDryers - moistureAsymptote) / Math.exp(steamPressure / 25)));
         if (machineSpeed <= 1)
            boneDryWeight = 0;
         else
            boneDryWeight = fiberToHeadbox * 3300 / (machineSpeed * trim);
         data.put(i, searchCol("QCS_BoneDryWeight", data), String.valueOf(boneDryWeight));
         data.put(i, searchCol("QCS_BasisWeight", data), String.valueOf(boneDryWeight * (1 + Double.parseDouble(data.get(i, searchCol("QCS_Moisture", data))) / 100)));
         double capMaxCalc = caliperMax * boneDryWeight / 50;
         double capMinCalc = capMaxCalc / 2;
         double noise = calcNoise(caliperNoise);
         data.put(i, searchCol("QCS_Caliper", data), String.valueOf(capMinCalc + (capMaxCalc - capMinCalc) / Math.exp((pressLoad - 700) * caliperSlope) + noise));
      }
   }

   /*
    * calcLab: Method that calculates the output variables from the lab configurations
    */
   public void calcLab(){
       System.out.println("Starting calcLab...");
      List<Integer> inputNames = new ArrayList<>();
      calcList(inputNames, numInputs, input);
	System.out.println("calcLab Finished inputnames ...");

      List<Integer> stateNames = new ArrayList<>();
      calcList(stateNames, numState, state);
	System.out.println("calcLab Finished statenames ...");

      int lastLab = lastInputCol + numOutputs;
      int firstLab = lastInputCol + 1;
      int stateRow = numInputs + 2;
      // Since temporary dynamic values are being used, the method must go through every row so the dynamics can be calculated cumulatively
      labPeriod = labPeriod / processPeriod;
      for (int i = firstLab; i < lastLab + 1; i++){
         String name = data.get(1, i);
         int numRows = labPeriod;
         for (int j = 3; j <= finalRow; j ++){
            if (j > dynRow) {
               for (int input : inputNames) {
                  dynamicValues(j, input, true);
               }
               for (int state : stateNames) {
                  dynamicValues(j, state, false);
               }
            }
            if ((j - 3) % numRows == 0)
               data.put(j, i, String.valueOf(gainModel(name, stateRow, j)));
         }
      }
   }


   /*
    * gainModel: Method that retrieves the lab configurations and calculate the final value
    */
   private double gainModel(String name, int sRow, int row){
      double weightedInput = 0;
      for (int i : labOutputs.get(name).rowKeySet()){
         // First row in a labOutputs table is ignored since it does not contain a variable
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

         if (searchCol(varName, data) < sRow){
            col = searchCol(varName, input);
            max = Double.parseDouble(input.get(8, col));
            min = Double.parseDouble(input.get(9, col));
         }
         else{
            col = searchCol(varName, state);
            max = Double.parseDouble(state.get(7, col));
            min = Double.parseDouble(state.get(8, col));
         }
         double inVal;
         // With temporary dynamic values, the table from which the value is retrieved depends on if dynamics are required
         if (row > dynRow)
            inVal = Double.parseDouble(dyn.get(3, searchCol(varName, dyn)));
         else
            inVal = Double.parseDouble(data.get(row, searchCol(varName, data)));

         weightedInput = gainFunction(inVal, max, min, asymptote, order, slope, model, direction, shape) * weight / 100 + weightedInput;

      }
      int labCol = searchCol(name, output);
      double labMax = Double.parseDouble(output.get(4, labCol));
      double labNoise = calcNoise(Double.parseDouble(output.get(3, labCol)));
      double labMin = Double.parseDouble(output.get(5, labCol));
      return (labMin + (labMax - labMin) * weightedInput) + labNoise;
   }

   /*
    * gainFunction: Method that calculates graph values from the lab configurations
    */
   private double gainFunction(double inVal, double max, double min, String asymptote, String order, String slope,
                              double model, double direction, double shape){
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
      if (model == 0){
         double g1;
         double g2;
         double g0;
         if (Double.parseDouble(order) == 2){
            if (shape == 0){
               if (direction == 0){
                  g2 = 1;
                  g1 = -2;
                  g0 = 1;
               }
               else {
                  g2 = -1;
                  g1 = 2;
                  g0 = 0;
               }
            }
            else if (shape == 1){
               if (direction == 0){
                  g2 = -0.5;
                  g1 = -0.5;
                  g0 = 1;
               }
               else {
                  g2 = 0.5;
                  g1 = 0.5;
                  g0 = 0;
               }
            }
            else {
               g2 = 2 * (0.5 - direction) / Math.pow(0.5 + Math.sqrt(Math.pow(0.5 - gainAsymptote, 2)), 2);
               g1 = -2 * g2 * gainAsymptote;
               g0 = g2 * Math.pow(gainAsymptote, 2) + direction;
            }
         }
         else {
            g2 = 0;
            if (direction == 0){
               g1 = -1;
               g0 = 1;
            }
            else{
               g1 = 1;
               g0 = 0;
            }
         }
         return g2 * Math.pow(gainInput, 2) + g1 * gainInput + g0;
      }
      // Exponential
      else if (model == 1){
         double slopeSign;
         double gainDirection;
         if (Double.parseDouble(order) == 1){
            gainAsymptote = 0;
            if (shape == 0){
               slopeSign = -1;
               if (direction == 0)
                  gainDirection = 1;
               else
                  gainDirection = 0;
            }
            else{
               slopeSign = 1;
               if (direction == 0)
                  gainDirection = 1;
               else
                  gainDirection = 0;
            }
         }
         else {
            slopeSign = -1;
            if (shape == 0){
               gainAsymptote = 0;
               if (direction == 0)
                  gainDirection = 1;
               else
                  gainDirection = 0;
            }
            else if (shape == 1) {
               gainAsymptote = 1;
               if (direction == 0)
                  gainDirection = 0;
               else
                  gainDirection = 1;
            }
            else
               gainDirection = direction;
         }
         double expNumerator = Math.exp(Double.parseDouble(slope) * slopeSign * Math.pow((gainInput - gainAsymptote), Double.parseDouble(order))) - 1;
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
    * createDataset: Method that prepares the final dataset, so it is in the correct format to be written as a CSV file
    */
   public void createDataset(){
      pulpeyePeriod = pulpeyePeriod / processPeriod;
      qcsPeriod = qcsPeriod / processPeriod;
      // The dataset is missing input columns which have not been read yet, so instead the columns in the below loop only go through the available data
      for (int col = firstVal; col <= lastInputCol; col++){
         String name = data.get(1, col);
         int numRows;
         if (name.contains("QCS")) {
            numRows = qcsPeriod;
         }
         else if (name.contains("PulpEye")) {
            numRows = pulpeyePeriod;
         }
         else
            numRows = 1;
         clear(numRows, col);
      }
      // After rows had been cleared, there is more Java heap memory available to read the remaining rows
      read(firstVal - 1);
      for (int col = 2; col < firstVal; col++){
         int numRows;
         String name = data.get(1, col);
         if (name.contains("PulpEye")) {
            numRows = pulpeyePeriod;
         }
         else
            numRows = 1;
         clear(numRows, col);
      }

      startDate = startDate + " 00:00:00";
      data.put(3, 1, startDate);
      /*
       * Parts of the below code was adapted from this tutorial (under sections 3, 4, 5): https://howtodoinjava.com/java/date-time/java-localdatetime-class/#3-parsing-a-string-to-localdatetime
       */
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
      for (int i = 4; i <= finalRow; i++){
         LocalDateTime date = LocalDateTime.parse(data.get(i-1, 1), formatter);
         data.put(i, 1, date.plusSeconds(processPeriod).format(formatter));
      }
      DateTimeFormatter current = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss-SSS");
      String time = LocalDateTime.now().format(current);
      // End of code reference
      // Final dataset can be written to with timings
      write(true, time);
   }

   /*
    * clear: Method that clears data from rows given a row and column number
    */
   private void clear(int row, int col){
      for (int i = 4; i <= finalRow; i++){
         if ((i - 3) % row != 0)
            data.put(i, col, "");
      }
   }
}
