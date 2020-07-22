package com.code.onboarding.util;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "roles")
public class RolesPropertiesUtil {
  
  /*
   * // private Map<String, RolesDTO> uriAccessDetails; private Object
   * uriAccessDetails;
   * 
   * @Value("${mail.sender.address}") private String senderAddress;
   * 
   * @Value("${mail.sender.password}") private String password;
   */
  private Map<String, List<String>> user;
  private Map<String, List<String>> admin;
  private Map<String, List<String>> manager;
  
  
  /*
   * // @Value("${roles.admin.get}") private Object admin;
   * // @Value("${roles.admin.post}") private Object manager;
   * // @Value("${roles.user}")
   */  
  
  
  /*
   * roles: admin: [GET]: - /ADMIN/getDetails [POST]: - /ADMIN/getDetails
   * [DELETE]: - /ADMIN/getDetails manager: [GET]: - /MANAGER/getDetails [POST]: -
   * /MANAGER/getDetails [DELETE]: - /MANAGER/getDetails user: [GET]: -
   * /USER/getDetails [POST]: - /USER/getDetails [DELETE]: - /USER/getDetails
   */


}
