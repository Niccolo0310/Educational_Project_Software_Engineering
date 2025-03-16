package ch.supsi.mavendemo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe che gestisce la lettura di un CSV e la creazione di oggetti Movie.
 */
public class MovieCSVReader {

    private MovieCSVReader() {
        // Evita istanziazione
    }

    public static List<Movie> readCSV(String filePath) throws IOException, CsvException {
        List<Movie> movies = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = csvReader.readAll();


            // Salta l'intestazione
            if (!rows.isEmpty()) {
                rows.remove(0);
            }

            for (String[] data : rows) {
                if (data.length < 16) {
                    continue; // Skip invalid rows
                }

                String title = data[1];
                int year = parseYear(data[2]);
                int runtime = parseRuntime(data[4]);
                double rating = parseRating(data[6]);
                String director = data[9];
                String[] actors = { data[10], data[11], data[12], data[13] };

                // Verifica anno plausibile
                if (year > 1900 && year < 2100) {
                    movies.add(new Movie(title, year, runtime, director, actors, rating));
                }
            }
        }


        return movies;
    }

    private static int parseYear(String yearString) {
        try {
            return Integer.parseInt(yearString.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static int parseRuntime(String runtimeString) {
        Matcher matcher = Pattern.compile("\\d+").matcher(runtimeString);
        return matcher.find() ? Integer.parseInt(matcher.group()) : 0;
    }

    private static double parseRating(String ratingString) {
        try {
            return Double.parseDouble(ratingString.trim());
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid rating format for " + ratingString);
            return 0.0;
        }
    }
}