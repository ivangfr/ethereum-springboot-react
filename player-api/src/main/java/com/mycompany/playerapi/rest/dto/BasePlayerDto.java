package com.mycompany.playerapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class BasePlayerDto {

    @Schema(example = "123")
    @NotBlank
    private String password;

    @Schema(example = "/path/to/UTC...")
    @NotBlank
    private String file;

    @Schema(example = "1")
    @NotNull
    @Positive
    private BigInteger gasPrice;

    @Schema(example = "3000000")
    @NotNull
    @Positive
    private BigInteger gasLimit;
}
