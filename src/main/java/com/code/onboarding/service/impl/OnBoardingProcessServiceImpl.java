package com.code.onboarding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.onboarding.dto.OnBoardingProcessDTO;
import com.code.onboarding.entity.OnBoardingProcess;
import com.code.onboarding.entity.Project;
import com.code.onboarding.repository.OnBoardingProcessRepository;
import com.code.onboarding.service.OnBoardingProcessService;

@Service
public class OnBoardingProcessServiceImpl implements OnBoardingProcessService {

	private OnBoardingProcessRepository onBoardingProcessRepository;

	@Autowired
	public OnBoardingProcessServiceImpl(OnBoardingProcessRepository onBoardingProcessRepository) {
		this.onBoardingProcessRepository = onBoardingProcessRepository;
	}

	@Override
	public void saveOnBoardingProcesses(List<OnBoardingProcessDTO> onBoardingProcesses, Project associatedProject) {
		List<OnBoardingProcess> processes = new ArrayList<>();
		onBoardingProcesses.forEach(process -> {
			OnBoardingProcess onBoardingProcess = new OnBoardingProcess();
			BeanUtils.copyProperties(process, onBoardingProcess);
			onBoardingProcess.setProject(associatedProject);
			processes.add(onBoardingProcess);
		});

		onBoardingProcessRepository.saveAll(processes);

	}

}
