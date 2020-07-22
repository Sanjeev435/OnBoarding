package com.code.onboarding;

public class MyTest {
  
  
  public static void main(String[] args) {
    System.out.println(isEmployeeExist(null));
    
    System.out.println(isEmployeeExist(0));
    
    System.out.println(isEmployeeExist(1));
    
    
  }
  
  public static boolean isEmployeeExist(Integer employeeId) {
    return employeeId != null && Integer.compare(employeeId, 0) > 0;
  }

}
