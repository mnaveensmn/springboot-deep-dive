package com.naveen.springbootdeepdive.controller;

import com.naveen.springbootdeepdive.model.Employee;
import com.naveen.springbootdeepdive.request.EmployeeRequest;
import com.naveen.springbootdeepdive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return new ResponseEntity<>(employeeService.getEmployee(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeRequest), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String name) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeeByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeeByKeyword(@RequestParam String keyword) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/employees/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeeByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByNameOrLocation(name, location), HttpStatus.OK);
    }

    @DeleteMapping("/employees/delete/{name}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.deleteByEmployeeName(name) + "No of records deleted", HttpStatus.OK);
    }

    @GetMapping("/employees/department/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartmentName(@PathVariable String name) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByDepartmentName(name), HttpStatus.OK);
    }


}
