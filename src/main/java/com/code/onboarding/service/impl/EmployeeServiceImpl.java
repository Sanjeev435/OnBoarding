package com.code.onboarding.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.onboarding.dto.EmployeeDTO;
import com.code.onboarding.entity.Employee;
import com.code.onboarding.repository.EmployeeRepository;
import com.code.onboarding.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository){
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public void saveEmployee(EmployeeDTO employeeDetails) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(employeeDetails, emp);
		
		employeeRepository.save(emp);
	}
	

}
