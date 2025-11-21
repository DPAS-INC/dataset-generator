package generator;

import com.google.common.collect.Table;

public class calcStateDyn {

    @FunctionalInterface
    public interface DynamicValueFunction {
        void apply(int row, int col, boolean isInput);
    }

    private final Table<Integer, Integer, String> data;
    private final Table<Integer, Integer, String> state;
    private final Table<Integer, Integer, String> input;
    private final Table<Integer, Integer, String> dyn;
    private final int finalRow;
    private final int lastInputCol;
    private final int firstVal;
    private final int dynRow;
    private final DynamicValueFunction dynamicValues;
    
    public static final int TRIM_AMOUNT = 20;

    public calcStateDyn(
            Table<Integer, Integer, String> data,
            Table<Integer, Integer, String> state,
            Table<Integer, Integer, String> input,
            Table<Integer, Integer, String> dyn,
            int finalRow,
            int lastInputCol,
            int firstVal,
            int dynRow,
            DynamicValueFunction dynamicValues) {
        this.data = data;
        this.state = state;
        this.input = input;
        this.dyn = dyn;
        this.finalRow = finalRow;
        this.lastInputCol = lastInputCol;
        this.firstVal = firstVal;
        this.dynRow = dynRow;
        this.dynamicValues = dynamicValues;
    }
	  
	private int searchCol (String name, Table<Integer, Integer, String> t){
	      for (int i = 2; i < lastInputCol + 3; i++){
	         String var = t.get(1, i);
	         if (var.equals(name))
	            return i;
	      }
	      return 0;
	   }
	private double calcNoise (double noise){
		     return 2 * Math.random() * noise - noise;
	}
	  
	public void calcStateDyn(){
		System.out.println("calcStateDyn");
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
	            dynamicValues.apply(i, searchCol("MV_ThinStockFlow", input), true);
	            dynamicValues.apply(i, searchCol("MV_ThinStockConsistency", input), true);
	            dynamicValues.apply(i, searchCol("MV_PressLoad", input), true);
	            dynamicValues.apply(i, searchCol("MV_SteamPressure", input), true);
	            dynamicValues.apply(i, searchCol("MV_MachineSpeed", state), false);
	            dynamicValues.apply(i, searchCol("PulpEye_BlendFreeness", state), false);
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
	            boneDryWeight = fiberToHeadbox * 3300 / (machineSpeed * TRIM_AMOUNT);
	         data.put(i, searchCol("QCS_BoneDryWeight", data), String.valueOf(boneDryWeight));
	         data.put(i, searchCol("QCS_BasisWeight", data), String.valueOf(boneDryWeight * (1 + Double.parseDouble(data.get(i, searchCol("QCS_Moisture", data))) / 100)));
	         double capMaxCalc = caliperMax * boneDryWeight / 50;
	         double capMinCalc = capMaxCalc / 2;
	         double noise = calcNoise(caliperNoise);
	         data.put(i, searchCol("QCS_Caliper", data), String.valueOf(capMinCalc + (capMaxCalc - capMinCalc) / Math.exp((pressLoad - 700) * caliperSlope) + noise));
	      }
   }
}
