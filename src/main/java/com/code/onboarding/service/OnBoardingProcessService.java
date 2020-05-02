package com.code.onboarding.service;

import java.util.List;

import com.code.onboarding.dto.OnBoardingProcessDTO;
import com.code.onboarding.entity.Project;

public interface OnBoardingProcessService {

	void saveOnBoardingProcesses(List<OnBoardingProcessDTO> onBoardingProcesses, Project associatedProject);

}
