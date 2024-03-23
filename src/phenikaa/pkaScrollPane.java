package phenikaa;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import phenikaa.person.Employee;
import phenikaa.person.employeeList;

public class pkaScrollPane {
    public static JList<Employee> list;
    static {
        list = new JList<>(employeeList.getListModel());
    }

    private pkaScrollPane() {
    }

    public static JScrollPane getScrollPane(int x, int y, int w, int h) {
        JScrollPane pane = new JScrollPane();
        pane.setBounds(x, y, w, h);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionBackground(new Color(0xa5a5a5));
        list.setForeground(Color.BLACK);
        list.setBackground(pane.getBackground());
        list.setFont(pane.getFont());
        pane.setViewportView(list);
        pane.setBounds(x, y, w, h);
        pane.setBackground(new Color(0xf2f2f2));
        pane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        pane.setForeground(Color.BLACK);
        return pane;
    }

    public static void update() {
        list.setModel(employeeList.getListModel());
    }

    public static void changeList(String type, String content) {
        try {
            if (type.equals("EID")) {
                list.setModel(employeeList.getListModelByEid(Integer.parseInt(content)));
            }
        } catch (NumberFormatException parse) {
            JOptionPane.showMessageDialog(pkaFrame.CENTER_PANEL, "Giá trị mà bạn nhập không hợp lệ!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        if (type.equals("Tên"))
            list.setModel(employeeList.getListModelByName(content));
    }

}
