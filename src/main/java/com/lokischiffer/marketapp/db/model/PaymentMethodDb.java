package com.lokischiffer.marketapp.db.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethodDb {

    private long id;
    private long funds;
    private String type;
}
