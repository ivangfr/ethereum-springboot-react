package com.mycompany.playerapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@AllArgsConstructor
@Data
public class PlayerDto {

    private BigInteger id;
    private String name;
    private BigInteger price;
    private String image;
    private Boolean forSale;
    private String agent;
}
