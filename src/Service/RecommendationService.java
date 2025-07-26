package Service;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides movie recommendations and movie lists.
 */
public class RecommendationService {
    private final List<Movie> allMovies = new ArrayList<>();

    public RecommendationService() {
        // Initialize some movies
        allMovies.add(new Movie(
                "The Grand Budapest Hotel",
                Language.ENGLISH,
                Genre.COMEDY,
                "A quirky comedy following the adventures of a legendary concierge.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"
        ));

        allMovies.add(new Movie(
                "Interstellar",
                Language.ENGLISH,
                Genre.SCIFI,
                "A team travels through a wormhole to save humanity.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"
        ));


        allMovies.add(new Movie(
                "24",
                Language.TAMIL,
                Genre.SCIFI,
                "A thrilling sci-fi action drama featuring time manipulation.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"
        ));

        allMovies.add(new Movie(
                "Taare Zameen Par",
                Language.HINDI,
                Genre.DRAMA,
                "The story of a dyslexic child and his art teacher.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"
        ));


        allMovies.add(new Movie(
                "Kabali",
                Language.TAMIL,
                Genre.ACTION,
                "An action-packed gangster drama.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"));

        allMovies.add(new Movie(
                "Psycho",
                Language.ENGLISH,
                Genre.HORROR,
                "Classic psychological thriller by Alfred Hitchcock.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"));

        allMovies.add(new Movie(
                "Super Deluxe",
                Language.TAMIL,
                Genre.DRAMA,
                "Multiple stories interconnected with dark humor.",
                "/posters/grand_budapest.jpg",
                "https://www.youtube.com/watch?v=1Fg5iWmQjwk"));

    }

    /**
     * Get movies matching language and genre.
     */
    public List<Movie> getMovies(Language language, Genre genre) {
        return allMovies.stream()
                .filter(m -> m.getLanguage() == language && m.getGenre() == genre)
                .collect(Collectors.toList());
    }

    /**
     * Get a single recommended movie from movies of given language and genre that user hasn't rated yet.
     * If all rated, suggests null.
     */
    public Movie getFirstUnratedMovie(Language lang, Genre genre, UserHistory history) {
        return getMovies(lang, genre).stream()
                .filter(m -> !history.hasRated(m))
                .findFirst().orElse(null);
    }

    /**
     * Suggest next movie based on current positive rating (3+ stars),
     * different genre but same language, or else any not yet rated.
     */
    public Movie recommendNextMovie(Movie lastMovie, int lastRating, UserHistory history) {
        List<Movie> candidates;

        if (lastRating >= 3) {
            // Recommend different genre but same language movies user hasn't rated
            candidates = allMovies.stream()
                    .filter(m -> m.getLanguage() == lastMovie.getLanguage())
                    .filter(m -> m.getGenre() != lastMovie.getGenre())
                    .filter(m -> !history.hasRated(m))
                    .collect(Collectors.toList());

            if (!candidates.isEmpty()) return candidates.get(0);
        }

        // Otherwise, recommend any movie user hasn't rated
        candidates = allMovies.stream()
                .filter(m -> !history.hasRated(m))
                .collect(Collectors.toList());

        if (!candidates.isEmpty())
            return candidates.get(0);

        // If all movies rated, return null
        return null;
    }
}
