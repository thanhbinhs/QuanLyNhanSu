package phenikaa;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class pkaButton extends JButton {

    public pkaButton(String name, int x, int y, int w, int h) {
        super(name);
        setBounds(x, y, w, h);
        setBackground(new Color(0x7f7f7f));
        setFocusable(false);
        setBorderPainted(false);
        setOpaque(true);
        setForeground(Color.BLACK);
        setFont(new Font("Times New Roman", Font.PLAIN, 12));
    }

    public static JComboBox<String> creComboBox(String[] content, int x, int y, int w, int h) {
        JComboBox<String> box = new JComboBox<>(content);
        box.setBounds(x, y, w, h);
        box.setBackground(new Color(0xf2f2f2));
        box.setFocusable(false);
        box.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        box.setForeground(Color.BLACK);
        box.setOpaque(true);
        box.setForeground(Color.BLACK);
        return box;
    }
}
