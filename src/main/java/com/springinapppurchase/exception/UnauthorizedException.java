package com.springinapppurchase.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String errorCode) {
        super(HttpStatus.UNAUTHORIZED, errorCode);
    }
}
