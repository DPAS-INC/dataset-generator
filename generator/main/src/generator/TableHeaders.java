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

    /**
     * Resolved header names for the QCS dynamic calculation (inputs from {@code input},
     * state noise/max for caliper from {@code state}, columns in {@code data} for
     * written outputs). Matching uses stable substrings so names need not be
     * hard-coded to legacy {@code MV_*} / {@code QCS_*} prefixes.
     */
    public static QcsDynColumnNames resolveQcsDynColumns(
            Table<Integer, Integer, String> input,
            Table<Integer, Integer, String> state,
            Table<Integer, Integer, String> data,
            int numInputs,
            int numState,
            int lastInputCol) {
        Objects.requireNonNull(data, "data");
        int inHi = Math.max(1, numInputs + 1);
        int stHi = Math.max(1, numState + 1);
        int dataHi = Math.max(2, lastInputCol + 2);

        String thinFlow = firstMatchingHeader(input, 2, inHi,
                h -> h.contains("ThinStockFlow") && !h.contains("Consistency"));
        String thinCons = firstMatchingHeader(input, 2, inHi, h -> h.contains("ThinStockConsistency"));
        String press = firstMatchingHeader(input, 2, inHi, h -> h.contains("PressLoad"));
        String steam = firstMatchingHeader(input, 2, inHi, h -> h.contains("SteamPressure"));

        String machineSpeedState = firstMatchingHeader(state, 2, stHi, h -> h.contains("MachineSpeed"));
        String blendFreenessState = firstMatchingHeader(state, 2, stHi, h -> h.contains("BlendFreeness"));
        String caliperState = firstMatchingHeader(state, 2, stHi, h -> h.contains("Caliper"));

        String moistureData = firstMatchingHeader(data, 2, dataHi, h -> h.contains("Moisture"));
        String boneDryData = firstMatchingHeader(data, 2, dataHi, h -> h.contains("BoneDryWeight"));
        String basisData = firstMatchingHeader(data, 2, dataHi, h -> h.contains("BasisWeight"));
        String caliperData = firstMatchingHeader(data, 2, dataHi, h -> h.contains("Caliper"));

        return new QcsDynColumnNames(
                thinFlow,
                thinCons,
                press,
                steam,
                machineSpeedState,
                blendFreenessState,
                caliperState,
                moistureData,
                boneDryData,
                basisData,
                caliperData);
    }

    /** Column header names used by {@link CalcStateDyn} after {@link TableHeaders#resolveQcsDynColumns}. */
    public record QcsDynColumnNames(
            String thinStockFlowInput,
            String thinStockConsistencyInput,
            String pressLoadInput,
            String steamPressureInput,
            String machineSpeedState,
            String blendFreenessState,
            String caliperState,
            String moistureData,
            String boneDryData,
            String basisData,
            String caliperData) {

        /** True when all inputs and QCS output targets exist on the dataset header row. */
        public boolean hasCoreQcsDatasetColumns() {
            return thinStockFlowInput != null
                    && thinStockConsistencyInput != null
                    && pressLoadInput != null
                    && steamPressureInput != null
                    && moistureData != null
                    && boneDryData != null
                    && basisData != null
                    && caliperData != null;
        }
    }
}
