package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.PaymentMethodDb;
import com.lokischiffer.marketapp.db.model.ProductDb;
import com.lokischiffer.marketapp.db.model.UserAddressDb;
import com.lokischiffer.marketapp.db.model.UserDb;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DummyDB {

    public Map<String, UserDb> userList;
    public Map<Long, ProductDb> productList;
    public Map<Long, UserAddressDb> addressList;
    public Map<Long, PaymentMethodDb> paymentMethodList;

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
        UserDb user = UserDb.builder().email("loki@mail.com").name("Norberto Mosquera")
                .password("NorbertoMosquera").build();
        userList.put(user.getEmail(), user);
        user = UserDb.builder().email("nicmoseli@mail.com").name("Nicolas Mosquera")
                .password("nicmoseli890918").build();
        userList.put(user.getEmail(), user);
        user = UserDb.builder().email("mariajose@mail.com").name("Maria Jose Mosquera")
                .password("MajitoMosquera").build();
        userList.put(user.getEmail(), user);
    }

    private void fillProductDb() {
        ProductDb product = ProductDb.builder().id(1).name("Cookies").quantity(20).build();
        productList.put(product.getId(), product);
        product = ProductDb.builder().id(2).name("Milk").quantity(30).build();
        productList.put(product.getId(), product);
        product = ProductDb.builder().id(3).name("Eggs").quantity(10).build();
        productList.put(product.getId(), product);
    }

    private void fillAddressesDb() {
        UserAddressDb address = UserAddressDb.builder().id(1001).addressName("Main Street")
                .addressNumber(152).build();
        addressList.put(address.getId(), address);
        address = UserAddressDb.builder().id(1002).addressName("Boulevard street")
                .addressNumber(94).build();
        addressList.put(address.getId(), address);
        address = UserAddressDb.builder().id(1003).addressName("42nd street")
                .addressNumber(67).build();
        addressList.put(address.getId(), address);
    }

    private void fillPaymentList() {
        PaymentMethodDb paymentMethod = PaymentMethodDb.builder().id(101).type("Credit card")
                .funds(5000).build();
        paymentMethodList.put(paymentMethod.getId(), paymentMethod);
        paymentMethod = PaymentMethodDb.builder().id(102).type("Cash")
                .funds(10000).build();
        paymentMethodList.put(paymentMethod.getId(), paymentMethod);
        paymentMethod = PaymentMethodDb.builder().id(103).type("Check")
                .funds(2000).build();
        paymentMethodList.put(paymentMethod.getId(), paymentMethod);
    }

    public boolean existsByEmail(String email){
        return userList.containsKey(email);
    }

    public UserDb findByEmail(String email) {
        return userList.get(email);
    }
}
