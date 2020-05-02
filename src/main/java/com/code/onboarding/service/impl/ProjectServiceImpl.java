package com.code.onboarding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.code.onboarding.dto.ProjectDTO;
import com.code.onboarding.entity.Project;
import com.code.onboarding.repository.ProjectRepository;
import com.code.onboarding.service.ProjectService;


@Service
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public Integer createProject(ProjectDTO project) {
		Project projectAfterCreation = projectRepository.save(new Project(project.getProjectAppliCode(), project.getProjectLongName(),
				project.getProjectShortName(), project.getUnitName()));
		return projectAfterCreation.getProjectId();
	}
	
	@Override
	public Project getProjectByAppliCode(String projectAppliCode) {
		Example<Project> project = Example.of((new Project()).from(projectAppliCode));
		return projectRepository.findOne(project).orElse(null);
	}

}
