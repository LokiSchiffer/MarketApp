package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.PaymentMethodDb;

import java.util.HashMap;
import java.util.Map;

public class DummyPaymentDB {

    public Map<Long, PaymentMethodDb> paymentMethodList;

    public DummyPaymentDB() {
        paymentMethodList = new HashMap<>();
        fillPaymentList();
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
}
