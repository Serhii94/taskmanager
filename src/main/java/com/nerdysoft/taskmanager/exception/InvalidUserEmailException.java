package com.nerdysoft.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUserEmailException extends RuntimeException {

    public InvalidUserEmailException(String message) {
        super(message);
    }
}
