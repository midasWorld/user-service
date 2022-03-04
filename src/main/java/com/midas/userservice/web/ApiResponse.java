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

  public static <T> ApiResponse<T> OK(T response) {
    return new ApiResponse<>(true, response);
  }

  public static ApiResponse<?> ERROR(Throwable throwable) {
    return new ApiResponse<>(false, throwable.getMessage());
  }

  public static ApiResponse<?> ERROR(String message) {
    return new ApiResponse<>(false, message);
  }

  public static ApiResponse<?> ERROR(ApiError error) {
    return new ApiResponse<>(false, error);
  }
}
