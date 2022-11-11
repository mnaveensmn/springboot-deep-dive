package com.naveen.springbootdeepdive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naveen.springbootdeepdive.request.EmployeeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Setter
@Getter
@ToString
@Table(name = "tbl_employee")
@Entity
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be null")
    private String name;

    @JsonIgnore
    private Long age = 0L;

    private String location;

    @NotBlank(message = "Email should not be null")
    @Email(message = "Please provide a valid address")
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @JoinColumn(name = "department_id")
    @OneToOne
    private Department department;

    public Employee(EmployeeRequest employeeRequest) {
        this.setName(employeeRequest.getName());
        this.setEmail(employeeRequest.getEmail());
        this.setAge(employeeRequest.getAge());
        this.setLocation(employeeRequest.getLocation());
    }
}
