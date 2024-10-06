package com.example.demo.controller;

import com.example.demo.manager.EmployeeManager;
import com.example.demo.model.Employees;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeManager employeeManager;

    public EmployeeController() {
        employeeManager = new EmployeeManager();
    }

    @GetMapping("/employees")
    public Employees getEmployees() {
        return employeeManager.getAllEmployees();
    }
}
