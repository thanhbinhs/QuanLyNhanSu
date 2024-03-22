package phenikaa;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class pkaButton extends JButton {

    public pkaButton(String name, int x, int y, int w, int h) {
        super(name);
        setBounds(x, y, w, h);
        setBackground(new Color(0xEED3D9));
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(true);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public static JComboBox<String> creComboBox(String[] content, int x, int y, int w, int h) {
        JComboBox<String> box = new JComboBox<>(content);
        box.setBounds(x, y, w, h);
        box.setBackground(new Color(0xF5E8dd));
        box.setFocusable(false);
        box.setFont(new Font("Arial", Font.PLAIN, 14));
        box.setForeground(Color.BLACK);
        box.setOpaque(true);
        box.setForeground(Color.BLACK);
        return box;
    }
}
