package com.code.onboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.onboarding.dto.OnBoardingProcessDTO;
import com.code.onboarding.dto.ProjectDTO;
import com.code.onboarding.service.OnBoardingProcessService;
import com.code.onboarding.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "project")
@RequestMapping(path = "project/")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private OnBoardingProcessService onBoardingProcessService;

	@GetMapping("getDetails/{projectAppliCode}")
	@ApiOperation(value = "Get Project Details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Project Details") })
	public ResponseEntity<ProjectDTO> getProject(@PathVariable("projectAppliCode") String projectAppliCode) {
		return new ResponseEntity<ProjectDTO>(projectService.getProjectDetailsByAppliCode(projectAppliCode),
				HttpStatus.OK);
	}

	@PostMapping("create")
	@ApiOperation(value = "Create Project")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "projectId") })
	public ResponseEntity<Integer> createProject(@RequestBody ProjectDTO projectDetail) {
		Integer projectId = projectService.createProject(projectDetail);
		return new ResponseEntity<Integer>(projectId, HttpStatus.CREATED);
	}

	@PostMapping("createOnBoardingProcess")
	@ApiOperation(value = "Associate OnBoarding Process With Project")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OnBoarding Process Created") })
	public ResponseEntity<Void> createOnBoardingProcess(@RequestBody List<OnBoardingProcessDTO> onBoardingProcesses,
			@RequestBody String projectAppliCode) {
		onBoardingProcessService.saveOnBoardingProcesses(onBoardingProcesses,
				projectService.getProjectIdByAppliCode(projectAppliCode));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
