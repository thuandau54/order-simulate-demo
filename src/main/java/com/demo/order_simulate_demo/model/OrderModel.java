package com.demo.order_simulate_demo.model;

import com.demo.order_simulate_demo.request.OrderRequest;
import com.demo.order_simulate_demo.utils.DateUtil;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private static volatile long idCounter = 0;
    private Long id;
    private String symbol;
    private BigDecimal price;
    private String quantity;
    private String side;

    private String status;
    private LocalDateTime createdTime;

    public OrderModel(OrderRequest request) {
        this.id = generateId();
        this.symbol = request.getSymbol();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
        this.side = request.getSide();
        this.status = request.getStatus();

        this.createdTime = DateUtil.stringToLocalDateTime(request.getCreatedTime());
    }

    private static synchronized Long generateId() {
        return ++idCounter;
    }
}