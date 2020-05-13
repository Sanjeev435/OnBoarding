package com.code.onboarding.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "employee")
public class EmployeeDTO {

	@NonNull
	@NotEmpty
	private String fName;
	@NonNull
	@NotEmpty
	private String lName;
	@NonNull
	@NotEmpty
	private String emailId;
	@NonNull
	@NotEmpty
	private String subGroup;
	@NonNull
	@NotEmpty
	private String role;

	@Nullable
	@XmlElement(required = false)
	private List<String> projectAppliCodes = new ArrayList<>();
}
