package com.springinapppurchase.dto.api;

import org.springframework.http.HttpStatus;

public class ApiError {
    public String msg;
    public HttpStatus httpStatus;
    public int httpStatusCode;

    public ApiError(String msg, HttpStatus httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatus.value();
    }

}
