package generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

class ConfigurationCsvTest {
      private static final List<String> CONFIG_FILES = List.of(
                  "input.csv", "state.csv", "output.csv", "processDesc.csv", "process.csv", "labOutputs.csv");

      @TempDir
      Path tempDirectory;

      @Test
      void preservesColumnPositionsInSparseTables() throws IOException {
            Table<Integer, Integer, String> original = TreeBasedTable.create();
            original.put(1, 1, "Outputs");
            original.put(1, 2, "Lab_Tensile");
            original.put(5, 2, "50");

            Table<Integer, Integer, String> imported = roundTrip(original, "sparse.csv");

            assertEquals("", imported.get(5, 1));
            assertEquals("50", imported.get(5, 2));
      }

      @Test
      void preservesCommasQuotesAndEmptyCells() throws IOException {
            Table<Integer, Integer, String> original = TreeBasedTable.create();
            original.put(1, 1, "name");
            original.put(1, 2, "value, with comma");
            original.put(2, 1, "quoted \"value\"");
            original.put(2, 2, "");

            Table<Integer, Integer, String> imported = roundTrip(original, "escaped.csv");

            assertEquals(original, imported);
      }

      @Test
      void roundTripsEveryExistingConfigurationSet() throws IOException {
            File configRoot = new File("config");
            File[] configDirectories = configRoot.listFiles(File::isDirectory);
            if (configDirectories == null) {
                  throw new IOException("Could not read configuration directory: " + configRoot.getAbsolutePath());
            }

            for (File configDirectory : configDirectories) {
                  Path exportedDirectory = Files.createDirectory(tempDirectory.resolve(configDirectory.getName()));
                  for (String fileName : CONFIG_FILES) {
                        Table<Integer, Integer, String> original = TreeBasedTable.create();
                        ConfigurationCsv.read(new File(configDirectory, fileName), original);

                        File exportedFile = exportedDirectory.resolve(fileName).toFile();
                        ConfigurationCsv.write(exportedFile, original);

                        Table<Integer, Integer, String> imported = TreeBasedTable.create();
                        ConfigurationCsv.read(exportedFile, imported);
                        assertEquals(original, imported,
                                    () -> configDirectory.getName() + File.separator + fileName + " changed");
                  }
            }
      }

      private Table<Integer, Integer, String> roundTrip(Table<Integer, Integer, String> original, String fileName)
                  throws IOException {
            File file = tempDirectory.resolve(fileName).toFile();
            ConfigurationCsv.write(file, original);
            Table<Integer, Integer, String> imported = TreeBasedTable.create();
            ConfigurationCsv.read(file, imported);
            return imported;
      }
}
