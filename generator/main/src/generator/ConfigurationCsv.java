package generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.google.common.collect.Table;

/**
 * Reads and writes the rectangular CSV tables used by configuration folders.
 */
final class ConfigurationCsv {
      private ConfigurationCsv() {
      }

      static void read(File file, Table<Integer, Integer, String> table) throws IOException {
            table.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                        CSVParser parser = CSVFormat.DEFAULT.parse(reader)) {
                  int row = 1;
                  for (CSVRecord record : parser) {
                        for (int column = 0; column < record.size(); column++) {
                              table.put(row, column + 1, record.get(column));
                        }
                        row++;
                  }
            }
      }

      static void write(File file, Table<Integer, Integer, String> table) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
                  if (table.isEmpty()) {
                        return;
                  }

                  int lastRow = table.rowKeySet().stream().mapToInt(Integer::intValue).max().orElse(0);
                  int lastColumn = table.columnKeySet().stream().mapToInt(Integer::intValue).max().orElse(0);
                  for (int row = 1; row <= lastRow; row++) {
                        for (int column = 1; column <= lastColumn; column++) {
                              String value = table.get(row, column);
                              printer.print(value == null ? "" : value);
                        }
                        printer.println();
                  }
            }
      }
}
