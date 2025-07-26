package UI;

import Model.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;


/**
 * Panel to display selected movie info.
 */
public class MovieInfoPanel extends JPanel {
    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JLabel posterLabel;
    private JButton rateButton;
    private JButton backButton;
    private JLabel clipLinkLabel;

    public MovieInfoPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        posterLabel = new JLabel();
        posterLabel.setHorizontalAlignment(JLabel.CENTER);
        add(posterLabel, BorderLayout.WEST);

        descriptionArea = new JTextArea();
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        rateButton = new JButton("Rate this Movie");
        backButton = new JButton("Back to Selection");
        buttonPanel.add(backButton);
        buttonPanel.add(rateButton);

        clipLinkLabel = new JLabel("");
        clipLinkLabel.setForeground(Color.BLUE.darker());
        clipLinkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clipLinkLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));

        clipLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String url = clipLinkLabel.getText();
                if (!url.isEmpty()) {
                    openWebpage(url);
                }
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(clipLinkLabel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);
    }

    public void displayMovie(Movie movie) {
        titleLabel.setText(movie.getTitle());
        descriptionArea.setText(movie.getDescription());

        // Load poster image safely
        ImageIcon icon = loadImage(movie.getImagePath());
        if (icon != null) {
            // Scale image if needed
            Image img = icon.getImage();
            Image scaled = img.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
            posterLabel.setIcon(new ImageIcon(scaled));
        } else {
            posterLabel.setIcon(null);
        }

        String clipUrl = movie.getClipUrl();
        if (clipUrl != null && !clipUrl.isEmpty()) {
            clipLinkLabel.setText(clipUrl);
        } else {
            clipLinkLabel.setText("");
        }
    }

    private ImageIcon loadImage(String path) {
        try {
            // Try loading from resource folder (adjust if your images are elsewhere)
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null)
                return new ImageIcon(imgURL);
            else
                return new ImageIcon(path);  // fallback if you use absolute path or URL
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void openWebpage(String uri) {
        try {
            Desktop.getDesktop().browse(new URI(uri));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to open link: " + uri);
        }
    }

    public void addRateButtonListener(ActionListener listener) { rateButton.addActionListener(listener); }

    public void addBackButtonListener(ActionListener listener) { backButton.addActionListener(listener); }


}

