package com.demo.order_simulate_demo.service.impl;

import com.demo.order_simulate_demo.enums.OrderStatusEnum;
import com.demo.order_simulate_demo.exception.NoContentException;
import com.demo.order_simulate_demo.exception.ResponseCode;
import com.demo.order_simulate_demo.exception.UnprocessableContentException;
import com.demo.order_simulate_demo.model.OrderModel;
import com.demo.order_simulate_demo.repository.OrderRepo;
import com.demo.order_simulate_demo.request.OrderRequest;
import com.demo.order_simulate_demo.response.OrderResponse;
import com.demo.order_simulate_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public List<OrderResponse> getAllOrder() {
        List<OrderModel> orderModels = orderRepo.findAll();
        if(orderModels.isEmpty()) {
            log.error("GET all order not found");
            throw new NoContentException(ResponseCode.NO_CONTENT, ResponseCode.NO_CONTENT.getMessage());
        }

        return orderModels.stream()
                .map(OrderResponse::new)
                .toList();
    }

    @Override
    public OrderResponse getOrder(Long id) {
        OrderModel orderModel = getOrderModel(id);
        return new OrderResponse(orderModel);
    }

    @Override
    public void createOrder(OrderRequest request) {
        OrderModel orderModel = new OrderModel(request);
        orderRepo.save(orderModel);
        createObjectLog(String.valueOf(orderModel.getId()), orderModel.getSymbol(), orderModel.getSide());
    }

    @Override
    public void cancelOrder(Long id) {
        OrderModel order = getOrderModel(id);
        if(!OrderStatusEnum.PENDING.toString().equalsIgnoreCase(order.getStatus())) {
            log.error("Failed to update order with ID {}: status is {} instead of PENDING", id, order.getStatus());
            throw new UnprocessableContentException(ResponseCode.NOT_PENDING_STATUS_ERROR, ResponseCode.NOT_PENDING_STATUS_ERROR.getMessage());
        }

        order.setStatus(OrderStatusEnum.CANCELLED.toString());
        orderRepo.save(order);
        updateObjectLog(String.valueOf(order.getId()), order.getStatus());
    }

    @Override
    public Long triggerOrderExecution() {
        OrderModel order = findRandom()
                .orElseThrow(() -> {
                    log.error("No orders available");
                    return new NoContentException(ResponseCode.NO_CONTENT, ResponseCode.NO_CONTENT.getMessage());
                });

        order.setStatus(OrderStatusEnum.EXECUTED.toString());
        orderRepo.save(order);
        updateObjectLog(String.valueOf(order.getId()), order.getStatus());

        return order.getId();
    }

    private Optional<OrderModel> findRandom() {
        List<OrderModel> orderList = orderRepo.findAllPending();
        if (orderList.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        int randomIndex = random.nextInt(orderList.size());
        return Optional.of(orderList.get(randomIndex));
    }

    private OrderModel getOrderModel(Long id) {
        OrderModel orderModel = orderRepo.findById(id);
        if(Objects.isNull(orderModel)) {
            log.error("Order with ID {} not found", id);
            throw new NoContentException(ResponseCode.NO_CONTENT, ResponseCode.NO_CONTENT.getMessage());
        }
        return orderModel;
    }
}
