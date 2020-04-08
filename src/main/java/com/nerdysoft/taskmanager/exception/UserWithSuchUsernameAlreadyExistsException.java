package com.nerdysoft.taskmanager.exception;

public class UserWithSuchUsernameAlreadyExistsException extends RuntimeException {

    public UserWithSuchUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
