package com.code.onboarding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.code.onboarding.dto.ProjectDTO;
import com.code.onboarding.entity.Project;
import com.code.onboarding.repository.ProjectRepository;
import com.code.onboarding.service.ProjectService;
import com.code.onboarding.service.specifications.ProjectSpecification;

@Service
public class ProjectServiceImpl implements ProjectService {

  private ProjectRepository projectRepository;

  @Autowired
  public ProjectServiceImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public Integer createProject(ProjectDTO project) {
    Project projectAfterCreation = projectRepository
        .save(new Project(project.getProjectAppliCode(), project.getProjectLongName(),
            project.getProjectShortName(), project.getUnitName()));
    return projectAfterCreation.getProjectId();
  }

  @Override
  public ProjectDTO getProjectDetailsByAppliCode(String projectAppliCode) {
    ProjectDTO projectDeatils = new ProjectDTO();
    BeanUtils.copyProperties(getProjectByAppliCode(projectAppliCode), projectDeatils);

    return projectDeatils;
  }

  @Override
  public Project getProjectEntityByAppliCode(String projectAppliCode) {
    return getProjectByAppliCode(projectAppliCode);
  }

  @Override
  public int getProjectIdByAppliCode(String projectAppliCode) {
    return getProjectByAppliCode(projectAppliCode).getProjectId();
  }

  @Override
  public List<ProjectDTO> searchEmployees(String keyWord) {
    List<ProjectDTO> projects = new ArrayList<>();
    projectRepository
        .findAll(ProjectSpecification.ComplexSpecifications.getProjectByKeyword(keyWord))
        .forEach(prj -> {
          ProjectDTO dto = new ProjectDTO();
          BeanUtils.copyProperties(prj, dto);
          projects.add(dto);
        });
    return projects;
  }

  private Project getProjectByAppliCode(String projectAppliCode) {
    ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("projectAppliCode",
        GenericPropertyMatchers.exact());

    Example<Project> project = Example
        .of((new Project(projectAppliCode, null, null, null)), matcher);
    return projectRepository.findOne(project).orElse(new Project());
  }

}
