package com.code.onboarding.entity;

import java.io.Serializable;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_PROJECT_DETAILS")
public class Project implements Serializable, Copyable<Project>{

	private static final long serialVersionUID = 1L;

	public Project(String projectAppliCode, String projectLongName, 
			String projectShortName, String unitName) {
		this.projectAppliCode = projectAppliCode;
		this.projectLongName = projectLongName;
		this.projectShortName = projectShortName;
		this.unitName = unitName;
	}

	@Id
	@SequenceGenerator(sequenceName="S_PROJECT_DETAILS", allocationSize = 1, name = "S_PROJECT_DETAILS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PROJECT_DETAILS")
	@Column(name = "PROJECT_ID", precision = 9)
	private int projectId;

	@Column(name = "PROJECT_APPLI_CODE", length = 20)
	private String projectAppliCode;

	@Column(name = "PROJECT_SHORT_NAME", length = 50)
	private String projectShortName;

	@Column(name = "PROJECT_LONG_NAME", length = 200)
	private String projectLongName;

	@Column(name = "PROJECT_UNIT_NAME", length = 200)
	private String unitName;

	@OneToMany(targetEntity = OnBoardingProcess.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OnBoardingProcess> onBoardingProcessess = new ArrayList<>();

	@OneToMany(targetEntity = Employee.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Employee> employeees = new ArrayList<>();
	
	/**
	 * <pre>
	 * Only <b>4 parameters</b> are allowed. Other then 4 
	 * parameters will not have any affect on object creation.
	 * The objects are need to be put in sequence of <b>
	 *  1. ProjectAppliCode
	 *  2. ProjectLongName
	 *  3. ProjectShortName
	 *  4. UnitName
	 * </b>
	 * <pre>
	 */
	@Override
	public  Project from(Object... obj) {
		return new Project((String) obj[0], (String) obj[1], 
				(String) obj[2], (String) obj[3]);
	}

}
