package com.code.onboarding.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;*/

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_EMPLOYEE_DETAIL")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Employee() {
		this.employeeId = null;
	}
	
	public Employee(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public Employee(String fName, String lName, 
			String emailId, String subGroup, String role) {
		this.role = role;
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.subGroup = subGroup;
	}
	
	public boolean isEmployeeExist() {
	  return employeeId != null && Integer.compare(employeeId, 0) > 0;
	}

	@Id
	@SequenceGenerator(sequenceName="S_EMPLOYEE_DETAIL", allocationSize = 1, name = "S_EMPLOYEE_DETAIL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMPLOYEE_DETAIL")
	@Column(name = "EMPLOYEE_ID", precision = 9)
	private Integer employeeId; // Primary ID

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
	
	@OneToMany(targetEntity = EmployeePrjRef.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeePrjRef> associatedProjects = new ArrayList<>();

	@OneToMany(targetEntity = OnBoardingProgress.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OnBoardingProgress> onBoardingProgress = new ArrayList<>();

}
