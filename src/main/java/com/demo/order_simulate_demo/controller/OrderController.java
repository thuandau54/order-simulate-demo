package com.demo.order_simulate_demo.controller;

import com.demo.order_simulate_demo.controller.api.OrderApi;
import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public void createOrder(OrderModel orderModel) {
        orderService.createOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrderList() {
        return orderService.getOrderList();
    }

    @Override
    public OrderModel getOrder(String id) {
        return orderService.getOrder(id);
    }

    @Override
    public void cancelOrder(String id) {
        orderService.cancelOrder(id);
    }

    @Override
    public void triggerOrderExecution() {
        orderService.triggerOrderExecution();
    }
}
