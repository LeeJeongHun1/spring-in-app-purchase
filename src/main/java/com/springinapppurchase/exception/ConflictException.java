package com.springinapppurchase.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {
    public ConflictException(String errorCode) {
        super(HttpStatus.CONFLICT, errorCode);
    }
}
