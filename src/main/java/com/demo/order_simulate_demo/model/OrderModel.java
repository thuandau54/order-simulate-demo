package com.demo.order_simulate_demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderModel {
    private String id;
    private String symbol;
    private BigDecimal price;
    private String quantity;
    private String side;

    private String status;
    private LocalDateTime createdTime;
}