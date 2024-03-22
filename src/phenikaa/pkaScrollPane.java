package phenikaa;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
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
        list.setSelectionBackground(new Color(0xEED3D9));
        list.setForeground(Color.BLACK);
        list.setBackground(pane.getBackground());
        list.setFont(pane.getFont());
        pane.setViewportView(list);
        pane.setBounds(x, y, w, h);
        pane.setBackground(new Color(0xf5e8dd));
        pane.setFont(new Font("Arial", Font.PLAIN, 14));
        pane.setForeground(Color.BLACK);
        return pane;
    }

    public static void update() {
        list.setModel(employeeList.getListModel());
    }

    public static void changeList(String type, String content) {
        if (type.equals("EID")) {
            list.setModel(employeeList.getListModelByEid(content));
        }
        if (type.equals("Tên"))
            list.setModel(employeeList.getListModelByName(content));
    }

    public static void filterList(String type) {
        list.setModel(employeeList.getListModelByDepartment(type));
    }

}
