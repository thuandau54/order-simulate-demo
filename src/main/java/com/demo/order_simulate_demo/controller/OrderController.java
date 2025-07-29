package com.demo.order_simulate_demo.controller;

import com.demo.order_simulate_demo.controller.api.OrderApi;
import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.request.OrderRequest;
import com.demo.order_simulate_demo.response.OrderResponse;
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
    public void createOrder(OrderRequest request) {
        orderService.createOrder(request);
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        return orderService.getAllOrder();
    }

    @Override
    public OrderResponse getOrder(Long id) {
        return orderService.getOrder(id);
    }

    @Override
    public void cancelOrder(Long id) {
        orderService.cancelOrder(id);
    }

    @Override
    public Long triggerOrderExecution() {
        return orderService.triggerOrderExecution();
    }
}
