package com.code.onboarding.service.factory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.code.onboarding.dto.Person;

@Component
public class ExcelService extends AbstractFileReader {

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public List<Person> readFile(String fileName) {
		return getData(fileName);
	}

	public List<Person> getData(String fileName) {
		List<Person> persons = new ArrayList<>();
		try {

			Resource resource = resourceLoader.getResource("classpath:" + fileName);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(resource.getFile()));

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			try {
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					Person person = new Person();
					int counter = 0;
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						setValuesInerson(person, counter, currentCell);
						counter++;
					}
					System.out.println(person.toString());
					persons.add(person);
				}
			} finally {
				workbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return persons;
	}

	private void setValuesInerson(Person person, int counter, Cell currentCell) {

		switch (counter) {
		case 0:
			person.setName(currentCell.getStringCellValue());
			break;
		case 1:
			person.setLastName(currentCell.getStringCellValue());
			break;
		case 2:
			person.setAddress(currentCell.getStringCellValue());
			break;
		case 3:
			person.setPhoneNumber((int) currentCell.getNumericCellValue());
			break;
		default:
			break;
		}

	}

}
