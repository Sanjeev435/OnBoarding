package com.code.onboarding.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_EMPLOYEE_DETAIL")
public class Employee {

	@Id
	@SequenceGenerator(sequenceName="S_EMPLOYEE_DETAIL", allocationSize = 1, name = "S_EMPLOYEE_DETAIL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMPLOYEE_DETAIL")
	@Column(name = "EMPLOYEE_ID", precision = 9)
	private int employeeId; // Primary ID

	@Column(name = "F_NAME", length = 80)
	private String fName;

	@Column(name = "L_NAME", length = 150)
	private String lName;

	@Column(name = "EMAIL_ID", length = 150)
	private String emailId;

	@Column(name = "SUB_GROUP", length = 100)
	private String subGroup;
	
	@Column(name = "EMP_ROLE", length = 200)
	private String role;
	
	@OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Project> associatedProjects = new ArrayList<>();

	@OneToMany(targetEntity = OnBoardingProgress.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OnBoardingProgress> onBoardingProgress = new ArrayList<>();



}
