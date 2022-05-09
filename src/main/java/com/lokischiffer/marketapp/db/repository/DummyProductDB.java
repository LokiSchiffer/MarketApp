package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.ProductDb;

import java.util.HashMap;
import java.util.Map;

public class DummyProductDB {

    public Map<Long, ProductDb> productList;

    public DummyProductDB() {
        productList = new HashMap<>();
        fillProductDb();
    }

    private void fillProductDb() {
        ProductDb product = ProductDb.builder().id(1).name("Cookies").quantity(20).build();
        productList.put(product.getId(), product);
        product = ProductDb.builder().id(2).name("Milk").quantity(30).build();
        productList.put(product.getId(), product);
        product = ProductDb.builder().id(3).name("Eggs").quantity(10).build();
        productList.put(product.getId(), product);
    }
}
