package com.lokischiffer.marketapp.db.repository;

import com.lokischiffer.marketapp.db.model.PaymentMethodDb;
import com.lokischiffer.marketapp.db.model.UserDb;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DummyDB {

    public Map<String, UserDb> userList;

    public DummyDB() {
        userList = new HashMap<>();
        createUsersDb();
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

    public boolean existsByEmail(String email){
        return userList.containsKey(email);
    }

    public UserDb findByEmail(String email) {
        return userList.get(email);
    }
}
