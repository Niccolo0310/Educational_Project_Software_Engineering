package ch.supsi.mavendemo;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class IMDbProcessor {
    public static void main(String[] args) {
        try {
            // 1. Carica la configurazione
            Properties config = ConfigurationLoader.loadConfig("config.properties");
            String inputFile = config.getProperty("inputFile");
            String outputFile = config.getProperty("outputFile");

            // 2. Leggi CSV e crea lista di Movie
            List<Movie> movies = MovieCSVReader.readCSV(inputFile);
            if (movies.isEmpty()) {
                System.out.println("No valid movies found in the CSV.");
                return;
            }

            // 3. Calcola statistiche
            Map<String, String> statistics = MovieStatsCalculator.calculateStatistics(movies);

            // 4. Scrivi statistiche in CSV
            CSVWriter.writeCSV(outputFile, statistics);

            System.out.println("Processing complete. Results saved to " + outputFile);
        } catch (IOException | CsvException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}