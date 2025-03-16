package ch.supsi.mavendemo;

/**
 * Modello di un film, con attributi come titolo, anno, durata, regista, attori e rating.
 */
public class Movie {
    private final String title;
    private final int year;
    private final int runtime;
    private final String director;
    private final String[] actors;
    private final double rating;

    public Movie(String title, int year, int runtime, String director, String[] actors, double rating) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Title: " + title
                + ", Year: " + year
                + ", Runtime: " + runtime + " min"
                + ", Director: " + director
                + ", Rating: " + rating;
    }
}