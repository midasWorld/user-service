package com.midas.userservice.exception;

public abstract class ServiceRuntimeException extends RuntimeException {
  public ServiceRuntimeException(String message) {
    super(message);
  }
}
