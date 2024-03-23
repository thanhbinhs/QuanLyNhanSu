package phenikaa.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

public interface employeeList {
    public static final ArrayList<Employee> employees = new ArrayList<>();

    // getter

    public static DefaultListModel<Employee> getListModel() {
        DefaultListModel<Employee> model = new DefaultListModel<>();
        for (Employee employee : employeeList.employees) {
            model.addElement(employee);
        }
        return model;
    }

    public static DefaultListModel<Employee> getListModelByName(String name) {
        ArrayList<Employee> list = employeeList.employees.stream().filter(x -> name.equals(x.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
        DefaultListModel<Employee> result = new DefaultListModel<>();
        for (Employee emp : list) {
            result.addElement(emp);
        }
        return result;
    }

    public static DefaultListModel<Employee> getListModelByEid(int eid) {
        Employee eidSearch = employeeList.employees.stream().filter(x -> eid == x.getEid()).findAny()
                .orElse(null);
        DefaultListModel<Employee> result = new DefaultListModel<>();
        result.addElement(eidSearch);
        return result;
    }

}
