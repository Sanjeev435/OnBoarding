package com.code.onboarding.service;

import java.util.List;

import com.code.onboarding.dto.OnBoardingProcessDTO;

public interface OnBoardingProcessService {

	void saveOnBoardingProcesses(List<OnBoardingProcessDTO> onBoardingProcesses, int projectId);

}
