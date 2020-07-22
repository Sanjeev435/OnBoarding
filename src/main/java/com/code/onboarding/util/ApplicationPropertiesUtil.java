package com.code.onboarding.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ApplicationPropertiesUtil {

  @Value("${mail.sender.address}")
  private String senderAddress;
  
  @Value("${mail.sender.password}")
  private String password;
}
