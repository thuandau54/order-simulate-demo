package com.demo.order_simulate_demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("java:S1165")
public class UnprocessableContentException extends RuntimeException {

    private final ResponseCode responseCode;
    private Map<String, String> params = new HashMap<>();

    public UnprocessableContentException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public UnprocessableContentException(ResponseCode responseCode, Map<String, String> params) {
        this.responseCode = responseCode;
        this.params = params;
    }

    public UnprocessableContentException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
