package phenikaa;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public final class pkaLabel {

    private pkaLabel() {
    }

    public static JLabel creLabel(String name, int x, int y, int w, int h) {
        JLabel label = new JLabel(name);
        label.setBounds(x, y, w, h);
        label.setLayout(null);
        label.setBackground(new Color(0xcccccc));
        label.setOpaque(true);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        return label;
    }

}
