package UI;  // ensure this matches your folder/package name capitalization

import Model.*;
import Service.RecommendationService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main application frame holding all panels with CardLayout.
 */
public class MovieRecommenderMain extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private SelectionPanel selectionPanel = new SelectionPanel();
    private MovieInfoPanel movieInfoPanel = new MovieInfoPanel();
    private RatePanel ratePanel = new RatePanel();
    private RecommendationPanel recommendationPanel = new RecommendationPanel();

    private RecommendationService recommendationService = new RecommendationService();
    private UserHistory userHistory = new UserHistory();

    private Movie currentMovie = null;
    private Language selectedLanguage;
    private Genre selectedGenre;

    public MovieRecommenderMain() {
        setTitle("Movie Recommendation System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        // Add panels to main panel
        mainPanel.add(selectionPanel, "selection");
        mainPanel.add(movieInfoPanel, "movieInfo");
        mainPanel.add(ratePanel, "rate");
        mainPanel.add(recommendationPanel, "recommendations");
        add(mainPanel);

        // Setup event listeners
        setupListeners();

        cardLayout.show(mainPanel, "selection");
    }

    private void setupListeners() {
        // SelectionPanel "Next" button
        selectionPanel.addNextButtonListener(e -> {
            selectedLanguage = selectionPanel.getSelectedLanguage();
            selectedGenre = selectionPanel.getSelectedGenre();

            if (selectedLanguage == null || selectedGenre == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select both language and genre.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Movie firstMovie = recommendationService.getFirstUnratedMovie(selectedLanguage, selectedGenre, userHistory);
            if (firstMovie == null) {
                JOptionPane.showMessageDialog(this,
                        "No movies available for selected language and genre OR all watched.",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            currentMovie = firstMovie;
            movieInfoPanel.displayMovie(currentMovie);
            cardLayout.show(mainPanel, "movieInfo");
        });

        // MovieInfoPanel rate button
        movieInfoPanel.addRateButtonListener(e -> {
            // Reset the ratePanel UI before showing
            ratePanel.resetRating();

            // Add submit listener only once to avoid multiple triggers
            if (!ratePanel.hasSubmitListener()) {
                ratePanel.addSubmitListener(ev -> {
                    int rating = ratePanel.getSelectedRating();
                    if (rating < 1 || rating > 5) {
                        JOptionPane.showMessageDialog(this,
                                "Please select a rating from 1 to 5 stars.",
                                "Rating Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    userHistory.addRating(currentMovie, rating);
                    // Get next recommendation based on rating
                    Movie nextMovie = recommendationService.recommendNextMovie(currentMovie, rating, userHistory);

                    if (nextMovie == null) {
                        JOptionPane.showMessageDialog(this,
                                "Thank you! No more new recommendations available.",
                                "Finished",
                                JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.show(mainPanel, "selection");
                        return;
                    }

                    currentMovie = nextMovie;

                    // Show recommendations panel listing all next possible movies
                    List<Movie> recs = recommendationService.getMovies(selectedLanguage, selectedGenre);
                    // Filter to movies not rated yet
                    recs.removeIf(m -> userHistory.hasRated(m));
                    if (recs.isEmpty()) recs.add(currentMovie); // fallback

                    recommendationPanel.setRecommendedMovies(recs);
                    cardLayout.show(mainPanel, "recommendations");
                });
            }

            cardLayout.show(mainPanel, "rate");
        });

        // MovieInfoPanel back button
        movieInfoPanel.addBackButtonListener(e -> cardLayout.show(mainPanel, "selection"));

        // RecommendationPanel select button
        recommendationPanel.addSelectListener(e -> {
            Movie selected = recommendationPanel.getSelectedMovie();
            if (selected == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a movie from the recommendations.",
                        "Selection Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentMovie = selected;
            movieInfoPanel.displayMovie(currentMovie);
            cardLayout.show(mainPanel, "movieInfo");
        });

        // RecommendationPanel finish button
        recommendationPanel.addFinishListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Thank you for using the Movie Recommendation System!",
                    "Goodbye",
                    JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(mainPanel, "selection");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieRecommenderMain app = new MovieRecommenderMain();
            app.setVisible(true);
        });
    }
}
