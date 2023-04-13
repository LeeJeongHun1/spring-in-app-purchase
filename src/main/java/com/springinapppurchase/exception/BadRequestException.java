package com.springinapppurchase.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
    public BadRequestException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
