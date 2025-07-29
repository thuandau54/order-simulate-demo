package com.demo.order_simulate_demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank
    private String symbol;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String quantity;

    @NotBlank
    @Pattern(regexp = "BUY|SELL", message = "Side must be BUY or SELL")
    private String side;

    @NotBlank
    @Pattern(regexp = "PENDING", message = "Status must be PENDING")
    private String status;

    @NotBlank
    private String createdTime;
}
