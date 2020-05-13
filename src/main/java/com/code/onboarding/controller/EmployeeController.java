package com.code.onboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.onboarding.dto.EmployeeDTO;
import com.code.onboarding.dto.NullAllowed;
import com.code.onboarding.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "employee")
@RequestMapping(path = "employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("create")
    @ApiOperation(value = "Create Employee")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Employee Created with mentioned ID")})
    public ResponseEntity<Integer> createEmployee(
	    @Validated(NullAllowed.class) @RequestBody EmployeeDTO employeeDetails) {
	Integer employeeId = employeeService.saveEmployee(employeeDetails);
	return new ResponseEntity<Integer>(employeeId, HttpStatus.CREATED);
    }

    @GetMapping("details")
    @ApiOperation(value = "Get Employee Details")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Employee Details") })
    public ResponseEntity<EmployeeDTO> getEmployeesDetails(@RequestParam(value = "emailId") String emailId) {
	return new ResponseEntity<EmployeeDTO>(employeeService.
		getEmployeeDetails(emailId), HttpStatus.OK);
    }
    
    @GetMapping("search")
    @ApiOperation(value = "Get Employee Details")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Employee Details "
    	+ "with given keyword") })
    public ResponseEntity<List<EmployeeDTO>> search(@RequestParam(value = "keyword") String keyword) {
	return new ResponseEntity<List<EmployeeDTO>>(employeeService.
		searchEmployees(keyword), HttpStatus.OK);
    }

}
