package com.mycompany.playerapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class BasePlayerDto {

    @ApiModelProperty(example = "123")
    @NotBlank
    private String password;

    @ApiModelProperty(position = 2, example = "/path/to/UTC...")
    @NotBlank
    private String file;

    @ApiModelProperty(position = 3, example = "1")
    @NotNull
    @Positive
    private BigInteger gasPrice;

    @ApiModelProperty(position = 4, example = "3000000")
    @NotNull
    @Positive
    private BigInteger gasLimit;

}
