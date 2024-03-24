// other fields and methods
package phenikaa;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import phenikaa.person.*;
import phenikaa.DBConfig;

public final class pkaPanel extends JPanel {

    public JTextField t_name, t_address;
    public JFormattedTextField tf_salary, tf_allowance, tf_commission, tf_phoneNumber;
    public JComboBox<String> cb_gender, cb_position, cb_department;
    public JSpinner js_dob, js_joinTime;
    public JScrollPane jl_scrollPane, jt_disTable;
    public static final pkaPanel ADD_PANEL, SEARCH_EDIT_PANEL, DISPLAY_PANEL, FUNCTION_PANEL;
    public static final String[] DEPARTMENT, POSITION, GENDER, EDIT_FILTER;
    static {
        DEPARTMENT = new String[] { "Phòng hành chính", "Phòng marketing", "Phòng nhân sự", "Phòng kế toán", "Phòng IT",
                "Phòng kinh doanh" };

        POSITION = new String[] { "Trưởng phòng", "Phó phòng", "Nhân viên", "Chuyên viên" };
        GENDER = new String[] { "Nam", "Nữ" };
        EDIT_FILTER = new String[] { "Tên", "EID" };

        ADD_PANEL = init_ADD_PANEL();
        SEARCH_EDIT_PANEL = init_SEARCH_EDIT_PANEL();
        DISPLAY_PANEL = init_DISPLAY_PANEL();
        FUNCTION_PANEL = init_FUNCTION_PANEL();

    }

    private pkaPanel(int x, int y, int w, int h) {
        super();
        setLayout(null);
        setBounds(x, y, w, h);
        setBackground(new Color(0xcccccc));
        setOpaque(true);
    }

