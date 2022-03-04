package com.midas.userservice.exception.users;

import com.midas.userservice.exception.ServiceRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status code
// 2XX → OK
// 3XX → Client
// 5XX → Server
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ServiceRuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
