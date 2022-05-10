package com.lokischiffer.marketapp.logic.service.checkout;

import com.lokischiffer.marketapp.db.model.ProductDb;
import com.lokischiffer.marketapp.db.repository.DummyProductDB;
import com.lokischiffer.marketapp.logic.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCheckoutService<T extends ProductDto> {

    @Autowired
    private DummyProductDB dummyDB;


    protected final ProductDto createInternal(T product) {
//        if (loginInternal(user) == null) {
//            throw new NullPointerException("User not identified");
//        }
        if (!dummyDB.productList.containsKey(product.getId())) {
            throw new NullPointerException("Product not found");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new IllegalArgumentException("You're trying to reserved a bigger quantity than"
                    + "there is available");
        } else {
            ProductDto productDto = createProductDto(dummyDB.productList.get(product.getId()));
            productDto.setQuantity(product.getQuantity());
            return productDto;
        }
    }

    protected final ProductDto updateQuantityInternal(long id, ProductDto product) {
        if (!dummyDB.productList.containsKey(id)) {
            throw new NullPointerException("There is no product with that ID");
        } else if (product.getId() != id) {
            throw new IllegalArgumentException("ID in the URI doesn't match the product ID");
        } else if (availableStock(product) < product.getQuantity()) {
            throw new IllegalArgumentException("You're trying to reserved a bigger quantity than"
                    + "there is available");
        } else {
            int reservedStock = dummyDB.productList.get(product.getId()).getReservedQuantity()
                    + product.getQuantity();
            dummyDB.productList.get(product.getId()).setReservedQuantity(reservedStock);
            ProductDto productDto = createProductDto(dummyDB.productList.get(product.getId()));
            productDto.setQuantity(product.getQuantity());
            return productDto;
        }
    }

    private ProductDto createProductDto(ProductDb productDb) {
        return new ProductDto(productDb.getId(), productDb.getName(), productDb.getQuantityInStock());
    }

    private int availableStock(ProductDto productDto) {
        return dummyDB.productList.get(productDto.getId()).getQuantityInStock()
                -dummyDB.productList.get(productDto.getId()).getReservedQuantity();
    }
}
