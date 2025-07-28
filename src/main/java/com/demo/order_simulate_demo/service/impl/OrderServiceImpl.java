package com.demo.order_simulate_demo.service.impl;

import com.demo.order_simulate_demo.enums.OrderStatusEnum;
import com.demo.order_simulate_demo.exception.NoContentException;
import com.demo.order_simulate_demo.exception.ResponseCode;
import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.repository.OrderRepo;
import com.demo.order_simulate_demo.request.OrderRequest;
import com.demo.order_simulate_demo.response.OrderResponse;
import com.demo.order_simulate_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public List<OrderResponse> getAllOrder() {
        List<OrderModel> orderModels = orderRepo.findAll();
        return orderModels.stream()
                .map(OrderResponse::new)
                .toList();
    }

    @Override
    public OrderResponse getOrder(String id) {
        OrderModel orderModel = orderRepo.findById(id);
        return new OrderResponse(orderModel);
    }

    @Override
    public void createOrder(OrderRequest request) {
        OrderModel orderModel = new OrderModel(request);
        orderRepo.save(orderModel);
    }

    @Override
    public void cancelOrder(String id) {
        OrderModel order = orderRepo.findById(id);
        if(Objects.isNull(order)) {
            log.warn("Order with ID {} not found", id);
            throw new NoContentException(ResponseCode.NO_CONTENT, ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
        }

        order.setStatus(OrderStatusEnum.CANCELLED.toString());
        orderRepo.save(order);
    }

    @Override
    public void triggerOrderExecution() {
        // random lenh lay ra duoc lenh
        OrderModel orderModel = new OrderModel();

        if(!OrderStatusEnum.PENDING.toString().equalsIgnoreCase(orderModel.getStatus())) return;

        orderModel.setStatus(OrderStatusEnum.EXECUTED.toString());
        orderRepo.save(orderModel);
    }
}
