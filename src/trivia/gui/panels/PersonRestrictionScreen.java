package trivia.gui.panels;

import trivia.entities.Person;
import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonRestrictionScreen {
    private JButton restartButton;
    private JButton addAnotherButton;
    private JButton searchButton;
    private JTextField name;
    private JTextField birthYear;
    private JTextField deathYear;
    private JTextField relatedJob;
    private JPanel movieRestrictionPanel;

    public PersonRestrictionScreen() {
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
        UIController.addRestriction(new Person(name.getText()));
    }
}
