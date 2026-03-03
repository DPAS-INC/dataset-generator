package generator;

import com.google.common.collect.Table;

public class StateCalculator {
    private final Table<Integer, Integer, String> data;
    private final Table<Integer, Integer, String> state;
    private final int finalRow;
    private int lastInputCol;

    public static final int TRIM_AMOUNT = 20;
    public static final double DRAW_FACTOR = 1.1;

    public StateCalculator(Table<Integer, Integer, String> data,
            Table<Integer, Integer, String> state,
            int finalRow,
            int lastInputCol) {
        this.data = data;
        this.state = state;
        this.finalRow = finalRow;
        this.lastInputCol = lastInputCol;
    }

    /*
     * calcState: Method that applies specific calculations to some state variables
     */
    public void calcState() {
    }

    // --- helpers private to this class ---

    /*
     * stateSetup: Method for calculating a given state column given its input
     * column and state table column
     */
    private void stateSetup(int col, int inCol, int stateCol) {
        // Since the state calculations were specific, the values could be hard-coded
        double intercept = 1000;
        double asymptote = 300;
        double slope = 0.5;
        double noise = Double.parseDouble(state.get(6, col));
        for (int i = 3; i <= finalRow; i++) {
            double noiseVal = calcNoise(noise);
            double inputVal = Double.parseDouble(data.get(i, inCol));
            double val = intercept - (intercept - asymptote) * (1 - 1 / Math.exp(slope * inputVal)) + noiseVal;
            data.put(i, stateCol, String.valueOf(val));
        }
    }

    /*
     * searchCol: Method returns the column number of a given name from a given
     * table
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
     * calcNoise: Method that calculates a random noise value from a given value
     */
    private double calcNoise(double noise) {
        return 2 * Math.random() * noise - noise;
    }

}
