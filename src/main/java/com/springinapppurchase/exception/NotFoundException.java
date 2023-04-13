package com.springinapppurchase.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {
    public NotFoundException(String errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}
