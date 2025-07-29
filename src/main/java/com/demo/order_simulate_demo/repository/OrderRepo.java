package com.demo.order_simulate_demo.repository;

import com.demo.order_simulate_demo.model.OrderModel;

import java.util.List;

public interface OrderRepo {

    List<OrderModel> findAll();

    List<OrderModel> findAllWithOutPendingStatus();

    OrderModel findById(Long id);

    void save(OrderModel orderModel);

}
