package com.naveen.springbootdeepdive.repository;

import com.naveen.springbootdeepdive.model.Department;
import com.naveen.springbootdeepdive.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
