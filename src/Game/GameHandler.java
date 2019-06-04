package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameHandler extends KeyAdapter {

    private Game game;
    private final int ROWS;
    private final int COLUMNS;
    private int score = 0;
    private int level = 1;
    private int wrong = 0;
    private int correct = 0;
    private int currentCoordinate = 0;
    private int previousCoordinate = 0;

    public GameHandler(Game game){
        this.game = game;
        ROWS = game.getROWS();
        COLUMNS = game.getCOLUMNS();
    }

    //*************************************user input handler******************************************
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moveUp();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moveDown();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moveRight();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moveLeft();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            enterPressed();
        }
        else if(e.getKeyCode() == KeyEvent.VK_H){
            find();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            game.setVisible(false);
        }
    }

    //**********************updating score, numbers, color, correct, wrong, level, status***************************
    private  void updateScore(boolean update){
        if(update){
            score += level;
        }
        else{
            if(score > 0){
                score = score - 1;
            }
            else{
                score = 0;
            }
        }

        game.getStatusPanel().getScoreLabel().setText("Score: " + score);
    }

    private  void updateLevel(){
        if(correct % 10 == 0){
            level++;
            game.regenarateNumbers(level);
            game.getStatusPanel().getLevelLabel().setText("Level: " + level);
        }
    }

    private  void updateWrong(){
        wrong++;
        game.getStatusPanel().getWrongLabel().setText("Mistakes: " + wrong);
    }

    private  void updateCorrect(){
        correct++;
        game.getStatusPanel().getCorrectLabel().setText("Correct: " + correct);
    }

    private  void updateStatus(String status){
        game.getStatusPanel().getStatusLabel().setText("Status: " + status);
    }

    private void updateNumbers(JButton button) {
        game.getTasks().remove(game.getNumberToFind());

        if(level >= 10){
            game.addNewTask(10);
        }
        else{
            game.addNewTask(level);
        }

        game.setNumberToFind();
        button.setText(game.getTasks().get(game.getTasks().size()-1).getAnswerAsText());
        game.getNumberToFindField().setText(game.getNumberToFind().getAsText());
    }

    private void updateColors(){
        int x = currentCoordinate/ COLUMNS;
        int y = currentCoordinate% COLUMNS;

        game.getStatusPanel().getCoordinatesLabel().setText("Coordinates: [" + y + ":" + x + "]");

        int previousX = previousCoordinate/ COLUMNS;
        int previousY = previousCoordinate% COLUMNS;

        game.getNumberButtons(previousX, previousY).setBackground(Color.LIGHT_GRAY);
        game.getNumberButtons(x, y).setBackground(Color.GREEN);

    }

    //********************************handling multiple correct answers color******************************
    private void resetColors(){
        int x = currentCoordinate/ COLUMNS;
        int y = currentCoordinate% COLUMNS;

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if(i == x && j == y){
                    game.getNumberButtons(i, j).setBackground(Color.GREEN);
                }
                else {
                    game.getNumberButtons(i, j).setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    //********************************movement****************************************************
    private void moveUp(){
        previousCoordinate = currentCoordinate;
        int tempCurrentCoordinates = currentCoordinate - COLUMNS;

        if(tempCurrentCoordinates < 0){
            currentCoordinate = currentCoordinate + COLUMNS*(ROWS-1);
        }
        else{
            currentCoordinate -= COLUMNS;
        }

        updateColors();
    }

    private void moveDown(){
        previousCoordinate = currentCoordinate;
        int tempCurrentCoordinates = currentCoordinate + COLUMNS;

        if(tempCurrentCoordinates >= ROWS*COLUMNS){
            currentCoordinate = currentCoordinate - COLUMNS*(ROWS-1);
        }
        else{
            currentCoordinate += COLUMNS;
        }

        updateColors();
    }

    private void moveRight(){
        previousCoordinate = currentCoordinate;
        int tempCurrentCoordinates = currentCoordinate + 1;

        if(tempCurrentCoordinates% COLUMNS == 0){
            currentCoordinate = currentCoordinate - COLUMNS + 1;
        }
        else{
            currentCoordinate++;
        }

        updateColors();
    }

    private void moveLeft(){
        previousCoordinate = currentCoordinate;

        if(currentCoordinate% COLUMNS == 0){
            currentCoordinate = currentCoordinate + COLUMNS - 1;
        }
        else{
            currentCoordinate--;
        }

        updateColors();
    }

    //********************************find****************************************************

    private void find(){

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if(game.getNumberButtons(i, j).getText().equals(game.getNumberToFind().getAnswerAsText())){
                    game.getNumberButtons(i, j).setBackground(Color.CYAN);
                }
            }
        }

        if(score-level >= 0){
            score = score - level;
        }
        else{
            score = 0;
        }

        game.getStatusPanel().getScoreLabel().setText("Score: " + score);
    }

    //********************************enter****************************************************
    private void enterPressed(){

        int x = currentCoordinate/ COLUMNS;
        int y = currentCoordinate% COLUMNS;

        if(game.getNumberButtons(x, y).getText().equals(game.getNumberToFind().getAnswerAsText())){
            updateNumbers(game.getNumberButtons(x, y));
            updateScore(true);
            updateCorrect();
            updateLevel();
            updateStatus("correct");
            resetColors();
        }
        else{
            updateScore(false);
            updateStatus("wrong");
            updateWrong();
        }
    }
}
