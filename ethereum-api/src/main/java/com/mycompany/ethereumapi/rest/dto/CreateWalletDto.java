package com.mycompany.ethereumapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
