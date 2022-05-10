package com.lokischiffer.marketapp.logic.service.checkout;

import com.lokischiffer.marketapp.logic.dto.ProductDto;

import java.util.HashMap;
import java.util.Map;

public class Checkout {

    private static Map<Long, ProductDto> productList;

    private Checkout() {
        productList = new HashMap<>();
        System.out.println("Checkout Initialization");
    }

    private static class RegistryHolder {
        static Checkout INSTANCE = new Checkout();
    }

    public static Checkout getInstance() {
        return RegistryHolder.INSTANCE;
    }

    public static boolean verifyList() {
        return productList.isEmpty();
    }

    public static void addProduct(ProductDto product) {
        if (productList.containsKey(product.getId())) {
            ProductDto newProduct = productList.get(product.getId());
            newProduct.setQuantity(newProduct.getQuantity() + product.getQuantity());
            productList.put(newProduct.getId(), newProduct);
        } else {
            productList.put(product.getId(), product);
        }
    }
}
