package com.demo.order_simulate_demo.repository.impl;

import com.demo.order_simulate_demo.enums.OrderStatusEnum;
import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.repository.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class OrderRepoImpl implements OrderRepo {

    private final Map<Long, OrderModel> orders = new HashMap<>();

    @Override
    public List<OrderModel> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<OrderModel> findAllWithOutPendingStatus() {
        return orders.values().stream()
                .filter(order -> !Objects.equals(OrderStatusEnum.PENDING.toString(), order.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public OrderModel findById(Long id) {
        return orders.get(id);
    }

    @Override
    public void save(OrderModel orderModel) {
        if(orders.containsKey(orderModel.getId())) {
            log.info("release key {}", orderModel.getId());
            orders.remove(orderModel.getId());
        }

        orders.put(orderModel.getId(), orderModel);
    }
}
