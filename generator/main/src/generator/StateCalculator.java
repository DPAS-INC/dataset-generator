package generator;

import java.util.HashMap;
import java.util.Map;

import java.util.Objects;
import java.util.List;


import com.google.common.collect.Table;

public class StateCalculator {

	// No state variables are required for the Blank implementation
	public static final List<String> REQUIRED_STATES = List.of();
    private final Table<Integer, Integer, String> data;
    private final Table<Integer, Integer, String> state;
    private final int finalRow;
    private int lastInputCol;

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
