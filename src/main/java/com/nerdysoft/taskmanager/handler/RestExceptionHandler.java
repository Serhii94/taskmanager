package com.nerdysoft.taskmanager.handler;

import com.nerdysoft.taskmanager.exception.InvalidUserEmailException;
import com.nerdysoft.taskmanager.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidUserEmailException.class)
    public ResponseEntity<?> handleResourceNotFoundException(InvalidUserEmailException iuee,
                                                             HttpServletRequest request) {

        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

}
