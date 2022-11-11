package com.naveen.springbootdeepdive.service;

import com.naveen.springbootdeepdive.model.Department;
import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.repository.DepartmentRepository;
import com.naveen.springbootdeepdive.repository.EmployeeRepository;
import com.naveen.springbootdeepdive.request.EmployeeRequest;
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

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getEmployee(int pageNumber, int pageSize) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return repository.findAll(pages).getContent();
    }

    @Override
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest);
        employee.setDepartment(getDepartment(employeeRequest.getDepartment()));
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
    public Employee updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee empToUpdate = getEmployeeById(id);
        empToUpdate.setName(employeeRequest.getName() != null ? employeeRequest.getName() : empToUpdate.getName());
        empToUpdate.setAge(employeeRequest.getAge() != 0 ? employeeRequest.getAge() : empToUpdate.getAge());
        empToUpdate.setLocation(employeeRequest.getLocation() != null ? employeeRequest.getLocation() : empToUpdate.getLocation());
        empToUpdate.setDepartment(employeeRequest.getDepartment() != null ? getDepartment(employeeRequest.getDepartment()) : empToUpdate.getDepartment());
        empToUpdate.setEmail(employeeRequest.getEmail() != null ? employeeRequest.getEmail() : empToUpdate.getEmail());
        return repository.save(empToUpdate);
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

    public Department getDepartment(String departmentName) {
        Department existingDepartment = departmentRepository.findByName(departmentName);
        if (existingDepartment == null) {
            Department department = new Department();
            department.setName(departmentName);
            departmentRepository.save(department);
            return department;
        }
        return existingDepartment;
    }
}
