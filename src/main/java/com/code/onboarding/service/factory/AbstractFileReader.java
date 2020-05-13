package com.code.onboarding.service.factory;

import java.util.List;

import com.code.onboarding.dto.PersonDTO;

public abstract class AbstractFileReader {

	public abstract List<PersonDTO> readFile(String fileName);
}
