package phenikaa;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class pkaFrame extends JFrame {

    public static final pkaPanel CENTER_PANEL, ADD_PANEL, SEARCH_EDIT_PANEL, DISPLAY_PANEL;
    public static final pkaPanel buttonPanel;
    public static final pkaButton addEmp, editEmp, showEmp;
    public static final CardLayout centerCardLayout;
    static {
        CENTER_PANEL = pkaPanel.getInstance(200, 0, 1080, 832);
        ADD_PANEL = pkaPanel.ADD_PANEL;
        SEARCH_EDIT_PANEL = pkaPanel.SEARCH_EDIT_PANEL;
        DISPLAY_PANEL = pkaPanel.DISPLAY_PANEL;
        buttonPanel = pkaPanel.getInstance(0, 0, 200, 832);

        addEmp = new pkaButton("Thêm nhân viên", 37, 52, 150, 50);
        editEmp = new pkaButton("Tìm kiếm và chỉnh sửa", 37, 137, 150, 50);
        showEmp = new pkaButton("Thống kê", 37, 224, 150, 50);

        centerCardLayout = new CardLayout();
        CENTER_PANEL.setLayout(centerCardLayout);
        CENTER_PANEL.add("Thêm nhân viên", ADD_PANEL);
        CENTER_PANEL.add("Tìm kiếm và chỉnh sửa", SEARCH_EDIT_PANEL);
        CENTER_PANEL.add("Thống kê", DISPLAY_PANEL);

    }

    public pkaFrame(String name) {
        super(name);
        initial();
        changeField();

    }

    private void initial() {
        setLayout(null);
        setSize(1280, 852);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void changeField() {
        ActionListener act = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pkaFrame.centerCardLayout.show(pkaFrame.CENTER_PANEL, e.getActionCommand());
                pkaFrame.CENTER_PANEL.repaint();
            }

        };
        addEmp.addActionListener(act);
        editEmp.addActionListener(act);
        showEmp.addActionListener(act);
        buttonPanel.add(addEmp, editEmp, showEmp);
        add(buttonPanel);
        add(CENTER_PANEL);
    }

}