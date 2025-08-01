package com.demo.order_simulate_demo.controller.api;

import com.demo.order_simulate_demo.response.FileResponse;
import com.demo.order_simulate_demo.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProductApi {

    @Operation(
            tags = {"ProductController"},
            summary = "xuat danh sách product"
    )
    @GetMapping(value = {"/export"})
    ResponseEntity<?> exportProduct();

}
