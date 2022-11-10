package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);
}
