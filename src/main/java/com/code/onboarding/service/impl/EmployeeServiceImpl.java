package com.code.onboarding.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.onboarding.dto.EmployeeDTO;
import com.code.onboarding.entity.Comments;
import com.code.onboarding.entity.Employee;
import com.code.onboarding.entity.EmployeePrjRef;
import com.code.onboarding.entity.OnBoardingProgress;
import com.code.onboarding.entity.Project;
import com.code.onboarding.exception.OnBoardingException;
import com.code.onboarding.exception.OnBoardingException.InvalidProcessException;
import com.code.onboarding.exception.OnBoardingException.RedundantDataException;
import com.code.onboarding.repository.EmployeeRepository;
import com.code.onboarding.service.EmployeeService;
import com.code.onboarding.service.MailNotificationService;
import com.code.onboarding.service.ProjectService;
import com.code.onboarding.service.specifications.EmployeeSpecification;
import com.code.onboarding.service.specifications.SearchCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private ProjectService projectService;

  private EmployeeRepository employeeRepository;

  private MailNotificationService mailNotificationService;

  @Autowired
  public EmployeeServiceImpl(ProjectService projectService,
      EmployeeRepository employeeRepository,
      MailNotificationService mailNotificationService) {
    this.projectService = projectService;
    this.employeeRepository = employeeRepository;
    this.mailNotificationService = mailNotificationService;
  }

  @Override
  public Integer saveEmployee(EmployeeDTO employeeDetails) {
    Employee emp = getEmployee(employeeDetails.getEmailId());

    if (emp.isEmployeeExist()) {
      BeanUtils.copyProperties(employeeDetails, emp);

      List<EmployeePrjRef> associatedProjects = new ArrayList<>();
      employeeDetails.getProjectAppliCodes().forEach(e -> associatedProjects
          .add(new EmployeePrjRef(emp, projectService.getProjectEntityByAppliCode(e))));
      emp.setAssociatedProjects(associatedProjects);

      return employeeRepository.save(emp).getEmployeeId();
    } else {
      log.error("Employee already exist : " + employeeDetails.getEmailId());
      throw new OnBoardingException.DataAlreadyExistException("Employee already exist");
    }
  }

  @Override
  public EmployeeDTO getEmployeeDetails(String emailId) {
    EmployeeDTO emp = new EmployeeDTO();
    BeanUtils.copyProperties(getEmployee(emailId), emp);
    return emp;
  }

  @Override
  public List<EmployeeDTO> searchEmployees(String keyWord) {
    List<EmployeeDTO> employees = new ArrayList<>();
    employeeRepository
        .findAll(
            EmployeeSpecification.ComplexSpecifications.getEmployeesByKeyword(keyWord))
        .forEach(emp -> {
          EmployeeDTO dto = new EmployeeDTO();
          BeanUtils.copyProperties(emp, dto);
          emp.getAssociatedProjects().forEach(prj -> dto.getProjectAppliCodes()
              .add(prj.getProject().getProjectAppliCode()));
          employees.add(dto);
        });
    return employees;
  }

  private Employee getEmployee(String emailId) {
    return employeeRepository
        .findOne(new EmployeeSpecification(
            new SearchCriteria("emailId", SearchCriteria.OperationType.EQ, emailId)))
        .orElse(new Employee());
  }

  @Override
  public String initiateOnBoardingProcess(String initiatorEmail, String resourceEmail,
      String projectAppliCode) {

    Employee resourceEmployee = getEmployee(resourceEmail);
    Employee initiatorEmployee = getEmployee(initiatorEmail);
    Project project = projectService.getProjectEntityByAppliCode(projectAppliCode);

    if (resourceEmployee.isEmployeeExist() && initiatorEmployee.isEmployeeExist()
        && project.isProjectExist()) {
      if (isOnBoardingProcessInitiated(resourceEmail, projectAppliCode)) {
        project.getOnBoardingProcessess().forEach(process -> {
          OnBoardingProgress progress = new OnBoardingProgress();
          progress.setEmployee(resourceEmployee);
          progress.setOnBoardingProcess(process);
          progress.getUserComments().add(new Comments("Process initiated",
              LocalDateTime.now(), initiatorEmployee, null, progress));
          resourceEmployee.getOnBoardingProgress().add(progress);
        });

        employeeRepository.save(resourceEmployee);

        mailNotificationService.sendNotificationForOnboardingInitiation(
            Arrays.asList(resourceEmployee.getEmailId()));
      } else {
        log.error("Onboarding process in already initiated for "
            + resourceEmployee.getLName() + "," + resourceEmployee.getFName());
        throw new RedundantDataException(
            "Onboarding process in already initiated for " + resourceEmployee.getLName()
                + "," + resourceEmployee.getFName());
      }
    } else {
      log.error("Onboarding process can't be initiated for " + resourceEmployee.getLName()
          + "," + resourceEmployee.getFName());
      throw new InvalidProcessException(
          "Onboarding process can't be initiated for " + resourceEmployee.getLName() + ","
              + resourceEmployee.getFName());
    }
    return "Onboarding process created successfully for " + resourceEmployee.getLName()
        + "," + resourceEmployee.getFName();
  }

  private boolean isOnBoardingProcessInitiated(String emailId, String projectAppliCode) {
    Employee employee = getEmployee(emailId);
    if (employee.isEmployeeExist()
        && CollectionUtils.isNotEmpty(employee.getOnBoardingProgress())) {
      for (OnBoardingProgress progress : employee.getOnBoardingProgress()) {
        if (progress.getOnBoardingProcess().getProject().getProjectAppliCode()
            .equalsIgnoreCase(projectAppliCode)) {
          return true;
        }
      }
    }
    return false;
  }

}
