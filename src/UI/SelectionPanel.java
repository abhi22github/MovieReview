package UI;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel where user selects language and genre.
 */
public class SelectionPanel extends JPanel {
    private JComboBox<Language> languageCombo;
    private JComboBox<Genre> genreCombo;
    private JButton nextButton;

    public SelectionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Select your preferred Movie Language and Genre:");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.insets = new Insets(10,10,20,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);

        JLabel langLabel = new JLabel("Language:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5,10,5,10);
        add(langLabel, gbc);

        languageCombo = new JComboBox<>(Language.values());
        gbc.gridx = 1;
        add(languageCombo, gbc);

        JLabel genreLabel = new JLabel("Genre:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(genreLabel, gbc);

        genreCombo = new JComboBox<>(Genre.values());
        gbc.gridx = 1;
        add(genreCombo, gbc);

        nextButton = new JButton("Next");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20,10,10,10);
        add(nextButton, gbc);
    }

    public Language getSelectedLanguage() {
        return (Language) languageCombo.getSelectedItem();
    }

    public Genre getSelectedGenre() {
        return (Genre) genreCombo.getSelectedItem();
    }


    public void addNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }
}
