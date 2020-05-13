package com.code.onboarding.entity;

import java.time.LocalDateTime;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="T_USER_COMMENTS")
public class Comments {
	
	@Id
	@SequenceGenerator(sequenceName="S_USER_COMMENTS", allocationSize = 1, name = "S_USER_COMMENTS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER_COMMENTS")
	@Column(name="COMMENT_ID", precision = 9)
	private Integer commentId; // Primary ID
	
	@Column(name="COMMENT", length = 300)
	private String comment;
	
	@JoinColumn(name = "EMPLOYEE_ID")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Employee.class, fetch = FetchType.EAGER)
	private Employee commentedUser;
	
	@Column(name = "COMMENT_ON", columnDefinition = "TIMESTAMP")
	private LocalDateTime  commentedOn;
	
}
