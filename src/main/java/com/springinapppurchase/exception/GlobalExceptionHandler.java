package com.springinapppurchase.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> custom(CustomException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setErrorMessage(getMessage(ex.getErrorCode()));
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<ExceptionResponse> bind(BindException ex) {
//        ExceptionResponse response = new ExceptionResponse();
//        response.setErrorCode("BAD_REQUEST");
//
//        BindingResult bindingResult = ex.getBindingResult();
//        FieldError fieldError = bindingResult.getFieldErrors().get(0);
//        String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
//
//        response.setErrorMessage(errorMessage);
//        response.setTimestamp(LocalDateTime.now());
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ExceptionResponse> custom(BindException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");

        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldErrors().get(0);
        String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();

        response.setErrorMessage(errorMessage);
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> invalidJsonFormat(HttpMessageNotReadableException ex) {
        String errorCode = "INVALID_JSON_FORMAT";
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(errorCode);
        response.setErrorMessage(getMessage(errorCode));
        response.setTimestamp(LocalDateTime.now());
        log.error("Error: ", ex);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> methodNotSupport(HttpRequestMethodNotSupportedException ex) {
        String errorCode = "METHOD_NOT_ALLOWED";
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(errorCode);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> maxUploadSizeExceed(MaxUploadSizeExceededException ex) {
        String errorCode = "FILE.OVER_UPLOAD_SIZE";
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(errorCode);
        response.setErrorMessage(getMessage(errorCode, ex.getMessage()));
        response.setTimestamp(LocalDateTime.now());
        log.error("Error: ", ex);

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> unknown(Exception ex) {
//        String errorCode = "UNKNOWN";
//        ExceptionResponse response = new ExceptionResponse();
//        response.setErrorCode(errorCode);
//        response.setErrorMessage(getMessage(errorCode, ex.getMessage()));
//        response.setTimestamp(LocalDateTime.now());
//        log.error("Error: ", ex);
//
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private String getMessage(String messageCode, String... args) {
        return messageSource.getMessage(messageCode, args, Locale.KOREAN);
    }
}
