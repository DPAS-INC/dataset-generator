package generator;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Table;

public class StateCalculator {
    public static final Map<String, Double> PROCESS_VARIABLES = new HashMap<>();

    public StateCalculator(Table<Integer, Integer, String> data,
            Table<Integer, Integer, String> state,
            int finalRow,
            int lastInputCol) {
    }

    /*
     * calcState: Method that applies specific calculations to some state variables
     */
    public void calcState() {
    }
}
