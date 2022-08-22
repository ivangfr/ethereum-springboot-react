package com.ivanfranchin.playerapi.rest.dto;

import java.math.BigInteger;

public record PlayerDto(BigInteger id, String name, BigInteger price, String image, Boolean forSale, String agent) {
}
