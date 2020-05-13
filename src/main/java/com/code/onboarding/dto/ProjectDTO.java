package com.code.onboarding.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "project")
public class ProjectDTO {
	
	private String projectAppliCode;
	private String projectShortName;
	private String projectLongName;
	private String unitName;

}
