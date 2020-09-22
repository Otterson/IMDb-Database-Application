package trivia.gui.panels;

import trivia.gui.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YearRangeWindow {
    private JPanel panel1;
    private JTextField endYearBox;
    private JTextField startYearBox;
    private JLabel endYear;
    private JLabel startYear;
    private JButton continueButton;
    private JButton restartButton;

    public JPanel getPanel() {
        return panel1;
    }

    public YearRangeWindow() {
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    UIController.setYears(Integer.parseInt(startYearBox.getText()),Integer.parseInt(endYearBox.getText()));
                    UIController.signalInputEnd();
                } catch (Exception tryAgain){
                    JOptionPane.showMessageDialog(null,"Invalid Input try again.");
                }
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add restart feature
            }
        });
    }
}
