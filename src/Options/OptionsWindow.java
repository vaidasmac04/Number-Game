package Options;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class OptionsWindow extends JFrame {
    private JComboBox<Integer> rows;
    private JComboBox<Integer> columns;
    private JLabel rowsLabel;
    private JLabel columnsLabel;
    private JButton start;
    private JButton exit;
    private int rowsNumber;
    private int columnsNumber;

    public OptionsWindow(){
        Integer[] options = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        rows = new JComboBox<>(options);
        columns = new JComboBox<>(options);
        start = new JButton("Start");
        exit = new JButton("Exit");
        rowsLabel = new JLabel("Rows:");
        rowsLabel.setHorizontalAlignment(JLabel.RIGHT);
        columnsLabel = new JLabel("Columns:");
        columnsLabel.setHorizontalAlignment(JLabel.RIGHT);

        addComponents();
    }

    public void start(){
        addActionListenerToButtons();
    }

    private void addComponents(){
        setLayout(new GridLayout(3, 2, 20, 20));
        add(rowsLabel);
        add(rows);
        add(columnsLabel);
        add(columns);
        add(start);
        add(exit);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
    }

    private void addActionListenerToButtons(){
        start.addActionListener(
                (event) -> {
                    rowsNumber = (int)rows.getSelectedItem();
                    columnsNumber = (int)columns.getSelectedItem();

                    Game game = new Game(rowsNumber, columnsNumber);
                    game.start();
                }
        );

        exit.addActionListener(
                (event) -> {
                    System.exit(0);
                }
        );

    }
}
