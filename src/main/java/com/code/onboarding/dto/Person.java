package com.code.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	
	private String name;
	private String lastName;
	private String address;
	private Integer phoneNumber;

}
