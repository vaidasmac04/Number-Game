package Game;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JLabel scoreLabel;
    private JLabel correctLabel;
    private JLabel wrongLabel;
    private JLabel levelLabel;
    private JLabel coordinatesLabel;
    private JLabel statusLabel;

    public StatusPanel(){
        this.setLayout(new GridLayout(3, 3, 5, 5));
        setup();
        this.setFocusable(true);
        this.setVisible(true);
    }

    private void setup(){
        setupCoordinatesLabel();
        setupStatusLabel();
        setupScore();
        setupLevelLabel();
        setupWrongLabel();
        setupCorrectLabel();
    }

    private void setupStatusLabel(){
        statusLabel = Builder.getLabel(Color.WHITE, "Status: waiting");
        this.add(statusLabel);
    }

    private void setupLevelLabel(){
        levelLabel = Builder.getLabel(Color.WHITE, "Level: 1");
        this.add(levelLabel);
    }

    private void setupWrongLabel(){
        wrongLabel = Builder.getLabel(Color.WHITE, "Mistakes: 0");
        this.add(wrongLabel);
    }

    private void setupCorrectLabel(){
        correctLabel = Builder.getLabel(Color.WHITE, "Correct: 0");
        this.add(correctLabel);
    }

    private void setupCoordinatesLabel(){
        coordinatesLabel = Builder.getLabel(Color.WHITE, "Coordinates: [0:0]");
        this.add(coordinatesLabel);
    }

    private void setupScore(){
        scoreLabel = Builder.getLabel(Color.WHITE, "Score: 0");
        this.add(scoreLabel);
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getCorrectLabel() {
        return correctLabel;
    }

    public JLabel getWrongLabel() {
        return wrongLabel;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    public JLabel getCoordinatesLabel() {
        return coordinatesLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
