package com.employees.vo;

import com.employees.model.Employee;
import com.employees.model.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResponseVo {

    private Employee employee;
    private String projectDetails;
    private Set<Project> projects = new HashSet<>();
}
