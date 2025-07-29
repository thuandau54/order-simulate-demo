package com.demo.order_simulate_demo.exception;

import lombok.Getter;

@Getter
public enum ResponseCode {

    NO_CONTENT("DATA-ERROR-204", "NO CONTENT"),
    NOT_PENDING_STATUS_ERROR("DATA-ERROR-422", "Order status is not PENDING"),
    INTERNAL_SERVER_ERROR("DATA-ERROR-500","INTERNAL SERVER ERROR");

    private final String errorCode;
    private final String message;

    ResponseCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
