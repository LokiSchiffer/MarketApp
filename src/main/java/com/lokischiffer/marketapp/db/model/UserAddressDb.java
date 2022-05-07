package com.lokischiffer.marketapp.db.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressDb {

    private long id;
    private String addressName;
    private int addressNumber;
}
