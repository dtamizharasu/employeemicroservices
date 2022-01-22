package com.employees.controller;

import com.employees.model.Employee;
import com.employees.service.EmployeeService;
import com.employees.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
* Employee Microservices APIs
* 1. Get All Employees Details
* 2. Get Employee with Project details By Id
* 3. Get the Employee Details
* 4. Add New Employee
* 5. Update Employee Details
* 6. Delete Employee Details
* */

// Swagger URL - http://localhost:8081/swagger-ui/

@Api(value = "Employee", tags = {"Employee"})
@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService service;

    /**
    * Get All the Employees Details
    * @return - Return List Of Employees
    */

    @ApiOperation(value = "Get All Employees with Project Details",
            response = ResponseVo.class, tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/allemployees")
    public Set<ResponseVo> getAllEmployees(){
        return service.findAllEmployees();
    }

    /**
     * Get Employee Record BY Id
     * @param id - ID for the Employee
     * @return - Employee
     */
    @ApiOperation(value = "Get Single Employees with Project Details",
            response = ResponseVo.class, tags = "Employee")
    @GetMapping("/employees/{id}")
    public ResponseVo getEmployeeById(@PathVariable Integer id){
        return service.getEmployeeByID(id);
    }

    /**
     * Get Employee Record BY Id without Project
     * @param id - ID for the Employee
     * @return - Employee
     */
    @ApiOperation(value = "Get Employee Detail",
            response = Employee.class, tags = "Employee")
    @GetMapping("/{id}")
    public Employee findByEmployeeId(@PathVariable Integer id){
        log.info("Get the Employee Details Without Project Details"+id);
        return service.findEmployeeById(id);
    }

    /**
     * @param employee - Employee Records
     * @return - Saved Employee Record
     */
    @ApiOperation(value = "Add New Employee Record", response = Employee.class, tags = "Employee")
    @PostMapping("/saveemployee")
    public Employee saveEmployee(@RequestBody Employee employee){
        log.info("Save New Employee Details "+employee.toString());
        return service.saveEmployee(employee);
    }

    /**
     * @param employee - Employee Records
     * @return - Updated Employee Record
     */

    @ApiOperation(value = "Update Employee Record", response = Employee.class, tags = "Employee")
    @PutMapping("/updateemployee")
    public Employee updateEmployee(@RequestBody Employee employee){
        return service.updateEmployee(employee);
    }

    /**
     * @param id - Employee Id
     * @return - Deleted Message
     */
    @ApiOperation(value = "Delete Employee Record", response = String.class, tags = "Employee")
    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable Integer id){
        service.deleteEmployeeById(id);
        return "Employee Details Deleted";
    }

//    /**
//     * Get Employee Record without Project
//     * @return - All Employee Record Without Project
//     */
//    @ApiOperation(value = "Get All Employee Details",
//            response = Employee.class, tags = "Employee")
//    @GetMapping("/get/all/employees")
//    public int[] findAllEmployees(){
//        log.info("Get All the Employee Details Record Without Project Details");
//        return service.getAllEmployeesId().getBody();
//    }

    /**
     * Get Employee Record without Project
     * @return - All Employee Record Without Project
     */
    @ApiOperation(value = "Get All Employee Details",
            response = Employee.class, tags = "Employee")
    @GetMapping("/get/all/employees")
    public Set<Employee> findAllEmployeesDetails(){
        log.info("Get All the Employee Details Record Without Project Details");
        return service.getAllEmployees();
    }
}
