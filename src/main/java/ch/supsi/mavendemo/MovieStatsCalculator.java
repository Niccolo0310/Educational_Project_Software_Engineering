package ch.supsi.mavendemo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe che calcola statistiche a partire da una lista di film.
 */
public class MovieStatsCalculator {

    private MovieStatsCalculator() {
        // Evita istanziazione
    }

    public static Map<String, String> calculateStatistics(List<Movie> movies) {
        Map<String, String> stats = new HashMap<>();
        stats.put("Total Movies", String.valueOf(movies.size()));

        // Durata media
        double avgRuntime = movies.stream()
                .filter(m -> m.getRuntime() > 0)
                .mapToInt(Movie::getRuntime)
                .average()
                .orElse(0);
        stats.put("Average Runtime", String.format("%.2f", avgRuntime));

        // Miglior regista (rating medio più alto)
        Map<String, Double> directorRatings = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getDirector,
                        Collectors.averagingDouble(Movie::getRating)
                ));
        String bestDirector = directorRatings.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
        stats.put("Best Director", bestDirector);

        // Attore/attrice più presente
        Map<String, Long> actorCount = movies.stream()
                .flatMap(m -> Arrays.stream(m.getActors()))
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));
        String mostPresentActor = actorCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Unknown");
        stats.put("Most Present Actor/Actress", mostPresentActor);

        // Anno più produttivo
        int mostProductiveYear = movies.stream()
                .collect(Collectors.groupingBy(Movie::getYear, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);
        stats.put("Most Productive Year", String.valueOf(mostProductiveYear));

        return stats;
    }
}