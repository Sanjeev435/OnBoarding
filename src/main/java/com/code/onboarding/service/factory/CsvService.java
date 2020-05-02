package com.code.onboarding.service.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.code.onboarding.dto.Person;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CsvService extends AbstractFileReader {

	@Autowired
	private ResourceLoader resourceLoader;

	static String csvFile = "person.csv";
	static String cvsSplitBy = ",";

	@Override
	public List<Person> readFile(String fileName) {
		return getData(fileName);
	}

	private List<Person> getData(String fileName) {
		List<Person> persons = new ArrayList<>();
		try {
			Resource resource = resourceLoader.getResource("classpath:" + fileName);
			try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
				String line = "";
				while ((line = br.readLine()) != null) {

					// use comma as separator
					String[] person = line.split(cvsSplitBy);
					System.out.println(
							person[0] + "  " + person[1] + "  " + person[2] + "  " + Integer.parseInt(person[3]));
					persons.add(setValuesInPerson(person));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return persons;
	}

	private Person setValuesInPerson(String[] person) {
		return new Person(person[0], person[1], person[2], Integer.parseInt(person[3]));
	}
}
