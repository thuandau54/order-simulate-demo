package com.demo.order_simulate_demo.repository;

import com.demo.order_simulate_demo.model.ProductModel;

import java.util.List;

public interface ProductRepo {

    List<ProductModel> getAllProduct();
}
