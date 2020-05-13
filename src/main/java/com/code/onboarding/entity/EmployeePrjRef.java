package com.code.onboarding.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "T_EMPLOYEE_PRJ_REF")
public class EmployeePrjRef {
	
	public EmployeePrjRef(Employee employee, Project project) {
		this.employee = employee;
		this.project = project;
	}

	@Id
	@SequenceGenerator(sequenceName = "S_EMPLOYEE_PRJ_REF", allocationSize = 1, name = "S_EMPLOYEE_PRJ_REF")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMPLOYEE_PRJ_REF")
	@Column(name = "EMP_PRJ_REF_ID", precision = 9)
	private Integer empPrjRefId; // Primary ID

	@JoinColumn(name = "EMPLOYEE_ID")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Employee.class, fetch = FetchType.LAZY)
	private Employee employee;

	@JoinColumn(name = "PROJECT_ID")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Project.class, fetch = FetchType.EAGER)
	private Project project;

}
