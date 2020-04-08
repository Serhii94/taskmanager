package com.nerdysoft.taskmanager.exception;

public class UserWithSuchEmailAlreadyExistsException extends RuntimeException {

    public UserWithSuchEmailAlreadyExistsException(String message) {
        super(message);
    }
}
