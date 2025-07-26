package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores user movie rating history.
 */
public class UserHistory {
    // Map<Movie title, rating>
    private Map<String, Integer> ratings = new HashMap<>();

    public void addRating(Movie movie, int rating) {
        ratings.put(movie.getTitle(), rating);
    }

    public Integer getRating(Movie movie) {
        return ratings.get(movie.getTitle());
    }

    public boolean hasRated(Movie movie) {
        return ratings.containsKey(movie.getTitle());
    }

    public Map<String, Integer> getAllRatings() {
        return ratings;
    }
}
