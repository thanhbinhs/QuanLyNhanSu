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
        label.setBackground(new Color(0xB5C0D0));
        label.setOpaque(true);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }

}
