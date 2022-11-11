package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getEmployee(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return repository.findAll(pages).getContent();
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
    public Employee updateEmployee(Long id, Employee employee) {
        Employee empToUpdate = getEmployeeById(id);
        empToUpdate.setName(employee.getName() != null ? employee.getName() : empToUpdate.getName());
        empToUpdate.setAge(employee.getAge() != 0 ? employee.getAge() : empToUpdate.getAge());
        empToUpdate.setLocation(employee.getLocation() != null ? employee.getLocation() : empToUpdate.getLocation());
        empToUpdate.setDepartment(employee.getDepartment() != null ? employee.getDepartment() : empToUpdate.getDepartment());
        empToUpdate.setEmail(employee.getEmail() != null ? employee.getEmail() : empToUpdate.getEmail());
        return repository.save(employee);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return repository.findByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return repository.findByNameContaining(keyword, sort);
    }

    @Override
    public List<Employee> getEmployeeByNameOrLocation(String name, String location) {
        return repository.getEmployeeByNameAndLocation(name, location);
    }

    @Override
    public Integer deleteByEmployeeName(String name) {
        return repository.deleteEmployeeByName(name);
    }


}
