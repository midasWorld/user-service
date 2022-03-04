package com.midas.userservice.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {
  private boolean success;
  private T response;

  public ApiResponse(boolean success, T response) {
    this.success = success;
    this.response = response;
  }

  public ApiResponse<T> OK(T response) {
    return new ApiResponse<>(true, response);
  }

  public ApiResponse<?> ERROR(Throwable throwable) {
    return new ApiResponse<>(false, throwable.getMessage());
  }

  public ApiResponse<?> ERROR(String message) {
    return new ApiResponse<>(false, message);
  }
}
