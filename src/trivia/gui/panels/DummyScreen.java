package trivia.gui.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DummyScreen {
    private JButton button1;
    private JPanel panel1;
    private JLabel label1;

    public DummyScreen() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hit");
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    private void createUIComponents() {
        System.out.println("creating");
        label1 = new JLabel();
        label1.setText("TEST\nTest");
    }


}
