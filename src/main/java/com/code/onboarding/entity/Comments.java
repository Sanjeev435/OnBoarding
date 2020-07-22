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
@Table(name = "T_USER_COMMENTS")
public class Comments {

  public Comments(String comment, LocalDateTime commentedOn, Employee commentedUser,
      OnBoardingProcess onBoardingProcess, OnBoardingProgress onBoardingProgress) {
    this.comment = comment;
    this.commentedOn = commentedOn;
    this.commentedUser = commentedUser;
    this.onBoardingProcess = onBoardingProcess;
    this.onBoardingProgress = onBoardingProgress;
  }

  @Id
  @SequenceGenerator(sequenceName = "S_USER_COMMENTS", allocationSize = 1, name = "S_USER_COMMENTS")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER_COMMENTS")
  @Column(name = "COMMENT_ID", precision = 9)
  private Integer commentId; // Primary ID

  @Column(name = "COMMENT_DETAILS", length = 300)
  private String comment;

  @Column(name = "COMMENT_TIME", columnDefinition = "TIMESTAMP")
  private LocalDateTime commentedOn;

  @JoinColumn(name = "EMPLOYEE_ID")
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Employee.class, fetch = FetchType.EAGER)
  private Employee commentedUser;

  @JoinColumn(name = "PROGRESS_ID")
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = OnBoardingProgress.class, fetch = FetchType.LAZY)
  private OnBoardingProgress onBoardingProgress;

  @JoinColumn(name = "PROCESS_ID")
  @ManyToOne(cascade = CascadeType.ALL, targetEntity = OnBoardingProcess.class, fetch = FetchType.LAZY)
  private OnBoardingProcess onBoardingProcess;

}
