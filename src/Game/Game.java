package Game;
import Arithmetics.*;

import javax.swing.*;

import java.awt.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;

public class Game extends JFrame{

	//In buttonPannel is displayed 2D array of numberButtons
	private JPanel buttonPannel;
	private JButton[][] numberButtons;

	//Tasks
	private List<Task> tasks; //all task that are displayed
	private Task numberToFind; //task that is needed to solve
	private JLabel numberToFindField; //displaying task to solve

	//status panel
	private StatusPanel statusPanel;

	//game: rows, columns
	private final int ROWS;
	private final int COLUMNS;

	public Game(int rows, int columns) {
		super("Game");
		ROWS = rows;
		COLUMNS = columns;
		setupFrame();
		setupStatusPanel();
		createTasks();
		setupButtonPannel();
		setVisible(true);
	}

	//start a game
	public void start(){
        addActionToPanel();
	}

	//********************************setup status panel******************************************
	private void setupStatusPanel(){
		statusPanel = new StatusPanel();
		add(statusPanel, BorderLayout.SOUTH);
	}

	//******************************create tasks********************************************************
	private void createTasks(){
		setupArithmetics();
		setNumberToFind();
		setupNumberToFindField();
	}

	private void setupArithmetics(){
		tasks = new ArrayList<>();

		for(int i = 0; i < ROWS* COLUMNS; i++){
			addNewTask(1);
		}
	}

	//this function is used in GameHandler class
	protected void addNewTask(int level){
		tasks.add(new Task(level));
	}

	//this function is used in GameHandler class
	protected void setNumberToFind(){
		Random random = new Random();
		int taskIndex = random.nextInt(tasks.size());
		numberToFind = tasks.get(taskIndex);
	}

	private void setupNumberToFindField(){
		numberToFindField = new JLabel();
		numberToFindField.setOpaque(true);
		numberToFindField.setBackground(Color.ORANGE);
		numberToFindField.setText(numberToFind.getAsText());
		numberToFindField.setFont(new Font("Serif", Font.BOLD, 30));
		numberToFindField.setHorizontalAlignment(JLabel.CENTER);
		numberToFindField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(numberToFindField, BorderLayout.NORTH);
	}

	//********************************setup frame******************************************
	private void setupFrame() {
		setLayout(new BorderLayout());
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	//********************************setup button panel******************************************
	private void setupButtonPannel(){
		setupNumbersButton();
		numberButtons[0][0] = Builder.getButton(Color.GREEN);

		buttonPannel = new JPanel(new GridLayout(ROWS, COLUMNS, 5, 5));
        buttonPannel.setFocusable(true);

		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				buttonPannel.add(numberButtons[i][j]);

			}
		}

		setupNumbersToButtonPanel();
		
		add(buttonPannel, BorderLayout.CENTER);
	}

	private void setupNumbersButton(){
		numberButtons = new JButton[ROWS][COLUMNS];

		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				numberButtons[i][j] = Builder.getButton(Color.LIGHT_GRAY);
			}
		}
	}
	
	private void setupNumbersToButtonPanel(){

		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				numberButtons[i][j].setText(tasks.get(i* COLUMNS+j).getAnswerAsText());
			}
		}
	}
	
	//**************when level is completed, numbers are regenerated***********************************
	protected void regenarateNumbers(int level){
		tasks.clear();

		for(int i = 0; i < ROWS*COLUMNS; i++){
			addNewTask(level);
		}


		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++) {
				numberButtons[i][j].setText(tasks.get(i*COLUMNS+j).getAnswerAsText());
			}
		}

		numberToFind = tasks.get(new Random().nextInt(tasks.size()));
		numberToFindField.setText(numberToFind.getAsText());
	}
	
	//*************************************user input handler******************************************
	private void addActionToPanel(){
		buttonPannel.addKeyListener(new GameHandler(this));
	}


	//*********************************************getters************************************************
	public JButton getNumberButtons(int x, int y) {
		return numberButtons[x][y];
	}

	public JLabel getNumberToFindField() {
		return numberToFindField;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	public int getROWS() {
		return ROWS;
	}

	public int getCOLUMNS() {
		return COLUMNS;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public Task getNumberToFind() {
		return numberToFind;
	}
}