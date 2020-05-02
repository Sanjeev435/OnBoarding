package com.code.onboarding.service.factory;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileReaderFactory {

	private static final String CSV_FILE_TYPE = "csv";

	private static final String EXCEL_FILE_TYPE = "excel";

	@Autowired
	private static CsvService csvService;

	@Autowired
	private static ExcelService excelService;

	public static AbstractFileReader getFileReader(String fileType) {
		switch (fileType) {
		case CSV_FILE_TYPE:
			return excelService;
		case EXCEL_FILE_TYPE:
			return csvService;
		default:
			log.error("No correct fileType specified while calling getFileReader() method.");
			return excelService;
		}
	}

}
