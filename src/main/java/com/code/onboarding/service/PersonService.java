package com.code.onboarding.service;

import com.code.onboarding.dto.Person;

public interface PersonService {
	
	public Person getPersonByName(String name, boolean isExcel);

}
