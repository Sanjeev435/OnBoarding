package com.code.onboarding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.onboarding.dto.EmployeeDTO;
import com.code.onboarding.entity.Employee;
import com.code.onboarding.entity.EmployeePrjRef;
import com.code.onboarding.repository.EmployeeRepository;
import com.code.onboarding.service.EmployeeService;
import com.code.onboarding.service.ProjectService;
import com.code.onboarding.service.specifications.EmployeeSpecification;
import com.code.onboarding.service.specifications.SearchCriteria;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private ProjectService projectService;
  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(ProjectService projectService,
      EmployeeRepository employeeRepository) {
    this.projectService = projectService;
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Integer saveEmployee(EmployeeDTO employeeDetails) {
    Employee emp = new Employee();
    BeanUtils.copyProperties(employeeDetails, emp);

    List<EmployeePrjRef> associatedProjects = new ArrayList<>();
    employeeDetails.getProjectAppliCodes().forEach(e -> associatedProjects
        .add(new EmployeePrjRef(emp, projectService.getProjectEntityByAppliCode(e))));
    emp.setAssociatedProjects(associatedProjects);

    return employeeRepository.save(emp).getEmployeeId();
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

}
