package com.lokischiffer.marketapp.logic.service;

import com.lokischiffer.marketapp.logic.dto.ProductDto;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class CheckoutService extends AbstractCheckoutService<UserDto> {

    public UserDto loginUser(UserDto user) {
        return loginInternal(user);
    }

    public ProductDto checkoutCreation(ProductDto product, UserDto user) {
        return createInternal(product, user);
    }
}
