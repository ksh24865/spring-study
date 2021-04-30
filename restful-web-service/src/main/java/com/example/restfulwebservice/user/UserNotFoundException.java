package com.example.restfulwebservice.user;

// HTTP Status code
// 2XX -> OK
// 4XX -> Client's fault
// 5XX -> Server's fault

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
