package ch.supsi.mavendemo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Classe di utilità per scrivere i risultati in un file CSV.
 */
public class CSVWriter {

    private CSVWriter() {
        // Evita istanziazione
    }

    public static void writeCSV(String filePath, Map<String, String> statistics) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Map.Entry<String, String> entry : statistics.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        }
    }
}