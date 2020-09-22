package trivia.gui.panels;

import javax.swing.*;

public class MatchResultScreen {
    private JPanel resultsScreen;
    private JLabel result_string;
    private String resultText;
    private String resultsString;

    public MatchResultScreen(String results) {
        resultText = results;

    }

    public JPanel getPanel() {
        return resultsScreen;
    }

    private void createUIComponents() {
        result_string = new JLabel(resultText);
    }
}
