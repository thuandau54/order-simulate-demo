package com.demo.order_simulate_demo.service;

import com.demo.order_simulate_demo.model.OrderModel;

import java.util.List;

public interface OrderService {

    List<OrderModel> getOrderList();

    OrderModel getOrder(String id);

    void createOrder(OrderModel orderModel);

    void cancelOrder(String id);

    void triggerOrderExecution();
}
