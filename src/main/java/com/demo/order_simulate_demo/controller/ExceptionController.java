package com.demo.order_simulate_demo.controller;

import com.demo.order_simulate_demo.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Object> handleNoContentException(NoContentException exception) {
        ResponseCode responseCode = exception.getResponseCode();
        ResponseBody body = ResponseBody.create(responseCode);
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException exception) {
        ResponseCode responseCode = exception.getResponseCode();
        ResponseBody body = ResponseBody.create(responseCode);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnprocessableContentException.class)
    public ResponseEntity<Object> handleUnprocessableContentException(UnprocessableContentException exception) {
        ResponseCode responseCode = exception.getResponseCode();
        ResponseBody body = ResponseBody.create(responseCode);
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRepositoryException(RuntimeException exception) {
        String errorCode = ResponseCode.INTERNAL_SERVER_ERROR.getErrorCode();
        String messageCode = ResponseCode.INTERNAL_SERVER_ERROR.getMessage();
        ResponseBody body = ResponseBody.create(errorCode, messageCode);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}