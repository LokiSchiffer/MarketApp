package com.lokischiffer.marketapp.db.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDb {

    private long id;
    private String email;
    private String name;
    private String password;

    public UserDb(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
