package generator;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Table;

public class CalcStateDyn {

    @FunctionalInterface
    public interface DynamicValueFunction {
        void apply(int row, int col, boolean isInput);
    }

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
    }

    public void calcStateDyn() {
    }

}