package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getEmployee() {
        return repository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        }
        throw new RuntimeException();
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            repository.delete(employeeOptional.get());
        } else {
            throw new RuntimeException("Could not find the employee for the id " + id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

}
