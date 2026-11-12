package generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

class ConfigurationSaverTest {
      @TempDir
      Path tempDirectory;

      @Test
      void savesCurrentSettingsWithoutGeneratingDataset() throws IOException {
            Table<Integer, Integer, String> input = table("Inputs", "Current input");
            Table<Integer, Integer, String> state = table("State", "Current state");
            Table<Integer, Integer, String> output = table("Outputs", "Current output");
            Table<Integer, String, String> descriptions = TreeBasedTable.create();
            descriptions.put(1, "Name", "Current area");
            descriptions.put(1, "Deadtime Reel", "1");
            descriptions.put(1, "Lag 1", "2");
            descriptions.put(1, "Lag 2", "3");

            Table<Integer, Integer, String> lab = TreeBasedTable.create();
            lab.put(1, 2, "Weight");
            lab.put(2, 1, "Current input");
            lab.put(2, 2, "5");
            Map<String, Table<Integer, Integer, String>> labs = new LinkedHashMap<>();
            labs.put("Current output", lab);

            Map<String, Double> process = processSettings();
            process.put("Process", 15.0);

            ConfigurationSaver.save(tempDirectory.toFile(), input, state, output, descriptions, labs, process,
                        "10/29/26", "4", "5");

            assertEquals("15.0", read("process.csv").get(2, 2));
            assertEquals("Current input", read("input.csv").get(1, 2));
            assertEquals("Current area", read("processDesc.csv").get(1, 2));
            assertEquals("Current output", read("labOutputs.csv").get(1, 1));
            assertFalse(Files.exists(tempDirectory.resolve("data")),
                        "Saving configuration must not generate a dataset");
      }

      @Test
      void reportsMissingSettingsWithoutWritingPartialConfiguration() {
            Map<String, Double> incompleteProcess = processSettings();
            incompleteProcess.remove("LabOffset");

            IOException error = assertThrows(IOException.class,
                        () -> ConfigurationSaver.save(tempDirectory.toFile(), table("Inputs", "Input"),
                                    table("State", "State"), table("Outputs", "Output"),
                                    TreeBasedTable.create(), new LinkedHashMap<>(), incompleteProcess,
                                    "10/29/26", "", ""));

            assertTrue(error.getMessage().contains("LabOffset"));
            assertFalse(Files.exists(tempDirectory.resolve("input.csv")));
      }

      private Table<Integer, Integer, String> table(String header, String currentValue) {
            Table<Integer, Integer, String> table = TreeBasedTable.create();
            table.put(1, 1, header);
            table.put(1, 2, currentValue);
            return table;
      }

      private Map<String, Double> processSettings() {
            Map<String, Double> process = new HashMap<>();
            process.put("Process", 5.0);
            process.put("QCS", 60.0);
            process.put("Lab", 1200.0);
            process.put("Pulpeye", 300.0);
            process.put("Settle", 10000.0);
            process.put("Uncoupled", 10.0);
            process.put("Isolated", 0.0);
            process.put("LabOffset", 0.0);
            return process;
      }

      private Table<Integer, Integer, String> read(String fileName) throws IOException {
            Table<Integer, Integer, String> table = TreeBasedTable.create();
            ConfigurationCsv.read(tempDirectory.resolve(fileName).toFile(), table);
            return table;
      }
}
