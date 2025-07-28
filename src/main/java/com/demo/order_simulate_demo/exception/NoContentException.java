package com.demo.order_simulate_demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("java:S1165")
public class NoContentException extends RuntimeException {

    private final ResponseCode responseCode;

    public NoContentException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public NoContentException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }
}
