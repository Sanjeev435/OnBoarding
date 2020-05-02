package com.code.onboarding.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	private String fName;
	private String lName;
	private String emailId;
	private String subGroup;
	private String role;
	private List<ProjectDTO> associatedProjects;
}
