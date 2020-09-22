package trivia.gui.panels;

import trivia.Main;
import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen {
    private JButton entitiySearchButton;
    private JButton shortestPathButton;
    private JButton leastActivityButton;
    private JButton actorsMostLocalizationsButton;
    private JButton findRelatedMoviesButton;
    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public StartScreen() {
        entitiySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setQuestionNember(0);
                UIController.signalInputEnd();
            }
        });
        shortestPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setQuestionNember(1);
                UIController.signalInputEnd();
            }
        });
        leastActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setQuestionNember(2);
                UIController.signalInputEnd();
            }
        });
        actorsMostLocalizationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setQuestionNember(3);
                UIController.signalInputEnd();
            }
        });
        findRelatedMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setQuestionNember(4);
                UIController.signalInputEnd();
            }
        });
    }
}
