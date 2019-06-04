package Game;

import javax.swing.*;
import java.awt.*;

public class Builder {

    public static JLabel getLabel(Color color, String text){
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(color);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setText(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    public static JButton getButton(Color color){
        JButton button = new JButton();
        button.setOpaque(true);
        button.setBackground(color);
        return button;
    }
}
