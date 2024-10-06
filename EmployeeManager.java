package com.example.demo.manager;

import com.example.demo.model.Employee;
import com.example.demo.model.Employees;

public class EmployeeManager {

    private Employees employees;

    public EmployeeManager() {
        employees = new Employees();
        employees.getEmployeeList().add(new Employee(1, "John", "Doe", "john.doe@example.com", "Manager"));
        employees.getEmployeeList().add(new Employee(2, "Jane", "Smith", "jane.smith@example.com", "Developer"));
        employees.getEmployeeList().add(new Employee(3, "Robert", "Brown", "robert.brown@example.com", "Designer"));
    }

    public Employees getAllEmployees() {
        return employees;
    }
}
