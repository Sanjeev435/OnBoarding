package com.code.onboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code.onboarding.dto.OnBoardingProcessDTO;
import com.code.onboarding.dto.ProjectDTO;
import com.code.onboarding.service.OnBoardingProcessService;
import com.code.onboarding.service.ProjectService;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "project/", consumes = "application/json" , produces = "application/json")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private OnBoardingProcessService onBoardingProcessService;
	
	
	
	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = {
            @ApiResponse(code = 406, message = ""),
            @ApiResponse(code = 404, message = ""),
            @ApiResponse(code = 201, message = "Project Id") })
	public ResponseEntity<Integer> createProject(@ApiParam ProjectDTO projectDetail) {
		Integer projectId = projectService.createProject(projectDetail);
		return new ResponseEntity<Integer>(projectId, HttpStatus.CREATED);
	}
	
	@PostMapping("/createOnBoardingProcess")
	@ResponseStatus(HttpStatus.CREATED)
	public void createOnBoardingProcess(List<OnBoardingProcessDTO> onBoardingProcesses, String projectAppliCode) {
		onBoardingProcessService.saveOnBoardingProcesses(onBoardingProcesses, 
				projectService.getProjectByAppliCode(projectAppliCode));
	}
	
	
	

}
