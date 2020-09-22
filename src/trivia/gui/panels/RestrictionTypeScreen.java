package trivia.gui.panels;

import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestrictionTypeScreen {
    private JButton peopleButton;
    private JButton movieButton;
    private JButton restartButton;
    private JButton searchButton;
    private JPanel restrictionTypePanel;

    public RestrictionTypeScreen() {
        peopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.showPersonRestrictionScreen();
            }
        });
        movieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.showMovieRestrictionScreen();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.clearRestrictions();
                UIController.showStartScreen();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.signalInputEnd();
            }
        });
    }

    public JPanel getPanel() {
        return restrictionTypePanel;
    }
}
