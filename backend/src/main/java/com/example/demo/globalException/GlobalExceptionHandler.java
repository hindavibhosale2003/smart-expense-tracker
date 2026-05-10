package com.example.demo.globalException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.excepation.ExpenseServiceException;
import com.example.demo.excepation.UserServiceException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<String>
    handleGlobalException(Exception exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(
            exception = ExpenseServiceException.class)
    public ResponseEntity<String>
    handleExpenseServiceException(
            ExpenseServiceException exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                exception.getHttpStatus()
        );
    }

    @ExceptionHandler(
            exception = UserServiceException.class)
    public ResponseEntity<String>
    handleUserServiceException(
            UserServiceException exception) {

        return new ResponseEntity<>(
                exception.getMessage(),
                exception.getHttpStatus()
        );
    }
}