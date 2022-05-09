package com.lokischiffer.marketapp.logic.service.checkout;

import com.lokischiffer.marketapp.logic.dto.ProductDto;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class CheckoutService extends AbstractCheckoutService<ProductDto, UserDto> {

    public ProductDto checkoutCreation(ProductDto product, UserDto user) {
        return createInternal(product, user);
    }
}
