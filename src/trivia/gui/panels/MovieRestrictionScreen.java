package trivia.gui.panels;

import trivia.entities.Movie;
import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieRestrictionScreen {
    private JButton restartButton;
    private JButton addAnotherButton;
    private JButton searchButton;
    private JTextField title;
    private JTextField releaseDate;
    private JTextField genre;
    private JTextField runtime;
    private JTextField rating;
    private JPanel movieRestrictionPanel;

    public MovieRestrictionScreen() {
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.clearRestrictions();
                UIController.showStartScreen();
            }
        });
        addAnotherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRestriction();
                UIController.showRestrictionTypeScreen();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRestriction();
                UIController.signalInputEnd();
            }
        });
    }

    public JPanel getPanel() {
        return movieRestrictionPanel;
    }

    private void addRestriction() {
        UIController.addRestriction(new Movie(title.getText()));
    }
}
