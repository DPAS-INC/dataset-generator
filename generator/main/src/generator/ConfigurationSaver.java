package generator;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

/** Writes the current configuration state without generating a dataset. */
final class ConfigurationSaver {
      private ConfigurationSaver() {
      }

      static void save(File directory, Table<Integer, Integer, String> input,
                  Table<Integer, Integer, String> state, Table<Integer, Integer, String> output,
                  Table<Integer, String, String> descriptions,
                  Map<String, Table<Integer, Integer, String>> labOutputs,
                  Map<String, Double> processVariables, String startDate, String trim, String draw)
                  throws IOException {
            if (directory == null || !directory.isDirectory()) {
                  throw new IOException("The save folder does not exist or is not a directory");
            }

            // Build and validate every derived table before creating any files, so an
            // invalid configuration cannot leave a partially saved folder behind.
            Table<Integer, Integer, String> processDescriptions = processDescriptions(descriptions);
            Table<Integer, Integer, String> process = processConfiguration(processVariables, startDate, trim, draw);
            Table<Integer, Integer, String> labs = labConfiguration(labOutputs);

            write(directory, input, "input");
            write(directory, state, "state");
            write(directory, output, "output");
            write(directory, processDescriptions, "processDesc");
            write(directory, process, "process");
            write(directory, labs, "labOutputs");
      }

      private static void write(File directory, Table<Integer, Integer, String> table, String name)
                  throws IOException {
            ConfigurationCsv.write(new File(directory, name + ".csv"), table);
      }

      private static Table<Integer, Integer, String> processDescriptions(
                  Table<Integer, String, String> descriptions) {
            Table<Integer, Integer, String> result = TreeBasedTable.create();
            result.put(1, 1, "Description");
            result.put(2, 1, "Deadtime Reel (min)");
            result.put(3, 1, "Lag 1 Reel (min)");
            result.put(4, 1, "Lag 2 Reel (min)");
            for (int column = 1; column <= descriptions.rowKeySet().size(); column++) {
                  result.put(1, column + 1, descriptions.get(column, "Name"));
                  result.put(2, column + 1, descriptions.get(column, "Deadtime Reel"));
                  result.put(3, column + 1, descriptions.get(column, "Lag 1"));
                  result.put(4, column + 1, descriptions.get(column, "Lag 2"));
            }
            return result;
      }

      private static Table<Integer, Integer, String> processConfiguration(Map<String, Double> variables,
                  String startDate, String trim, String draw) throws IOException {
            String[] headers = { "Start Date", "Process Period (sec)", "Analyzer 1 Period", "Lab Period (sec)",
                        "Analyzer 2 Period", "Added Settle Time (sec)", "Uncoupled Moves", "Isolated Moves",
                        "Lab Offset (sec)", "Trim (ft)", "Draw" };
            String[] keys = { null, "Process", "QCS", "Lab", "Pulpeye", "Settle", "Uncoupled", "Isolated",
                        "LabOffset", null, null };
            String[] directValues = { startDate, null, null, null, null, null, null, null, null, trim, draw };

            Table<Integer, Integer, String> result = TreeBasedTable.create();
            for (int row = 1; row <= headers.length; row++) {
                  result.put(row, 1, headers[row - 1]);
                  String key = keys[row - 1];
                  if (key == null) {
                        result.put(row, 2, directValues[row - 1] == null ? "" : directValues[row - 1]);
                  } else {
                        Double value = variables.get(key);
                        if (value == null) {
                              throw new IOException("Missing process setting: " + key);
                        }
                        result.put(row, 2, value.toString());
                  }
            }
            return result;
      }

      private static Table<Integer, Integer, String> labConfiguration(
                  Map<String, Table<Integer, Integer, String>> labOutputs) {
            Table<Integer, Integer, String> result = TreeBasedTable.create();
            int row = 1;
            for (Map.Entry<String, Table<Integer, Integer, String>> entry : labOutputs.entrySet()) {
                  result.put(row, 1, entry.getKey());
                  for (int column = 2; column <= 8; column++) {
                        result.put(row, column, "");
                  }

                  Table<Integer, Integer, String> table = entry.getValue();
                  for (int sourceRow = 2; sourceRow <= table.rowKeySet().size(); sourceRow++) {
                        row++;
                        for (int column = 1; column <= 8; column++) {
                              String value = table.get(sourceRow, column);
                              result.put(row, column, value == null ? "" : value);
                        }
                  }

                  row++;
                  for (int column = 1; column <= 8; column++) {
                        result.put(row, column, "");
                  }
                  row++;
            }
            return result;
      }
}
