package com.demo.order_simulate_demo.repository.impl;

import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.repository.OrderRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepoImpl implements OrderRepo {

    private final Map<String, OrderModel> orders = new HashMap<>();

    @Override
    public List<OrderModel> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public OrderModel findById(String id) {
        return orders.get(id);
    }

    @Override
    public void save(OrderModel orderModel) {
        orders.put(orderModel.getId(), orderModel);
    }
}
