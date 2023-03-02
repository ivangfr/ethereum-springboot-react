package com.ivanfranchin.ethereumapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class CreateWalletDto {

    @Schema(example = "123")
    @NotBlank
    private String password;

    @Schema(example = "10000000000000000000")
    @NotNull
    private BigInteger initialBalance;
}
