package com.lokischiffer.marketapp.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product_info")
public class ProductDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantityInStock;

    @Column
    private int reservedQuantity;
}
