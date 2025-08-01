package com.demo.order_simulate_demo.controller;

import com.demo.order_simulate_demo.controller.api.ProductApi;
import com.demo.order_simulate_demo.response.FileResponse;
import com.demo.order_simulate_demo.service.TestExportExcel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController implements ProductApi {

    private final TestExportExcel testExportExcel;

    @Override
    public ResponseEntity<?> exportProduct() {
        return FileResponse.file(testExportExcel.exportProduct());
    }
}
