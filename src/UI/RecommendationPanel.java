package UI;
import Model.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Display further movie recommendations after rating.
 */
public class RecommendationPanel extends JPanel {
    private DefaultListModel<Movie> movieListModel = new DefaultListModel<>();
    private JList<Movie> movieJList = new JList<>(movieListModel);
    private JButton selectButton;
    private JButton finishButton;

    public RecommendationPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel label = new JLabel("Recommended movies based on your rating:");
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        movieJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieJList.setVisibleRowCount(8);
        JScrollPane scrollPane = new JScrollPane(movieJList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        selectButton = new JButton("View Selected Movie");
        finishButton = new JButton("Finish");

        buttonsPanel.add(selectButton);
        buttonsPanel.add(finishButton);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setRecommendedMovies(List<Movie> movies) {
        movieListModel.clear();
        for (Movie movie : movies) {
            movieListModel.addElement(movie);
        }
    }

    public Movie getSelectedMovie() {
        return movieJList.getSelectedValue();
    }

    public void addSelectListener(ActionListener listener) {
        selectButton.addActionListener(listener);
    }
    public void addFinishListener(ActionListener listener) {
        finishButton.addActionListener(listener);
    }

}

