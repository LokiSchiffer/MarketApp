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
        if (RegistryHolder.INSTANCE == null)
            RegistryHolder.INSTANCE = new Checkout();
        return RegistryHolder.INSTANCE;
    }

    public boolean verifyList() {
        return productList.isEmpty();
    }

    public boolean verifyProduct(ProductDto product) {
        return productList.containsKey(product.getId());
    }

    public void addProduct(ProductDto product) {
        if (productList.containsKey(product.getId())) {
            ProductDto newProduct = productList.get(product.getId());
            newProduct.setQuantity(newProduct.getQuantity() + product.getQuantity());
            productList.put(newProduct.getId(), newProduct);
        } else {
            productList.put(product.getId(), product);
        }
    }

    public void removeProduct(ProductDto product) {
        if (productList.containsKey(product.getId())) {
            productList.remove(product.getId());
        }
    }

    public void deleteInstance() {
        if (RegistryHolder.INSTANCE != null) {
            RegistryHolder.INSTANCE = null;
        }
        System.out.println("Checkout deletion");
    }
}
