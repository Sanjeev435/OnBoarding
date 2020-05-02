package com.code.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.code.onboarding.dto.Person;
import com.code.onboarding.payload.DemoPayLoad;
import com.code.onboarding.service.PersonService;

@RestController
public class DemoController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("detail/{name}")
	public Person welcomeUser(@PathVariable("name") String name) {
		return DemoPayLoad.getFormattedPayload(personService.getPersonByName(name, false));
	}
	

}