    private static pkaPanel init_ADD_PANEL() {
        pkaPanel ADD_PANEL = new pkaPanel(0, 0, 1080, 832);

        // them cac nhan dan de lam title cho cac field trong ADD_PANEL them nhan vien
        ADD_PANEL.add(pkaLabel.creLabel("Tên: ", 129, 74, 442, 30), pkaLabel.creLabel("Địa chỉ: ", 129, 174, 417, 30),
                pkaLabel.creLabel("Ngày vào làm: ", 129, 274, 363, 30),
                pkaLabel.creLabel("Chức vụ: ", 129, 374, 298, 30),
                pkaLabel.creLabel("Phòng ban: ", 129, 474, 298, 30),
                pkaLabel.creLabel("Số điện thoại: ", 612, 74, 339, 30),
                pkaLabel.creLabel("Ngày sinh: ", 612, 174, 249, 30),
                pkaLabel.creLabel("Giới tính: ", 612, 274, 205, 30), pkaLabel.creLabel("Lương: ", 612, 374, 205, 30),
                pkaLabel.creLabel("Hoa hồng: ", 612, 474, 205, 30), pkaLabel.creLabel("Phụ cấp: ", 612, 574, 205, 30));

        // them cac JTextField voi muc dich nhan thong tin cua nhan vien bao gom: ho
        // ten, dia chi, eid
        ADD_PANEL.t_name = pkaTextField.getTextField(129, 104, 442, 40);
        ADD_PANEL.t_address = pkaTextField.getTextField(129, 204, 417, 40);

        // them cac JFormattedTextField de nhap thong tin cua nhan vien bao gom: sdt,
        // luong, hoa hong, phu cap
        ADD_PANEL.tf_phoneNumber = pkaTextField.getPhoneField(612, 104, 339, 40); // sdt
        ADD_PANEL.tf_salary = pkaTextField.getNumberField(612, 404, 205, 40); // luong
        ADD_PANEL.tf_commission = pkaTextField.getNumberField(612, 504, 205, 40); // hoa hong
        ADD_PANEL.tf_allowance = pkaTextField.getNumberField(612, 604, 205, 40); // phu cap

        // them cac JSpinner de nhap thong tin cua nhan vien bao gom: ngay thang nam
        // sinh, ngay vao lam
        ADD_PANEL.js_dob = pkaTextField.creDateChooser(612, 204, 249, 40);
        ADD_PANEL.js_joinTime = pkaTextField.creDateChooser(129, 304, 363, 40);

        // them cac JComboBox de nhap thong tin cho nhan vien bao gom: gioi tinh, phong
        // ban, chuc vu
        ADD_PANEL.cb_gender = pkaButton.creComboBox(GENDER, 612, 304, 205, 40); // gioi tinh
        ADD_PANEL.cb_department = pkaButton.creComboBox(DEPARTMENT, 129, 504, 298, 40);
        ADD_PANEL.cb_position = pkaButton.creComboBox(POSITION, 129, 404, 298, 40);

        // them button "Them nhan vien" de thuc hien viec them nhan vien vao danh sach
        pkaButton add = new pkaButton("Thêm", 612, 707, 100, 50);
        // them ActionListener cho button de them nhan vien
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // tao mot nhan vien moi voi thong tin da nhap
                // khi tao nhan vien moi, constructor cua Employee se kiem tra xem eid cua nhan
                // vien da
                // ton tai trong danh sach chua, neu eid da co trong danh sach thi se throw
                // phenikaa.exception.pkaException
                Employee newEmployee = new Employee(ADD_PANEL.t_name.getText(), ADD_PANEL.t_address.getText(),
                        (String) ADD_PANEL.cb_gender.getSelectedItem(),
                        (String) ADD_PANEL.cb_department.getSelectedItem(),
                        (String) ADD_PANEL.cb_position.getSelectedItem(), ADD_PANEL.tf_phoneNumber.getText(),
                        (Date) ADD_PANEL.js_dob.getValue(),
                        (Date) ADD_PANEL.js_joinTime.getValue(),
                        ((Number) ADD_PANEL.tf_salary.getValue()).doubleValue(),
                        ((Number) ADD_PANEL.tf_commission.getValue()).doubleValue(),
                        ((Number) ADD_PANEL.tf_allowance.getValue()).doubleValue());

                // neu nhu eid cua nhan vien chua co trong danh sach thi se them nhan vien vao
                // danh sach
                employeeList.employees.add(newEmployee);
                try {
                    addEmployee(DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS),
                            newEmployee);
                    System.out.println("Thêm nhân viên thành công!");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

                // thong bao them nhan vien thanh cong
                JOptionPane.showMessageDialog(pkaFrame.CENTER_PANEL, "Thêm nhân viên thành công!", "Thông báo ",
                        JOptionPane.INFORMATION_MESSAGE);

                // moi khi them nhan vien thi danh sach nhan vien se duoc update
                pkaScrollPane.update();
                pkaTable.update();
            }
        });

        // them cac component vao ADDADD_PANEL
        ADD_PANEL.add(ADD_PANEL.t_name, ADD_PANEL.t_address, ADD_PANEL.tf_phoneNumber,
                ADD_PANEL.tf_salary,
                ADD_PANEL.tf_commission, ADD_PANEL.tf_allowance, ADD_PANEL.js_dob, ADD_PANEL.js_joinTime,
                ADD_PANEL.cb_gender, ADD_PANEL.cb_department, ADD_PANEL.cb_position, add);

        return ADD_PANEL;
    }

    private static pkaPanel init_SEARCH_EDIT_PANEL() {

        pkaPanel EDIT_PANEL = new pkaPanel(200, 0, 1080, 832);
        pkaPanel EDIT_FIELD = init_EDIT_FIELD();

        // hien thi danh sach nhan vien qua JList
        EDIT_PANEL.add(pkaLabel.creLabel("Danh sách nhân viên: ", 58, 136, 276, 30));
        EDIT_PANEL.jl_scrollPane = pkaScrollPane.getScrollPane(58, 166, 320, 479);

        // thanh cong cu tim kiem de tim kiem nhan vien
        EDIT_PANEL.add(pkaLabel.creLabel("Tìm kiếm: ", 81, 56, 82, 33));
        JComboBox<String> cb_Type = pkaButton.creComboBox(EDIT_FILTER, 182, 56, 152, 33); // JComboBox de lua chon
                                                                                          // phuong phap tim kiem (tim
                                                                                          // theo ten hay eid)
        JTextField t_searchField = pkaTextField.getTextField(353, 56, 227, 33); // JTextField de dien ten / eid

        // cac button
        pkaButton search = new pkaButton("Tìm kiếm", 599, 56, 103, 33); // tim kiem dua tren cac filter
        pkaButton reset = new pkaButton("Khôi phục", 730, 56, 103, 33); // reset filter, hien thi danh sach tat ca nhan
        // vien

        // 2 button edit va delete de thuc hien viec chinh sua thong tin va xoa employee
        // duoc chon
        pkaButton delete = new pkaButton("Xóa người này", 216, 723, 80, 40);
        pkaButton edit = new pkaButton("Sửa thông tin", 96, 723, 80, 40);

        // dau tien jlist trong nen se khong hien thi cac button va editPanel
        delete.setVisible(false);
        edit.setVisible(false);
        EDIT_FIELD.setVisible(false);

        // them ActionListener cho cac button

        // seacrh
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // chung ta se can get kieu tim kiem (ten hay eid)
                String type = (String) cb_Type.getSelectedItem();

                // sau do se thay doi JList thanh danh sach tuong ung voi filter do
                pkaScrollPane.changeList(type, t_searchField.getText());
            }

        });

        // reset
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // dua JList ve trang thai hien thi toan bo danh sach nhan vien
                pkaScrollPane.update();
            }

        });

        // add Listener cho JList
        // khi co mot object trong JList duoc chon thi moi hien thi 2 button la edit va
        // delete
        pkaScrollPane.list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                // neu khong co object nao duoc chon -> an button edit va delete dong thoi an
                // luon EDIT_FIELD
                if (pkaScrollPane.list.isSelectionEmpty()) {
                    delete.setVisible(false);
                    edit.setVisible(false);
                    EDIT_FIELD.setVisible(false);
                    return;

                }

                // neu co object duoc chon thi se hien thi 2 button edit va delete
                delete.setVisible(true);
                edit.setVisible(true);
            }

        });

        // edit
        // khi an button edit thi EDIT_FIELD se hien ra de ta thuc hien viec chinh sua
        // thong tin
        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // dau tien ta se get object duoc chon va cap nhat EDIT_FIELD thanh thong tin
                // tuong ung
                EDIT_FIELD.setup((Employee) pkaScrollPane.list.getSelectedValue());

                // sau do hien thi EDIT_FIELD
                EDIT_FIELD.setVisible(true);

            }

        });

        // delete se xoa employee duoc chon
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dua ra thong bao de xac nhan xoa
                int confirm = JOptionPane.showConfirmDialog(pkaFrame.CENTER_PANEL, "Bạn có chắc chắn muốn xóa?",
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {// xoa employee duoc chon
                    try {
                        Connection conn = DriverManager.getConnection(DBConfig.DB_URL,
                                DBConfig.USER,
                                DBConfig.PASS);
                        deleteEmployee(conn, employeeList.employees.get(pkaScrollPane.list.getSelectedIndex()));
                        System.out.println("Xóa nhân viên thành công!");
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }

                    // xoa object ra khoi danh sach
                    employeeList.employees.remove((Employee) pkaScrollPane.list.getSelectedValue());
                    // sau khi xoa thi cap nhat lai JList
                    pkaScrollPane.update();
                    pkaTable.update();
                    JOptionPane.showMessageDialog(pkaFrame.CENTER_PANEL, "Xóa thành công!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // them button save de thuc hien luu thong tin cua nhan vien sau khi chinh sua
        pkaButton save = new pkaButton("Lưu", 387, 507, 80, 40);

        // them ActionListener cho button save
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // get object duoc chon trong JList, ep kieu thanh Employee va goi method
                // changeInfo cua
                // Employee de thuc hien viec cap nhat thong tin

                // neu eid cua nhan vien da ton tai thi se throw phenikaa.exception.pkaException

                ((Employee) pkaScrollPane.list.getSelectedValue()).changeInfo(EDIT_FIELD.t_name.getText(),
                        EDIT_FIELD.t_address.getText(),
                        (String) EDIT_FIELD.cb_gender.getSelectedItem(),
                        (String) EDIT_FIELD.cb_department.getSelectedItem(),
                        (String) EDIT_FIELD.cb_position.getSelectedItem(), EDIT_FIELD.tf_phoneNumber.getText(),
                        (Date) EDIT_FIELD.js_dob.getValue(),
                        (Date) EDIT_FIELD.js_joinTime.getValue(),
                        ((Number) EDIT_FIELD.tf_salary.getValue()).doubleValue(),
                        ((Number) EDIT_FIELD.tf_commission.getValue()).doubleValue(),
                        ((Number) EDIT_FIELD.tf_allowance.getValue()).doubleValue());

                try {
                    updateEmployee(DriverManager.getConnection(DBConfig.DB_URL,
                            DBConfig.USER, DBConfig.PASS),
                            employeeList.employees.get(pkaScrollPane.list.getSelectedIndex()));
                    System.out.println("Sửa nhân viên thành công!");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
                // thong bao cap nhat thong tin thanh cong
                JOptionPane.showMessageDialog(pkaFrame.CENTER_PANEL, "Cập nhật thông tin hoàn tất!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

                // cap nhat table
                pkaTable.update();

                // khi luu thong tin xong thi se kiem tra filter hien tai, neu khong co filter
                // thi se hien thi toan bo
                // danh sach nhan vien
                if (t_searchField.getText().equals("")) {
                    pkaScrollPane.update();
                    return;
                }
                // neu co filter thi se hien thi danh sach nhan vien tuong ung voi filter
                try {
                    String type = (String) cb_Type.getSelectedItem();
                    pkaScrollPane.changeList(type, t_searchField.getText());
                } catch (NumberFormatException parse) {
                    JOptionPane.showMessageDialog(pkaFrame.CENTER_PANEL, "Giá trị mà bạn nhập không hợp lệ!",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        });

        // them button save vao EDIT_FIELD
        EDIT_FIELD.add(save);

        // add cac thanh phan vao EDIT_PANEL
        EDIT_PANEL.add(EDIT_PANEL.jl_scrollPane, delete, edit, EDIT_FIELD, cb_Type, t_searchField, search, reset);
        return EDIT_PANEL;
    }

    private static pkaPanel init_EDIT_FIELD() {
        pkaPanel EDIT_FIELD = new pkaPanel(401, 168, 658, 547);

        // them label vao EDIT_FIELD
        EDIT_FIELD.add(pkaLabel.creLabel("Tên: ", 0, 0, 354, 24), pkaLabel.creLabel("Địa chỉ: ", 0, 80, 334, 24),
                pkaLabel.creLabel("Ngày vào làm: ", 0, 160, 291, 24), pkaLabel.creLabel("Chức vụ: ", 0, 240, 239, 24),
                pkaLabel.creLabel("Phòng ban: ", 0, 320, 239, 24),
                pkaLabel.creLabel("Số điện thoại: ", 387, 0, 271, 24),
                pkaLabel.creLabel("Ngày sinh: ", 387, 80, 200, 24),
                pkaLabel.creLabel("Giới tính: ", 387, 160, 164, 24), pkaLabel.creLabel("Lương: ", 387, 240, 164, 24),
                pkaLabel.creLabel("Hoa hồng: ", 387, 320, 164, 24), pkaLabel.creLabel("Phụ cấp: ", 387, 400, 164, 24));

        // setup cac field de dien thong tin
        EDIT_FIELD.t_name = pkaTextField.getTextField(0, 24, 354, 32);
        EDIT_FIELD.t_address = pkaTextField.getTextField(0, 104, 334, 32);

        EDIT_FIELD.tf_phoneNumber = pkaTextField.getPhoneField(387, 24, 271, 32);
        EDIT_FIELD.tf_salary = pkaTextField.getNumberField(387, 264, 164, 32);
        EDIT_FIELD.tf_commission = pkaTextField.getNumberField(387, 344, 164, 32);
        EDIT_FIELD.tf_allowance = pkaTextField.getNumberField(387, 424, 164, 32);

        EDIT_FIELD.js_dob = pkaTextField.creDateChooser(387, 104, 200, 32);
        EDIT_FIELD.js_joinTime = pkaTextField.creDateChooser(0, 184, 291, 32);
        EDIT_FIELD.cb_gender = pkaButton.creComboBox(GENDER, 387, 184, 164, 32);
        EDIT_FIELD.cb_department = pkaButton.creComboBox(DEPARTMENT, 0, 344, 239, 32);
        EDIT_FIELD.cb_position = pkaButton.creComboBox(POSITION, 0, 264, 239, 32);

        // them cac component vua tao vao EDIT_FIELD
        EDIT_FIELD.add(EDIT_FIELD.t_name, EDIT_FIELD.t_address, EDIT_FIELD.tf_phoneNumber,
                EDIT_FIELD.tf_salary,
                EDIT_FIELD.tf_commission, EDIT_FIELD.tf_allowance, EDIT_FIELD.js_dob, EDIT_FIELD.js_joinTime,
                EDIT_FIELD.cb_gender, EDIT_FIELD.cb_department, EDIT_FIELD.cb_position);

        return EDIT_FIELD;
    }

    private void setup(Employee emp) {

        this.t_name.setText(emp.getName());
        this.t_address.setText(emp.getAddress());

        this.tf_phoneNumber.setText(emp.getPhoneNumber());
        this.tf_salary.setValue((Object) emp.getSalary());
        this.tf_commission.setValue((Object) emp.getCommission());
        this.tf_allowance.setValue((Object) emp.getAllowance());

        this.cb_department.setSelectedItem((Object) emp.getDepartment());
        this.cb_position.setSelectedItem((Object) emp.getPosition());
        this.cb_gender.setSelectedItem((Object) emp.getGender());

        this.js_dob.setValue((Object) emp.getDOB());
        this.js_joinTime.setValue((Object) emp.getJoinTime());

    }

    private static pkaPanel init_DISPLAY_PANEL() {
        pkaPanel DISPLAY_PANEL = new pkaPanel(200, 0, 1080, 832);

        // filter setup
        DISPLAY_PANEL.add(pkaLabel.creLabel("Bộ lọc: ", 38, 33, 50, 40));
        JComboBox<String> dis_filter = pkaButton.creComboBox(DEPARTMENT, 100, 33, 215, 40);
        JButton filter_button = new pkaButton("Lọc", 350, 33, 100, 40);
        JButton reset_button = new pkaButton("Gỡ bộ lọc", 470, 33, 100, 40);

        // them hanh dong cho bo loc

        filter_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get department va cap nhat lai table
                String department_ft = (String) dis_filter.getSelectedItem();
                pkaTable.filter(department_ft);
            }

        });

        reset_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // reset filter
                pkaTable.filter("Tất cả");
            }

        });

        DISPLAY_PANEL.jt_disTable = pkaTable.SCROLL_TABLE;
        DISPLAY_PANEL.add(DISPLAY_PANEL.jt_disTable, dis_filter, filter_button, reset_button);
        return DISPLAY_PANEL;
    }

    private static pkaPanel init_FUNCTION_PANEL() {
        pkaPanel FUNCTION_PANEL = new pkaPanel(0, 0, 200, 832);
        pkaButton addEmp = new pkaButton("Thêm nhân viên", 37, 52, 150, 50);
        pkaButton editEmp = new pkaButton("Tìm kiếm và chỉnh sửa", 37, 137, 150, 50);
        pkaButton showEmp = new pkaButton("Thống kê", 37, 224, 150, 50);
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
        FUNCTION_PANEL.add(addEmp, editEmp, showEmp);
        return FUNCTION_PANEL;
    }

    public static pkaPanel getInstance(int x, int y, int w, int h) {
        return new pkaPanel(x, y, w, h);
    }

    public static void addEmployee(Connection conn, Employee employee) {
        try {
            String sql = "INSERT INTO employee (employee_id, name, date_of_birth, gender, address, phone_number, salary, join_date, department, position, commission, allowance, work_duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            // Set các giá trị cho các cột trong câu lệnh SQL từ đối tượng Employee
            statement.setInt(1, employee.getEid()); // Lấy mã nhân viên
            statement.setString(2, employee.getName()); // Lấy tên
            statement.setDate(3, java.sql.Date.valueOf(
                employee.getDOB().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));  // Lấy ngày sinh
            statement.setString(4, employee.getGender()); // Lấy giới tính
            statement.setString(5, employee.getAddress()); // Lấy địa chỉ
            statement.setString(6, employee.getPhoneNumber()); // Lấy số điện thoại
            statement.setBigDecimal(7, BigDecimal.valueOf(employee.getSalary())); // Lấy lương
            statement.setDate(8, java.sql.Date.valueOf(
                employee.getJoinTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));                                                                                // gia
            statement.setString(9, employee.getDepartment()); // Lấy phòng ban
            statement.setString(10, employee.getPosition()); // Lấy id vị trí
            statement.setBigDecimal(11, BigDecimal.valueOf(employee.getCommission())); // Lấy hoa hồng
            statement.setBigDecimal(12, BigDecimal.valueOf(employee.getAllowance()));// Lấy phụ cấp
            statement.setDate(13, java.sql.Date.valueOf(LocalDate.now().minusYears(
                employee.getJoinTime().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getYear()))); // Lấy thời gian làm việc
            // Thực hiện câu lệnh SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployee(Connection conn, Employee employee) {
        try {
            String sql = "UPDATE employee SET name=?, date_of_birth=?, gender=?, address=?, phone_number=?, salary=?, join_date=?, department=?, position=?, commission=?, allowance=?, work_duration=? WHERE employee_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            java.util.Date dob = employee.getDOB();
            java.util.Date joinTime = employee.getJoinTime();

            // Set các giá trị cho các cột trong câu lệnh SQL từ đối tượng Employee
            // Lấy mã nhân viên
            statement.setString(1, employee.getName()); // Lấy tên
            statement.setDate(2, new java.sql.Date(dob.getTime())); // Lấy ngày sinh
            statement.setString(3, employee.getGender()); // Lấy giới tính
            statement.setString(4, employee.getAddress()); // Lấy địa chỉ
            statement.setString(5, employee.getPhoneNumber()); // Lấy số điện thoại
            statement.setBigDecimal(6, BigDecimal.valueOf(employee.getSalary())); // Lấy lương
            statement.setDate(7, new java.sql.Date(joinTime.getTime())); // Lấy ngày tham gia
            statement.setString(8, employee.getDepartment()); // Lấy phòng ban
            statement.setString(9, employee.getPosition()); // Lấy id vị trí
            statement.setBigDecimal(10, BigDecimal.valueOf(employee.getCommission())); // Lấy hoa hồng
            statement.setBigDecimal(11, BigDecimal.valueOf(employee.getAllowance()));// Lấy phụ cấp
            Calendar cal = Calendar.getInstance();
            cal.setTime(employee.getJoinTime());
            int joinYear = cal.get(Calendar.YEAR);
            statement.setDate(12, java.sql.Date.valueOf(LocalDate.now().minusYears(joinYear)));
            // Lấy thời gian làm việc
            statement.setInt(13, employee.getEid());
            // Thực hiện câu lệnh SQL
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(Connection conn, Employee employee) {
        try {
            String sql = "DELETE FROM employee WHERE employee_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, employee.getEid());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Component c, Component... c1) {
        super.add(c);
        for (Component c2 : c1) {
            super.add(c2);
        }
    }
}
