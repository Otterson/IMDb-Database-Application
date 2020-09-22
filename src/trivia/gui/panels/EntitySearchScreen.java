package trivia.gui.panels;

import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntitySearchScreen {
    private JButton peopleButton;
    private JPanel startScreen;
    private JButton moviesButton;
    private JLabel message;

    public EntitySearchScreen() {
        peopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setSearchTarget("People");
                UIController.showRestrictionTypeScreen();
            }
        });
        moviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIController.setSearchTarget("Movies");
                UIController.showRestrictionTypeScreen();
            }
        });
    }

    public JPanel getPanel() {
        return startScreen;
    }
}
