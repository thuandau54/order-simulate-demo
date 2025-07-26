package com.demo.order_simulate_demo.service.impl;

import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.repository.OrderRepo;
import com.demo.order_simulate_demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<OrderModel> getOrderList() {
        return orderRepo.getOrderList();
    }

    @Override
    public OrderModel getOrder(String id) {
        return orderRepo.getOrder(id);
    }

    @Override
    public void createOrder(OrderModel orderModel) {
        orderRepo.save(orderModel);
    }

    @Override
    public void cancelOrder(String id) {
        orderRepo.cancel(id);
    }

    @Override
    public void triggerOrderExecution() {

    }
}
