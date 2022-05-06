package com.lokischiffer.marketapp.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressDto {

    private long id;
    private String addressName;
    private int addressNumber;
}
