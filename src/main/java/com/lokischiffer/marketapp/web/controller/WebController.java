package com.lokischiffer.marketapp.web.controller;

import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WebController {

    @PostMapping (value = "/login")
    @ResponseStatus (HttpStatus.ACCEPTED)
    public void userLogin(@RequestBody @Valid UserDto user) {
        UserDto newUser = user;
        newUser.setEmail("Pruba@gmail.com");
        System.out.println(newUser);
    }

    @PostMapping (value = "/checkout/creation")
    @ResponseStatus (HttpStatus.CREATED)
    public void createCheckout() {
        System.out.println("Checkout creation method");
    }

    @PutMapping (value = "/checkout/update")
    @ResponseStatus (HttpStatus.OK)
    public void updateProductQuantity() {
        System.out.println("Product quantity modification method");
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
