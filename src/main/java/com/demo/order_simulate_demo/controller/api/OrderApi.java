package com.demo.order_simulate_demo.controller.api;

import com.demo.order_simulate_demo.request.OrderRequest;
import com.demo.order_simulate_demo.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface OrderApi {

    @Operation(
            tags = {"OrderController"},
            summary = "Tạo mới order"
    )
    @PostMapping(value = {"/orders"})
    void createOrder(@Valid @RequestBody OrderRequest request);

    @Operation(
            tags = {"OrderController"},
            summary = "Lấy danh sách order"
    )
    @GetMapping(value = {"/orders"})
    List<OrderResponse> getAllOrder();

    @Operation(
            tags = {"OrderController"},
            summary = "Lấy chi tiết order"
    )
    @GetMapping(value = {"/orders/{id}"})
    OrderResponse getOrder(@PathVariable @NotNull Long id);

    @Operation(
            tags = {"OrderController"},
            summary = "Hủy order nếu PENDING"
    )
    @PostMapping(value = {"/orders/{id}/cancel"})
    void cancelOrder(@PathVariable @NotNull Long id);

    @Operation(
            tags = {"OrderController"},
            summary = "Admin trigger khớp lệnh ngẫu nhiên các lệnh PENDING"
    )
    @PostMapping(value = {"/orders/simulate-execution"})
    Long triggerOrderExecution();

}
