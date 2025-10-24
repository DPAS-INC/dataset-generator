package generator;

import com.google.common.collect.Table;

public class StateCalculator {
  private final Table<Integer, Integer, String> data;
  private final Table<Integer, Integer, String> state;
  private final double trim;
  private final double draw;
  private final int finalRow;
  private int lastInputCol;

  public StateCalculator(Table<Integer, Integer, String> data,
                         Table<Integer, Integer, String> state,
                         double trim,
                         double draw,
                         int finalRow,
                         int lastInputCol) {
    this.data = data;
    this.state = state;
    this.trim = trim;
    this.draw = draw;
    this.finalRow = finalRow;
    this.lastInputCol = lastInputCol;
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
  
  
  
  
  // --- helpers private to this class ---

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
   * calcNoise: Method that calculates a random noise value from a given value
   */
  private double calcNoise (double noise){
     return 2 * Math.random() * noise - noise;
  }
  
  
  
  
}
