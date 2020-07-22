package com.code.onboarding.service;

import java.util.List;

import com.code.onboarding.dto.EmployeeDTO;

public interface EmployeeService {

  Integer saveEmployee(EmployeeDTO employeeDetails);

  EmployeeDTO getEmployeeDetails(String emailId);

  List<EmployeeDTO> searchEmployees(String keyWord);

  String initiateOnBoardingProcess(String initiatorEmail, String resourceEmail,
      String projectAppliCode);

}
