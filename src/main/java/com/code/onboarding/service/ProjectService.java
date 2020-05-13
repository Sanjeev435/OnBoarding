package com.code.onboarding.service;

import java.util.List;

import com.code.onboarding.dto.ProjectDTO;
import com.code.onboarding.entity.Project;

public interface ProjectService {

	Integer createProject(ProjectDTO project);
	
	int getProjectIdByAppliCode(String projectAppliCode);
	
	ProjectDTO getProjectDetailsByAppliCode(String projectAppliCode);

	Project getProjectEntityByAppliCode(String projectAppliCode);

	List<ProjectDTO> searchEmployees(String keyWord);
	
	

}
