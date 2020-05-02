package com.code.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
	
	private String projectAppliCode;
	private String projectShortName;
	private String projectLongName;
	private String unitName;

}
