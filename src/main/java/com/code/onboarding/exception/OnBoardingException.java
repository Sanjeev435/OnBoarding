package com.code.onboarding.exception;

public class OnBoardingException {
  
  // Hiding default constructor
  private OnBoardingException() {
  }
  
  public static class DataAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataAlreadyExistException(String errorMessage, Throwable err) {
      super(errorMessage, err);
    }

    public DataAlreadyExistException(String errorMessage) {
      super(errorMessage);
    }
  }

  public static class InvalidProcessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidProcessException(String errorMessage, Throwable err) {
      super(errorMessage, err);
    }

    public InvalidProcessException(String errorMessage) {
      super(errorMessage);
    }
  }

  public static class RedundantDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RedundantDataException(String errorMessage, Throwable err) {
      super(errorMessage, err);
    }

    public RedundantDataException(String errorMessage) {
      super(errorMessage);
    }
  }

}
