package com.code.onboarding.service.impl;

import org.springframework.stereotype.Service;

import com.code.onboarding.constants.ApplicationConstants;
import com.code.onboarding.dto.Person;
import com.code.onboarding.service.PersonService;
import com.code.onboarding.service.factory.FileReaderFactory;

@Service
public class PersonServiceImpl implements PersonService {

	@Override
	public Person getPersonByName(String name, boolean isExcel) {
		return (Person) FileReaderFactory.getFileReader(ApplicationConstants.EXCEL_FILE_TYPE).readFile("person.xlsx")
				.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findAny().orElse(null);

	}

}
