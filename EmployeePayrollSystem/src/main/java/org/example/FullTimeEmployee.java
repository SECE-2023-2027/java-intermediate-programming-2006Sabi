package org.example;

class FullTimeEmployee extends Employee {
    private double baseSalary;

    public FullTimeEmployee(int employeeId, String name, String department, String position, double baseSalary) {
        super(employeeId, name, department, position, "FullTime");
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        return baseSalary;
    }
}

