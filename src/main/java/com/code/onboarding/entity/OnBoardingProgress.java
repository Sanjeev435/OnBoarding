package com.code.onboarding.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="T_ONBOARDING_PROGRESS")
public class OnBoardingProgress {
	
	@Id
	@SequenceGenerator(sequenceName="S_ONBOARDING_PROGRESS", allocationSize = 1, name = "S_ONBOARDING_PROGRESS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ONBOARDING_PROGRESS")
	@Column(name="PROGRESS_ID", precision = 9)
	private Integer progressId; // Primary ID
	
	@Column(name="IS_COMPLETED", length = 1)
	private boolean completed;

	@JoinColumn(name = "PROCESS_ID")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = OnBoardingProcess.class, fetch = FetchType.EAGER)
	private OnBoardingProcess onBoardingProcess;
	
	@JoinColumn(name = "EMPLOYEE_ID")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Employee.class, fetch = FetchType.LAZY)
	private Employee employee;
	
	@OneToMany(targetEntity = Comments.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comments> userComments = new ArrayList<>();
	
}
