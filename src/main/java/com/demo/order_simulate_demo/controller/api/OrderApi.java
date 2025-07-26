package com.demo.order_simulate_demo.controller.api;

import com.demo.order_simulate_demo.model.OrderModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
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
    void createOrder(@RequestBody @Valid OrderModel orderModel);

    @Operation(
            tags = {"OrderController"},
            summary = "Lấy danh sách order"
    )
    @GetMapping(value = {"/orders"})
    List<OrderModel> getOrderList();

    @Operation(
            tags = {"OrderController"},
            summary = "Lấy chi tiết order"
    )
    @GetMapping(value = {"/orders/{id}"})
    OrderModel getOrder(@RequestBody @Valid String id);

    @Operation(
            tags = {"OrderController"},
            summary = "Hủy order nếu PENDING"
    )
    @PostMapping(value = {"/orders/{id}/cancel"})
    void cancelOrder(@RequestBody @Valid String id);

    @Operation(
            tags = {"OrderController"},
            summary = "Admin trigger khớp lệnh ngẫu nhiên các lệnh PENDING"
    )
    @PostMapping(value = {"/orders/simulate-execution"})
    void triggerOrderExecution();

}
