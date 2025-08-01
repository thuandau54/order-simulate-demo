package com.demo.order_simulate_demo.service.bases;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractServiceImpl {

    protected void createObjectLog(String id, String symbol, String side) {
        log.info("Create new Order: {} symbol: {} side: {}", id, symbol, side);
    }

    protected void updateObjectLog(String id, String status) {
        log.info("Order {} status updated to {}", id, status);
    }
}
