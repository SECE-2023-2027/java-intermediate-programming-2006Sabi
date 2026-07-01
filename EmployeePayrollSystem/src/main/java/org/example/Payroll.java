package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

class Payroll {
    private int payrollId;
    private int employeeId;
    private java.sql.Date payPeriodStart;
    private java.sql.Date payPeriodEnd;
    private double grossSalary;
    private double taxDeductions;
    private double otherDeductions;
    private double netSalary;
    private String status;

    public Payroll(int payrollId, int employeeId, java.sql.Date payPeriodStart, java.sql.Date payPeriodEnd, double grossSalary, double taxDeductions, double otherDeductions) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.grossSalary = grossSalary;
        this.taxDeductions = taxDeductions;
        this.otherDeductions = otherDeductions;
        calculateNetSalary();
        this.status = "Pending";
    }

    private void calculateNetSalary() {
        this.netSalary = grossSalary - taxDeductions - otherDeductions;
    }

    public void savePayroll() {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "INSERT INTO payroll (payroll_id, employee_id, pay_period_start, pay_period_end, gross_salary, tax_deductions, other_deductions, net_salary, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, payrollId);
            pstmt.setInt(2, employeeId);
            pstmt.setDate(3, payPeriodStart);
            pstmt.setDate(4, payPeriodEnd);
            pstmt.setDouble(5, grossSalary);
            pstmt.setDouble(6, taxDeductions);
            pstmt.setDouble(7, otherDeductions);
            pstmt.setDouble(8, netSalary);
            pstmt.setString(9, status);
            pstmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Employee ID not found in the database. Please ensure the employee is added before processing payroll.");
        } catch (SQLException e) {
            System.out.println("Error while saving payroll: " + e.getMessage());
        }
    }

    public void updateStatus(String newStatus) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "UPDATE payroll SET status = ? WHERE payroll_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, payrollId);
            pstmt.executeUpdate();
            this.status = newStatus; // Update the object's status as well
            System.out.println("Payroll status updated successfully to " + newStatus);
        } catch (SQLException e) {
            System.out.println("Error while updating payroll status: " + e.getMessage());
        }
    }

    public static Payroll getPayrollById(int payrollId) {
        Payroll payroll = null;
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT * FROM payroll WHERE payroll_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, payrollId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                java.sql.Date payPeriodStart = rs.getDate("pay_period_start");
                java.sql.Date payPeriodEnd = rs.getDate("pay_period_end");
                double grossSalary = rs.getDouble("gross_salary");
                double taxDeductions = rs.getDouble("tax_deductions");
                double otherDeductions = rs.getDouble("other_deductions");
                payroll = new Payroll(payrollId, employeeId, payPeriodStart, payPeriodEnd, grossSalary, taxDeductions, otherDeductions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }

    public void displayPayslip() {
        System.out.println("Payslip Details:");
        System.out.println("Payroll ID: " + payrollId);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Pay Period: " + payPeriodStart + " to " + payPeriodEnd);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Tax Deductions: " + taxDeductions);
        System.out.println("Other Deductions: " + otherDeductions);
        System.out.println("Net Salary: " + netSalary);
        System.out.println("Status: " + status);
    }
}

