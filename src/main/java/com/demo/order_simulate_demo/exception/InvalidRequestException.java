package com.demo.order_simulate_demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("java:S1165")
public class InvalidRequestException extends RuntimeException {

    private final ResponseCode responseCode;
    private Map<String, String> params = new HashMap<>();

    public InvalidRequestException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public InvalidRequestException(ResponseCode responseCode, Map<String, String> params) {
        this.responseCode = responseCode;
        this.params = params;
    }

    public InvalidRequestException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

}
