package com.user.user_service.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.FOUND)
public class UserAlreadyExits extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExits(String message) {
        super(message);
    }
}