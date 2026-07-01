package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String position;
    private String employmentType;

    public Employee(int employeeId, String name, String department, String position, String employmentType) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.employmentType = employmentType;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateSalary();

    public void saveEmployee() {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "INSERT INTO employee (employee_id, name, department, position, employment_type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            pstmt.setString(2, name);
            pstmt.setString(3, department);
            pstmt.setString(4, position);
            pstmt.setString(5, employmentType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding employee: " + e.getMessage());
        }
    }
}

