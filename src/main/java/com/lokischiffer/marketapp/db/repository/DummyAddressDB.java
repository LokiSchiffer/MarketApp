package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.UserAddressDb;

import java.util.HashMap;
import java.util.Map;

public class DummyAddressDB {

    public Map<Long, UserAddressDb> addressList;

    public DummyAddressDB() {
        addressList = new HashMap<>();
        fillAddressesDb();
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
}
