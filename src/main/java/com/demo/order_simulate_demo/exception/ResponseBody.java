package com.demo.order_simulate_demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody {
    private String code;
    private String message;
    private long serverTime;

    public static ResponseBody create(ResponseCode responseCode) {
        return ResponseBody.builder()
                .code(responseCode.getErrorCode())
                .message(responseCode.getMessage())
                .serverTime(System.currentTimeMillis())
                .build();
    }

    public static ResponseBody create(String errorCode, String message) {
        return ResponseBody.builder()
                .code(errorCode)
                .message(message)
                .serverTime(System.currentTimeMillis())
                .build();
    }

}
