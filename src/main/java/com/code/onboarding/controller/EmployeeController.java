package com.code.onboarding.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.onboarding.dto.EmployeeDTO;
import com.code.onboarding.service.EmployeeService;

@RestController
@RequestMapping(path = "employee/")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@PostMapping("create")
	public void createEmployee(EmployeeDTO employeeDetails) {
		employeeService.saveEmployee(employeeDetails);
	}

}
