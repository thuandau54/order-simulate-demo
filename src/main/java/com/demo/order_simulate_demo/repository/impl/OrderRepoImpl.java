package com.demo.order_simulate_demo.repository.impl;

import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.repository.OrderRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {

    // list fake data

    @Override
    public List<OrderModel> getOrderList() {
        return List.of();
    }

    @Override
    public OrderModel getOrder(String id) {
        return null;
    }

    @Override
    public void save(OrderModel orderModel) {

    }

    @Override
    public void cancel(String id) {

    }
}
