package com.example.bike.exception;

import org.springframework.web.client.RestClientException;

public class InvalidException extends RestClientException {
    public InvalidException(String msg) {
        super(msg);
    }
}
