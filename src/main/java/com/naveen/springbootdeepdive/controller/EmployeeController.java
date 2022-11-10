package com.naveen.springbootdeepdive.controller;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppDetails() {
        return appName + " - " + appVersion;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployee();
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable("id") Long id) {
        return "Fetching the employee details for the id " + id;
    }

    @PostMapping("/employees")
    public String saveEmployee(@RequestBody Employee employee) {
        return "Saving the employee to the database";
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employee;
    }

    @DeleteMapping("/employees")
    public String deleteEmployee(@RequestParam("id") Long id) {
        return "Delete the employee details for the id" + id;
    }
}
