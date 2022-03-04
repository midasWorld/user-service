package com.midas.userservice.exception;

import com.midas.userservice.exception.users.NotFoundException;
import com.midas.userservice.web.ApiError;
import com.midas.userservice.web.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> newResponse(Throwable throwable, HttpStatus status, WebRequest request) {
        ApiError error = new ApiError(LocalDateTime.now(), throwable.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(ApiResponse.ERROR(error), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return newResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllException(Exception e, WebRequest request) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public final ResponseEntity<?> handleNotFoundException(ServiceRuntimeException e, WebRequest request) {
        if (e instanceof NotFoundException)
            return newResponse(e, HttpStatus.NOT_FOUND, request);

        log.warn("Unexpected service exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
