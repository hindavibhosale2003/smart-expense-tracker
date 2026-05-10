package com.example.demo.excepation;

import org.springframework.http.HttpStatus;

public class ExpenseServiceException extends RuntimeException {

    private String errorMessage;

    private HttpStatus httpStatus;

    @Override
    public String getMessage() {

        return errorMessage;
    }

    public HttpStatus getHttpStatus() {

        return httpStatus;
    }

    public ExpenseServiceException(
            String errorMessage,
            HttpStatus httpStatus) {

        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}