package com.demo.order_simulate_demo.controller;

import com.demo.order_simulate_demo.exception.InvalidRequestException;
import com.demo.order_simulate_demo.exception.NoContentException;
import com.demo.order_simulate_demo.exception.ResponseCode;
import com.demo.order_simulate_demo.exception.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRepositoryException(RuntimeException exception) {
        String errorCode = ResponseCode.INTERNAL_SERVER_ERROR.getErrorCode();
        String messageCode = ResponseCode.INTERNAL_SERVER_ERROR.getMessage();
        ResponseBody body = ResponseBody.create(errorCode, messageCode);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}