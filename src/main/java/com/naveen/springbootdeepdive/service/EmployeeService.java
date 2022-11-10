package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployee();

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Long id, Employee employee);

    List<Employee> getEmployeeByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeeByKeyword(String keyword);

}
