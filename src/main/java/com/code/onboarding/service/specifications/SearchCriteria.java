package com.code.onboarding.service.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
  private String key;
  private OperationType operation;
  private Object value;
  
 /**
  * Supported Operations
  * @author Sanjeev
  */
  public enum OperationType{
    EQ,
    LT, 
    GT,
    LIKE,
    GT_THEN_EQL_TO,
    LESS_THEN_EQL_TO    
    ; 
  }
  }
