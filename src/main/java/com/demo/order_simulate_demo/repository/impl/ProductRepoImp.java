package com.demo.order_simulate_demo.repository.impl;

import com.demo.order_simulate_demo.model.ProductModel;
import com.demo.order_simulate_demo.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ProductRepoImp implements ProductRepo {

    private final Map<Long, ProductModel> products = new HashMap<>();

    public ProductRepoImp() {
        ProductModel product = new ProductModel("1", "gao", "10");
        ProductModel product1 = new ProductModel("2", "gao1", "101");
        ProductModel product2 = new ProductModel("3", "gao1", "1011");

        products.put(1L, product);
        products.put(2L, product1);
        products.put(3L, product2);
    }

    @Override
    public List<ProductModel> getAllProduct() {
        return new ArrayList<>(products.values());
    }
}
