import javax.swing.SwingUtilities;
import javax.swing.plaf.nimbus.State;

import phenikaa.person.Employee;
import phenikaa.person.employeeList;
import phenikaa.DBConfig;
import phenikaa.*;

import java.math.BigDecimal;
import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;


public class Main {


    public static void main(String[] args) {

        try(Connection con = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
        Statement stmt = con.createStatement();) {
        stmt.execute("USE employee");
        System.out.println("Connected to database");
        try {
        String sql = "SELECT * FROM employee";

        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
        Employee employee = new Employee();
        employee.setEid(resultSet.getInt("employee_id"));
        employee.setName(resultSet.getString("name"));
        employee.setDOB(resultSet.getDate("date_of_birth"));
        employee.setGender(resultSet.getString("gender"));
        employee.setAddress(resultSet.getString("address"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setJoinTime(resultSet.getDate("join_date"));
        employee.setDepartment(resultSet.getString("department"));
        employee.setPosition(resultSet.getString("position"));
        employee.setCommission(resultSet.getDouble("commission"));
        employee.setAllowance(resultSet.getDouble("allowance"));
        employeeList.employees.add(employee);
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new pkaFrame("Phenikaa - Employee Management Application");
            }
        });

        con.close();
        }catch(Exception e){
        System.out.println(e);
        }

    }

}