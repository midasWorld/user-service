package com.midas.userservice.exception.users;

import com.midas.userservice.exception.ServiceRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends ServiceRuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
