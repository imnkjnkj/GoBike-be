package com.example.bike.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(InvalidException.class)
    protected ResponseEntity<Object> handleInvalidException(InvalidException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException e) {
        StringBuilder errors = new StringBuilder();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errors.append(violation.getPropertyPath().toString() + " : " + violation.getMessage() + ", ");
        }
        ApiError apiError = new ApiError(BAD_REQUEST);
        if(errors.length() < 2){
            apiError.setMessage(e.getMessage());
        } else {
            apiError.setMessage(errors.toString().trim().substring(0, errors.length() - 2));
        }
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> onUsernameNotFoundException(UsernameNotFoundException ex){
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> onBadCredentialsException(BadCredentialsException ex){
        ApiError apiError = new ApiError(BAD_REQUEST, "Invalid username or password");
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append(", ");
        }
        ApiError apiError = new ApiError(BAD_REQUEST);
        if(errors.length() < 2){
            apiError.setMessage(ex.getGlobalError().getDefaultMessage());
        } else {
            apiError.setMessage(errors.toString().trim().substring(0, errors.length() - 2));
        }
        return buildResponseEntity(apiError);
    }
}
