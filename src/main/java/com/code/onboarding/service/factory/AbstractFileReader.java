package com.code.onboarding.service.factory;

import java.util.List;

import com.code.onboarding.dto.Person;

public abstract class AbstractFileReader {

	public abstract List<Person> readFile(String fileName);
}
