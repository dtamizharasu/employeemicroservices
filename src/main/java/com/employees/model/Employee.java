package com.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // added for removing hibernateLazyInitializer {} response
public class Employee {

    @Id
    @GeneratedValue
    private Integer empId;
    private String empName;
    private String designation;
    private String emailId;
    private String mobileNum;
    private String availability;
}
