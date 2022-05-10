package com.lokischiffer.marketapp.logic.service.checkout;

import com.lokischiffer.marketapp.logic.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class CheckoutService extends AbstractCheckoutService<ProductDto> {

    public ProductDto checkoutCreation(ProductDto product) {
        return createInternal(product);
    }

    public ProductDto checkoutAddition(ProductDto product) {
        return addInternal(product);
    }

    public ProductDto updateQuantity(long id, ProductDto product) {
        return updateQuantityInternal(id, product);
    }
}
