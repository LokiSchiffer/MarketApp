package com.lokischiffer.marketapp.web.controller;

import com.lokischiffer.marketapp.logic.dto.ProductDto;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import com.lokischiffer.marketapp.logic.service.checkout.CheckoutService;
import com.lokischiffer.marketapp.logic.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WebController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping (value = "/login")
    @ResponseStatus (HttpStatus.ACCEPTED)
    @ResponseBody
    public UserDto userLogin(@RequestBody @Valid UserDto user) {
        UserDto newUser = loginService.loginUser(user);
        System.out.println(newUser);
        return newUser;
    }

    @PostMapping (value = "/checkout/creation/")
    @ResponseStatus (HttpStatus.CREATED)
    @ResponseBody
    public ProductDto createCheckout(@RequestBody @Valid ProductDto product) {
        ProductDto newProduct = checkoutService.checkoutCreation(product);
        System.out.println("Checkout creation method");
        return newProduct;
    }

    @PutMapping (value = "/checkout/update/{id}")
    @ResponseStatus (HttpStatus.OK)
    @ResponseBody
    public ProductDto updateProductQuantity(@PathVariable("id") final long id,
                                            @RequestBody @Valid ProductDto product) {
        ProductDto newProduct = checkoutService.updateQuantity(id, product);
        System.out.println("Product quantity modification method");
        return newProduct;
    }

    @DeleteMapping (value = "/checkout/deletion")
    @ResponseStatus (HttpStatus.OK)
    public void removeProduct() {
        System.out.println("Product remove method");
    }

    @PostMapping (value = "/checkout/address")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void setAddress() {
        System.out.println("Address for delivery set to checkout");
    }

    @PutMapping (value = "/checkout/address/{address}")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void updateAddress(@PathVariable("address") final String address) {
        System.out.println("Address for delivery set to " + address);
    }

    @PostMapping (value = "/checkout/payment")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void setPaymentMethod() {
        System.out.println("Method for payment set to order");
    }

    @PutMapping (value = "/checkout/payment/{payMethod}")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void updatePaymentMethod(@PathVariable("payMethod") final String payMethod) {
        System.out.println("Method for payment set to " + payMethod);
    }
    @GetMapping (value = "/checkout")
    @ResponseStatus (HttpStatus.OK)
    public void obtainCheckoutInfo() {
        System.out.println("Method for displaying checkout info");
    }
}
