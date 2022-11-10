package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getEmployee() {
        return repository.findAll();
    }

}
