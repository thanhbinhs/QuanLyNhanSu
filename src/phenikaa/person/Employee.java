package phenikaa.person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public final class Employee {
    private String name, address, gender, department, position, phoneNumber;
    private int eid;
    private double salary, commission, allowance;
    private Date dob, joinTime;
    private Period workDuration;

    public Employee() {
    }

    public Employee(String name, String address, int eid, String gender, String department, String position,
            String phoneNumber, Date dob, Date joinTime,
            double salary, double commission, double allowance) throws phenikaa.exception.pkaException {

        // check xem eid da co trong list chua
        if (employeeList.employees.stream().anyMatch(x -> (x.getEid() == eid)))
            throw new phenikaa.exception.pkaException("Nhân viên này đã tồn tại!");
        this.name = name;
        this.address = address;
        this.eid = eid;
        this.gender = gender;
        this.department = department;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.joinTime = joinTime;
        this.workDuration = Period.between(LocalDate.now(),
                joinTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        this.salary = salary;
        this.commission = commission;
        this.allowance = allowance;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
    public void setJoinTime(Date date) {
        this.joinTime = date;
    }

    public void setDOB(Date dob) {
        this.dob = dob;
    }

    public void setWorkDuration(Date joinTime) {
        this.workDuration = Period.between(LocalDate.now(java.time.ZoneId.systemDefault()),
                joinTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
    }

    // getter
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getEid() {
        return eid;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public double getCommission() {
        return commission;
    }

    public double getAllowance() {
        return allowance;
    }

    public Date getDOB() {
        return dob;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Period getWorkDuration() {
        return workDuration;
    }


    // setter
    public void changeInfo(String name, String address, int eid, String gender, String department, String position,
            String phoneNumber, Date dob, Date joinTime,
            double salary, double commission, double allowance) throws phenikaa.exception.pkaException {
        if (employeeList.employees.stream().anyMatch(x -> (x.getEid() == eid) && (eid != this.eid))
        )
            throw new phenikaa.exception.pkaException("Nhân viên này đã tồn tại!");
        this.name = name;
        this.address = address;
        this.eid = eid;
        this.gender = gender;
        this.department = department;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.joinTime = joinTime;
        this.workDuration = Period.between(LocalDate.now(),
                joinTime.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        this.salary = salary;
        this.commission = commission;
        this.allowance = allowance;
    }

    @Override
    public String toString() {
        return "EID: " + eid + " | Tên: " + name + " | " + department;
    }

}
