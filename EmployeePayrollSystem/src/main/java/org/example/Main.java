package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Employee Payroll System");

        while (true) {
            System.out.println("1. Add Employee");
            System.out.println("2. Process Payroll");
            System.out.println("3. Generate Payslip");
            System.out.println("4. Update Payroll Status");
            System.out.println("5. Exit");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addEmployee(scanner);
                        break;
                    case 2:
                        processPayroll(scanner);
                        break;
                    case 3:
                        generatePayslip(scanner);
                        break;
                    case 4:
                        updatePayrollStatus(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        try {
            System.out.println("Enter Employee ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Department:");
            String department = scanner.nextLine();
            System.out.println("Enter Position:");
            String position = scanner.nextLine();
            System.out.println("Enter Employment Type (1 for FullTime, 2 for PartTime):");
            int type = scanner.nextInt();

            if (type == 1) {
                System.out.println("Enter Base Salary:");
                double baseSalary = scanner.nextDouble();
                FullTimeEmployee employee = new FullTimeEmployee(id, name, department, position, baseSalary);
                employees.add(employee);
                employee.saveEmployee();
            } else if (type == 2) {
                System.out.println("Enter Hourly Rate:");
                double hourlyRate = scanner.nextDouble();
                PartTimeEmployee employee = new PartTimeEmployee(id, name, department, position, hourlyRate);
                employees.add(employee);
                employee.saveEmployee();
            } else {
                System.out.println("Invalid employment type. Returning to main menu.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please check your entries.");
            scanner.nextLine();
        }
    }

    private static void processPayroll(Scanner scanner) {
        try {
            System.out.println("Enter Payroll ID:");
            int payrollId = scanner.nextInt();
            System.out.println("Enter Employee ID:");
            int employeeId = scanner.nextInt();
            System.out.println("Enter Gross Salary:");
            double grossSalary = scanner.nextDouble();
            System.out.println("Enter Tax Deductions:");
            double taxDeductions = scanner.nextDouble();
            System.out.println("Enter Other Deductions:");
            double otherDeductions = scanner.nextDouble();
            Payroll payroll = new Payroll(payrollId, employeeId, null, null, grossSalary, taxDeductions, otherDeductions);
            payroll.savePayroll();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please check your entries.");
            scanner.nextLine();
        }
    }

    private static void generatePayslip(Scanner scanner) {
        try {
            System.out.println("Enter Payroll ID for payslip generation:");
            int payrollId = scanner.nextInt();
            Payroll payroll = Payroll.getPayrollById(payrollId);
            if (payroll != null) {
                payroll.displayPayslip();
            } else {
                System.out.println("Payroll record not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please check your entries.");
            scanner.nextLine();
        }
    }

    private static void updatePayrollStatus(Scanner scanner) {
        try {
            System.out.println("Enter Payroll ID to update status:");
            int payrollId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new status (e.g., Paid):");
            String newStatus = scanner.nextLine();
            Payroll payroll = Payroll.getPayrollById(payrollId);
            if (payroll != null) {
                payroll.updateStatus(newStatus);
            } else {
                System.out.println("Payroll record not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please check your entries.");
            scanner.nextLine();
        }
    }
}