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

import com.code.onboarding.constants.ApplicationConstants;
import com.code.onboarding.dto.EmployeeDTO;
import com.code.onboarding.dto.NullAllowed;
import com.code.onboarding.exception.OnBoardingException.DataAlreadyExistException;
import com.code.onboarding.exception.OnBoardingException.InvalidProcessException;
import com.code.onboarding.exception.OnBoardingException.RedundantDataException;
import com.code.onboarding.security.Secured;
import com.code.onboarding.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(value = "employee")
@RequestMapping(path = "employee/")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @Secured
  @PostMapping("create")
  @ApiOperation(value = "Create Employee")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Employee Created with mentioned ID"),
      @ApiResponse(code = 226, message = "Employee already exist with provided email-id") })
  public ResponseEntity<Integer> createEmployee(
      @Validated(NullAllowed.class) @RequestBody EmployeeDTO employeeDetails) {
    try {
      Integer employeeId = employeeService.saveEmployee(employeeDetails);
      return new ResponseEntity<Integer>(employeeId, HttpStatus.CREATED);
    } catch (DataAlreadyExistException ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<Integer>(ApplicationConstants.ZERO, HttpStatus.IM_USED);
    }
  }

  @GetMapping("details")
  @ApiOperation(value = "Get Employee Details")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Employee Details") })
  public ResponseEntity<EmployeeDTO> getEmployeesDetails(
      @RequestParam(value = "emailId") String emailId) {
    return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeDetails(emailId),
        HttpStatus.OK);
  }

  @GetMapping("search")
  @ApiOperation(value = "Search Employee Details")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Search Employee Details "
      + "with given keyword") })
  public ResponseEntity<List<EmployeeDTO>> search(
      @RequestParam(value = "keyword") String keyword) {
    return new ResponseEntity<List<EmployeeDTO>>(employeeService.searchEmployees(keyword),
        HttpStatus.OK);
  }

  @GetMapping("initiateOnboarding")
  @ApiOperation(value = "Initiate onboarding process")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "OnBoarding process initiated"),
      @ApiResponse(code = 226, message = "OnBoarding process already initiated"),
      @ApiResponse(code = 400, message = "Data not valid for initiate onboarding")})
  public ResponseEntity<String> initiateOnBoardingProcess(
      @RequestParam(value = "initiatorEmail") String initiatorEmail,
      @RequestParam(value = "resourceEmail") String resourceEmail,
      @RequestParam(value = "projectAppliCode") String projectAppliCode) {
    try {
      String message = employeeService.initiateOnBoardingProcess(initiatorEmail,
          resourceEmail, projectAppliCode);
      return new ResponseEntity<String>(message, HttpStatus.OK);
    } catch (RedundantDataException ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.IM_USED);
    } catch (InvalidProcessException ex) {
      log.error(ex.getMessage(), ex);
      return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

}
