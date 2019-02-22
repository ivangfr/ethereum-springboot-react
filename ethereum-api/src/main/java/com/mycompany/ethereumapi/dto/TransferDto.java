package com.mycompany.ethereumapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class TransferDto {

    @ApiModelProperty(example = "0x...")
    @NotBlank
    private String fromAddress;

    @ApiModelProperty(position = 2, example = "0x...")
    @NotBlank
    private String toAddress;

    @ApiModelProperty(position = 3, example = "10000000000000000000")
    @NotNull
    @Positive
    private BigInteger amount;

    @ApiModelProperty(position = 4, example = "20000000000")
    @NotNull
    @Positive
    private BigInteger gasPrice;

    @ApiModelProperty(position = 5, example = "21000")
    @NotNull
    @Positive
    private BigInteger gasLimit;

}
