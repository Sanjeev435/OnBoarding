package com.code.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnBoardingProcessDTO {

	private String comments;

	private String documentName;
	private byte[] documentContent;
	private String documentType;

	private String videoName;
	private byte[] videoContent;
	private String videoType;

	private String picName;
	private byte[] picContent;
	private String picType;

	private int sortOrder;
}
