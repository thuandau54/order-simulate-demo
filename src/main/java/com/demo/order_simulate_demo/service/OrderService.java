package com.demo.order_simulate_demo.service;

import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.request.OrderRequest;
import com.demo.order_simulate_demo.response.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrder();

    OrderResponse getOrder(String id);

    void createOrder(OrderRequest request);

    void cancelOrder(String id);

    void triggerOrderExecution();
}
