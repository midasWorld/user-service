package com.midas.userservice.exception;

public class ServiceRuntimeException extends RuntimeException {
  public ServiceRuntimeException(String message) {
    super(message);
  }
}
