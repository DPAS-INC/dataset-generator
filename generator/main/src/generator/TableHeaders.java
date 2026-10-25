package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.google.common.collect.Table;

/**
 * Helpers for reading variable names from configuration tables as produced from
 * CSV files. State configs use row 1 column 1 as the label {@code State}; actual
 * state variable names are in row 1 from column 2 onward.
 */
public final class TableHeaders {

    private TableHeaders() {
    }

    /**
     * State variable names from {@code state} row 1, every column index {@code >= 2}
     * that has a non-blank header. Order follows ascending column index.
     */
    public static List<String> stateVariableNames(Table<Integer, Integer, String> state) {
        List<String> out = new ArrayList<>();
        if (state == null || state.cellSet().isEmpty()) {
            return out;
        }
        List<Integer> cols = new ArrayList<>(state.columnKeySet());
        Collections.sort(cols);
        for (int c : cols) {
            if (c < 2) {
                continue;
            }
            String v = state.get(1, c);
            if (v != null && !v.trim().isEmpty()) {
                out.add(v.trim());
            }
        }
        return out;
    }

    /**
     * First header in row {@code 1} between {@code minCol} and {@code maxCol} inclusive
     * that matches {@code predicate}.
     */
    public static String firstMatchingHeader(
            Table<Integer, Integer, String> t,
            int minCol,
            int maxCol,
            Predicate<String> predicate) {
        if (t == null || minCol > maxCol) {
            return null;
        }
        for (int c = minCol; c <= maxCol; c++) {
            String h = t.get(1, c);
            if (h == null) {
                continue;
            }
            String trim = h.trim();
            if (!trim.isEmpty() && predicate.test(trim)) {
                return trim;
            }
        }
        return null;
    }

}
