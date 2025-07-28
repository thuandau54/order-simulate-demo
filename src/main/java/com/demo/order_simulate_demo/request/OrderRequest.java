package com.demo.order_simulate_demo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String symbol;
    private BigDecimal price;
    private String quantity;
    private String side;
    private String status;

    private String createdTime;
}
