package com.code.onboarding.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "person")
public class PersonDTO {
	
	private String name;
	private String lastName;
	private String address;
	private Integer phoneNumber;

}
