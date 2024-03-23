package phenikaa;

import java.awt.CardLayout;
import javax.swing.JFrame;

public class pkaFrame extends JFrame {

    public static final pkaPanel CENTER_PANEL, ADD_PANEL, SEARCH_EDIT_PANEL, DISPLAY_PANEL, FUNCTION_PANEL;

    public static final CardLayout centerCardLayout;
    static {
        CENTER_PANEL = pkaPanel.getInstance(200, 0, 1080, 832);
        ADD_PANEL = pkaPanel.ADD_PANEL;
        SEARCH_EDIT_PANEL = pkaPanel.SEARCH_EDIT_PANEL;
        DISPLAY_PANEL = pkaPanel.DISPLAY_PANEL;
        FUNCTION_PANEL = pkaPanel.FUNCTION_PANEL;

        centerCardLayout = new CardLayout();
        CENTER_PANEL.setLayout(centerCardLayout);
        CENTER_PANEL.add("Thêm nhân viên", ADD_PANEL);
        CENTER_PANEL.add("Tìm kiếm và chỉnh sửa", SEARCH_EDIT_PANEL);
        CENTER_PANEL.add("Thống kê", DISPLAY_PANEL);

    }

    public pkaFrame(String name) {
        super(name);
        initial();
        addComponents();
    }

    private void initial() {
        setLayout(null);
        setSize(1280, 852);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        add(CENTER_PANEL);
        add(FUNCTION_PANEL);
    }

}