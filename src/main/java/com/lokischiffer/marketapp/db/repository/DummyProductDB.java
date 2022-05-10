package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.ProductDb;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DummyProductDB {

    public Map<Long, ProductDb> productList;

    public DummyProductDB() {
        productList = new HashMap<>();
        fillProductDb();
    }

    private void fillProductDb() {
        ProductDb product = ProductDb.builder().id(1).name("Cookies").quantityInStock(20).build();
        productList.put(product.getId(), product);
        product = ProductDb.builder().id(2).name("Milk").quantityInStock(30).build();
        productList.put(product.getId(), product);
        product = ProductDb.builder().id(3).name("Eggs").quantityInStock(10).build();
        productList.put(product.getId(), product);
    }

    public boolean existsByName(String name){
        for (ProductDb productDb : productList.values()) {
            if (productDb.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public ProductDb findByName(String name) {
        for (ProductDb productDb : productList.values()) {
            if (productDb.getName().equalsIgnoreCase(name)) {
                return productDb;
            }
        }
        return null;
    }
}
