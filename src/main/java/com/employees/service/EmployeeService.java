package com.employees.service;

import com.employees.model.Employee;
import com.employees.model.Project;
import com.employees.repository.EmployeeRepository;
import com.employees.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

    public Set<ResponseVo> findAllEmployees(){
        Set<ResponseVo> empDetails = new HashSet<>();
        Set<Project> projects = new HashSet<>();

        Set<Employee> employeeList = new HashSet<>(employeeRepository.findAll());

        employeeList.forEach(emp -> {
            String message = "Employee Not Mapped Any Project";
            ResponseVo responseVo = new ResponseVo();
            Project project;
            ResponseEntity<Integer[]> response =
                    restTemplate.getForEntity(
                            "http://Allocation-Service/api/allocation/projectIds/" + emp.getEmpId(),
                            Integer[].class);

            Integer[] projIds = response.getBody();
            if (projIds != null) {
                for (Integer id : projIds) {
                    project = restTemplate
                            .getForObject("http://Project-Service/api/project/" + id, Project.class);
                    projects.add(project);
                }
                responseVo.setProjects(projects);
                message = "Employee Mapped Project Details";
            }

            responseVo.setEmployee(emp);
            responseVo.setProjectDetails(message);
            empDetails.add(responseVo);
        });

        return empDetails;
    }

    public ResponseVo getEmployeeByID(Integer id){
        Set<Project> projects = new HashSet<>();
        ResponseVo responseVo = new ResponseVo();
        String message = "Employee Mapped Project Details";
        Employee employee = employeeRepository.getById(id);
        responseVo.setEmployee(employee);
        ResponseEntity<int[]> response =
            restTemplate.getForEntity("http://Allocation-Service/api/allocation/projectIds/"+id,int[].class);
        int[] projIds = response.getBody();
            if(projIds!=null){
                for(int ids: projIds){
                    Project project =
                        restTemplate.getForObject("http://Project-Service/api/project/"+ids,Project.class);
                    projects.add(project);
                }
            }else {
                message = "Employee Not Mapped Any Project";
            }
        responseVo.setProjectDetails(message);
        responseVo.setProjects(projects);

        return responseVo;
    }

    public Employee findEmployeeById(Integer id){
        return employeeRepository.getById(id);
    }
    public Employee updateEmployee(Employee emp){
        return employeeRepository.save(emp);
    }

    public Employee saveEmployee(Employee emp){
        return employeeRepository.save(emp);
    }

    public void deleteEmployeeById(Integer id){
        employeeRepository.deleteById(id);
    }

//    public ResponseEntity<int[]> getAllEmployeesId(){
//
//        ResponseEntity<int[]> responseEntity = null;
//        int[] ids;
//        List<Integer> listIds = employeeRepository.findAll().stream()
//                .map(Employee::getEmpId).collect(Collectors.toList());
//        if(!listIds.isEmpty()){
//            ids =   listIds.stream().mapToInt(Integer::intValue).toArray();
//            responseEntity = new ResponseEntity<>(ids, HttpStatus.ACCEPTED);
//        }
//
//        return responseEntity;
//    }

    public Set<Employee> getAllEmployees(){

       Set<Employee> employeeSet = new HashSet<>(employeeRepository.findAll());
       return employeeSet;
    }
}
