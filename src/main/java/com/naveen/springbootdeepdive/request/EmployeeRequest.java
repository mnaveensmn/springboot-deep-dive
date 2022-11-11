package com.naveen.springbootdeepdive.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeRequest {
    private String name;
    private Long age = 0L;
    private String email;
    private String location;
    private String department;
    private List<String> communities;
}
