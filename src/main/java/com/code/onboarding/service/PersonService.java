package com.code.onboarding.service;

import com.code.onboarding.dto.PersonDTO;

public interface PersonService {
	
	public PersonDTO getPersonByName(String name, boolean isExcel);

}
