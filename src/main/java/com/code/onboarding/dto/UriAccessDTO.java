package com.code.onboarding.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UriAccessDTO {
  
  private String requestType;
  private List<String> assignedRole;

}
