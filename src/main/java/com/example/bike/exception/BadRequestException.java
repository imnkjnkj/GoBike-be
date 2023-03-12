package com.example.bike.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;

public class BadRequestException extends ErrorResponseException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST,asProblemDetail(message) , null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
    }
}
