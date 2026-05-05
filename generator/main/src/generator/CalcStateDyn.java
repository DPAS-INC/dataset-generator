package generator;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Table;

// IMPORTANT: DO NOT ALLOW THIS TO BE RENAMED calcStateDyn
public class CalcStateDyn {

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

    public static final Map<String, Double> PROCESS_VARIABLES = new HashMap<>();

    static {
        // Keep in sync with StateCalculator.STATE_VARIABLES.
        PROCESS_VARIABLES.putAll(StateCalculator.PROCESS_VARIABLES);
    }

    public CalcStateDyn(
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

    private int searchCol(String name, Table<Integer, Integer, String> t) {
        for (int i = 2; i < lastInputCol + 3; i++) {
            String var = t.get(1, i);
            if (var.equals(name))
                return i;
        }
        return 0;
    }

    private double calcNoise(double noise) {
        return 2 * Math.random() * noise - noise;
    }

    public void calcStateDyn() {
    }

}