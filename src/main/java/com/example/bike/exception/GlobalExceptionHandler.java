package com.example.bike.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(GenericNotFoundException.class)
    public ErrorResponse handleGenericNotFoundException(GenericNotFoundException e) {
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage() + " not found").build();
    }

    @ExceptionHandler(DataAccessException.class)
    public ErrorResponse handleDataAccessException(DataAccessException e) {
        return ErrorResponse.builder(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        StringBuilder errors = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.append(violation.getPropertyPath().toString()).append(" : ").append(violation.getMessage()).append(", ");
        }
        if (errors.length() < 2) {
            return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
        }
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST,
                errors.toString().trim().substring(0, errors.length() - 2)).build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append(", ");
        }
        String message;
        if (errors.length() < 2) {
            message = Objects.requireNonNull(ex.getGlobalError()).getDefaultMessage();
        } else {
            message = errors.toString().trim().substring(0, errors.length() - 2);
        }
        return ResponseEntity.badRequest().body(
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message)
        );
    }
}
