package com.mycompany.ethereumapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class TransferDto {

    @Schema(example = "0x...")
    @NotBlank
    private String fromAddress;

    @Schema(example = "0x...")
    @NotBlank
    private String toAddress;

    @Schema(example = "10000000000000000000")
    @NotNull
    @Positive
    private BigInteger amount;

    @Schema(example = "20000000000")
    @NotNull
    @Positive
    private BigInteger gasPrice;

    @Schema(example = "21000")
    @NotNull
    @Positive
    private BigInteger gasLimit;
}
