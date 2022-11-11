package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployee(int pageNumber, int pageSize);

    Employee saveEmployee(EmployeeRequest employeeRequest);

    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Long id, EmployeeRequest employeeRequest);

    List<Employee> getEmployeeByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeeByKeyword(String keyword);

    List<Employee> getEmployeeByNameOrLocation(String name, String location);

    Integer deleteByEmployeeName(String name);

    List<Employee> getEmployeeByDepartmentName(String name);

}
