package com.code.onboarding.service;

import com.code.onboarding.dto.ProjectDTO;
import com.code.onboarding.entity.Project;

public interface ProjectService {

	Integer createProject(ProjectDTO project);

	Project getProjectByAppliCode(String projectAppliCode);

}
