package com.code.onboarding.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ONBOARDING_PROCESS")
public class OnBoardingProcess {

	@Id
	@SequenceGenerator(sequenceName="S_ONBOARDING_PROCESS", allocationSize = 1, name = "S_ONBOARDING_PROCESS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ONBOARDING_PROCESS")
	@Column(name = "PROCESS_ID", precision = 9)
	private int processId;

	@Column(name = "COMMNETS", length = 200)
	private String comments;

	@Column(name = "DOCUMENT_NAME", length = 100)
	private String documentName;

	@Lob
	@Column(name = "DOCUMENT_CONTENT", length = 200)
	private byte[] documentContent;

	@Column(name = "DOCUMENT_TYPE", length = 20)
	private String documentType;
	
	@Column(name = "VIDEO_NAME", length = 100)
	private String videoName;

	@Lob
	@Column(name = "VIDEO_CONTENT", length = 200)
	private byte[] videoContent;
	
	@Column(name = "VIDEO_TYPE", length = 20)
	private String videoType;
	
	@Column(name = "PIC_NAME", length = 100)
	private String picName;

	@Lob
	@Column(name = "PIC_CONTENT", length = 200)
	private byte[] picContent;

	@Column(name = "PIC_TYPE", length = 20)
	private String picType;

	@Column(name = "SORT_ORDER", precision = 3)
	private int sortOrder;

	@JoinColumn(name = "PROJECT_APPLI_CODE")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Project.class, fetch = FetchType.LAZY)
	private Project project;
}
