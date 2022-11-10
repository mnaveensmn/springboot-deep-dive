package com.naveen.springbootdeepdive.controller;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee empToUpdate = employeeService.getEmployeeById(id);
        empToUpdate.setName(employee.getName() != null ? employee.getName() : empToUpdate.getName());
        empToUpdate.setAge(employee.getAge() != 0 ? employee.getAge() : empToUpdate.getAge());
        empToUpdate.setLocation(employee.getLocation() != null ? employee.getLocation() : empToUpdate.getLocation());
        empToUpdate.setDepartment(employee.getDepartment() != null ? employee.getDepartment() : empToUpdate.getDepartment());
        empToUpdate.setEmail(employee.getEmail() != null ? employee.getEmail() : empToUpdate.getEmail());
        return employeeService.updateEmployee(empToUpdate);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return "Deleted the employee details for the id " + id;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
