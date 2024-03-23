package phenikaa;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import phenikaa.person.*;

public final class pkaTable {
    public static final DefaultTableModel TABLE_MODEL;
    public static final JTable DISPLAY_TABLE;
    public static final JScrollPane SCROLL_TABLE;
    static {
        TABLE_MODEL = init_TABLE_MODEL();
        DISPLAY_TABLE = init_DISPLAY_TABLE();
        SCROLL_TABLE = init_SCROLL_TABLE(DISPLAY_TABLE);
    }

    private pkaTable() {

    }

    private static JScrollPane init_SCROLL_TABLE(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(38, 127, 1004, 659);
        scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        scrollPane.setBackground(Color.WHITE);
        return scrollPane;
    }

    private static JTable init_DISPLAY_TABLE() {
        JTable newTable = new JTable(TABLE_MODEL);
        newTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        newTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        return newTable;
    }

    public static void filter(String filter) {
        if (filter.equals("Tất cả")) {
            update();
            return;
        }
        DISPLAY_TABLE.setModel(get_TableModel_by_Department(filter));
    }

    private static DefaultTableModel init_TABLE_MODEL() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("EID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Phòng ban");
        tableModel.addColumn("Chức vụ");
        tableModel.addColumn("Số điện thoại");
        for (Employee emp : employeeList.employees) {
            tableModel.addRow(new Object[] { emp.getEid(), emp.getName(), emp.getAddress(), emp.getDepartment(),
                    emp.getPosition(), emp.getPhoneNumber() });
        }
        return tableModel;
    }

    public static DefaultTableModel get_TableModel_by_Department(String department) {
        ArrayList<Employee> list = employeeList.employees.stream().filter(x -> x.getDepartment().equals(department))
                .collect(Collectors.toCollection(ArrayList::new));
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("EID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Phòng ban");
        tableModel.addColumn("Chức vụ");
        tableModel.addColumn("Số điện thoại");
        for (Employee emp : list) {
            tableModel.addRow(new Object[] { emp.getEid(), emp.getName(), emp.getAddress(), emp.getDepartment(),
                    emp.getPosition(), emp.getPhoneNumber() });
        }
        return tableModel;
    }

    public static void update() {
        DISPLAY_TABLE.setModel(init_TABLE_MODEL());
    }
}
