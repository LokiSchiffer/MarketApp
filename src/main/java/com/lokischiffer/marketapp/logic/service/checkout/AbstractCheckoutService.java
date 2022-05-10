package com.lokischiffer.marketapp.logic.service.checkout;

import com.lokischiffer.marketapp.db.model.ProductDb;
import com.lokischiffer.marketapp.db.repository.DummyProductDB;
import com.lokischiffer.marketapp.logic.dto.ProductDto;
import com.lokischiffer.marketapp.logic.exceptions.custom.ParameterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCheckoutService<T extends ProductDto> {

    @Autowired
    private DummyProductDB dummyDB;

    private Checkout checkout;


    protected final ProductDto createInternal(T product) {
//        if (loginInternal(user) == null) {
//            throw new NullPointerException("User not identified");
//        }
        if (!verifyInstance()){
            throw new IllegalArgumentException("The checkout is already created");
        } else if (!dummyDB.productList.containsKey(product.getId())) {
            throw new ParameterNotFoundException("Product not found");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new IllegalArgumentException("You're trying to reserved a bigger quantity than"
                    + "there is available");
        } else {
            checkout.addProduct(product);
            dummyDB.productList.get(product.getId()).setReservedQuantity(product.getQuantity());
            ProductDto productDto = createProductDto(dummyDB.productList.get(product.getId()));
            productDto.setQuantity(dummyDB.productList.get(product.getId()).getReservedQuantity());
            return productDto;
        }
    }
    protected final ProductDto addInternal(T product) {
        if (verifyInstance()) {
            dropInstance();
            throw new ParameterNotFoundException("There is no checkout created");
        } else if (!dummyDB.productList.containsKey(product.getId())) {
            throw new ParameterNotFoundException("There is no product with that ID");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new IllegalArgumentException("You're trying to reserve a bigger quantity than"
                    + " there is available");
        } else if (checkout.verifyProduct(product)) {
            throw new IllegalArgumentException("That product is already on the checkout");
        } else {
            checkout.addProduct(product);
            dummyDB.productList.get(product.getId()).setReservedQuantity(product.getQuantity());
            ProductDto productDto = createProductDto(dummyDB.productList.get(product.getId()));
            productDto.setQuantity(dummyDB.productList.get(product.getId()).getReservedQuantity());
            return productDto;
        }
    }

    protected final ProductDto updateQuantityInternal(long id, ProductDto product) {
        if (verifyInstance()) {
            dropInstance();
            throw new ParameterNotFoundException("There is no checkout created");
        } else if (!dummyDB.productList.containsKey(id)) {
            throw new ParameterNotFoundException("There is no product with that ID");
        } else if (product.getId() != id) {
            throw new IllegalArgumentException("ID in the URI doesn't match the product ID");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new IllegalArgumentException("You're trying to reserve a bigger quantity than"
                    + " there is available");
        } else if (!checkout.verifyProduct(product)) {
            throw new ParameterNotFoundException("That product is not on the checkout");
        } else {
            int reservedStock = dummyDB.productList.get(product.getId()).getReservedQuantity()
                    + product.getQuantity();
            dummyDB.productList.get(product.getId()).setReservedQuantity(reservedStock);
            ProductDto productDto = createProductDto(dummyDB.productList.get(product.getId()));
            productDto.setQuantity(dummyDB.productList.get(product.getId()).getReservedQuantity());
            return productDto;
        }
    }

    protected final ProductDto removeInternal(String name) {
        if (verifyInstance()) {
            dropInstance();
            throw new ParameterNotFoundException("There is no checkout created");
        } else if (!dummyDB.existsByName(name)) {
            throw new ParameterNotFoundException("There is no product with that name");
        }
        ProductDto product = createProductDto(dummyDB.findByName(name));
        if (!checkout.verifyProduct(product)) {
            throw new ParameterNotFoundException("That product is not on the checkout");
        } else {
            checkout.removeProduct(product);
            if (checkout.verifyList()) {
                dropInstance();
            }
            ProductDto productDto = createProductDto(dummyDB.productList.get(product.getId()));
            productDto.setQuantity(dummyDB.productList.get(product.getId()).getReservedQuantity());
            dummyDB.productList.get(product.getId()).setReservedQuantity(0);
            return productDto;
        }
    }

    private boolean verifyInstance() {
        checkout = Checkout.getInstance();
        return checkout.verifyList();
    }

    private void dropInstance() {
        checkout.deleteInstance();
    }

    private ProductDto createProductDto(ProductDb productDb) {
        return new ProductDto(productDb.getId(), productDb.getName(), productDb.getQuantityInStock());
    }

    private int availableStock(ProductDto productDto) {
        return dummyDB.productList.get(productDto.getId()).getQuantityInStock()
                -dummyDB.productList.get(productDto.getId()).getReservedQuantity();
    }
}
