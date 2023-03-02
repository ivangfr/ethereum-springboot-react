package com.ivanfranchin.ethereumapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigInteger;

@Data
public class DeployContractDto {

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
