package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel to let user rate the movie from 1 to 5 stars.
 */
public class RatePanel extends JPanel {
    private JButton[] starButtons = new JButton[5];
    private JButton submitButton;
    private int selectedRating = 0;
    private JLabel instructionLabel;
    private JLabel ratingDisplayLabel;

    public RatePanel() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        instructionLabel = new JLabel("Rate the movie:");
        instructionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(instructionLabel, BorderLayout.NORTH);

        JPanel starsPanel = new JPanel();
        starsPanel.setLayout(new FlowLayout());

        for (int i = 0; i < 5; i++) {
            JButton star = new JButton("☆");
            star.setFont(new Font("Dialog", Font.PLAIN, 36));
            int rating = i + 1;
            star.addActionListener(e -> setRating(rating));
            star.setBorderPainted(false);
            star.setContentAreaFilled(false);
            starButtons[i] = star;
            starsPanel.add(star);
        }
        add(starsPanel, BorderLayout.CENTER);

        ratingDisplayLabel = new JLabel("Your rating: 0");
        ratingDisplayLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(ratingDisplayLabel, BorderLayout.WEST);

        submitButton = new JButton("Submit Rating");
        submitButton.setEnabled(false);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void setRating(int rating) {
        selectedRating = rating;
        ratingDisplayLabel.setText("Your rating: " + rating);

        for (int i = 0; i < 5; i++) {
            starButtons[i].setText(i < rating ? "★" : "☆");
        }
        submitButton.setEnabled(true);
    }

    public int getSelectedRating() {
        return selectedRating;
    }

    public void addSubmitListener(ActionListener listener) { submitButton.addActionListener(listener); }
    public void resetRating() {
        selectedRating = 0;
        // reset stars UI here accordingly
    }
    public boolean hasSubmitListener() {
        for (ActionListener al : submitButton.getActionListeners()) {
            if (al != null) return true;
        }
        return false;
    }




}

