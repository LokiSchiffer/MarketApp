package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.logic.dto.PaymentMethodDto;
import com.lokischiffer.marketapp.logic.dto.ProductDto;
import com.lokischiffer.marketapp.logic.dto.UserAddressDto;
import com.lokischiffer.marketapp.logic.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DummyDB {

    public Map<String, UserDto> userList;
    public Map<Long, ProductDto> productList;
    public Map<Long, UserAddressDto> addressList;
    public Map<Long, PaymentMethodDto> paymentMethodList;

    public DummyDB() {
        userList = new HashMap<>();
        productList = new HashMap<>();
        addressList = new HashMap<>();
        paymentMethodList = new HashMap<>();
        createUsersDb();
        fillProductDb();
        fillAddressesDb();
        fillPaymentList();
    }

    private void createUsersDb() {
        UserDto user = UserDto.builder().email("loki@mail.com").name("Norberto Mosquera")
                .password("NorbertoMosquera").build();
        userList.put(user.getEmail(), user);
        user = UserDto.builder().email("nicmoseli@mail.com").name("Nicolas Mosquera")
                .password("nicmoseli890918").build();
        userList.put(user.getEmail(), user);
        user = UserDto.builder().email("mariajose@mail.com").name("Maria Jose Mosquera")
                .password("MajitoMosquera").build();
        userList.put(user.getEmail(), user);
    }

    private void fillProductDb() {
        ProductDto product = ProductDto.builder().id(1).name("Cookies").quantity(20).build();
        productList.put(product.getId(), product);
        product = ProductDto.builder().id(2).name("Milk").quantity(30).build();
        productList.put(product.getId(), product);
        product = ProductDto.builder().id(3).name("Eggs").quantity(10).build();
        productList.put(product.getId(), product);
    }

    private void fillAddressesDb() {
        UserAddressDto address = UserAddressDto.builder().id(1001).addressName("Main Street")
                .addressNumber(152).build();
        addressList.put(address.getId(), address);
        address = UserAddressDto.builder().id(1002).addressName("Boulevard street")
                .addressNumber(94).build();
        addressList.put(address.getId(), address);
        address = UserAddressDto.builder().id(1003).addressName("42nd street")
                .addressNumber(67).build();
        addressList.put(address.getId(), address);
    }

    private void fillPaymentList() {
        PaymentMethodDto paymentMethod = PaymentMethodDto.builder().id(101).type("Credit card")
                .funds(5000).build();
        paymentMethodList.put(paymentMethod.getId(), paymentMethod);
        paymentMethod = PaymentMethodDto.builder().id(102).type("Cash")
                .funds(10000).build();
        paymentMethodList.put(paymentMethod.getId(), paymentMethod);
        paymentMethod = PaymentMethodDto.builder().id(103).type("Check")
                .funds(2000).build();
        paymentMethodList.put(paymentMethod.getId(), paymentMethod);
    }
}
