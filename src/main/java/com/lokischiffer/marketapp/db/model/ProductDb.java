package com.lokischiffer.marketapp.db.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDb {

    private long id;
    private String name;
    private int quantityInStock;
    private int reservedQuantity;
}
