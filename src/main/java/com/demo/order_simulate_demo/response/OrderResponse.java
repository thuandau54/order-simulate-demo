package com.demo.order_simulate_demo.response;

import com.demo.order_simulate_demo.model.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String symbol;
    private BigDecimal price;
    private String quantity;
    private String side;
    private String status;

    private LocalDateTime createdTime;

    public OrderResponse(OrderModel model) {
        this.symbol = model.getSymbol();
        this.price = model.getPrice();
        this.quantity = model.getQuantity();
        this.side = model.getSide();
        this.status = model.getStatus();

        this.createdTime = model.getCreatedTime();
    }
}
