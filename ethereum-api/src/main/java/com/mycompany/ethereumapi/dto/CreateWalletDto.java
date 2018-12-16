package com.mycompany.ethereumapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class CreateWalletDto {

    @ApiModelProperty(example = "123")
    @NotNull
    @NotEmpty
    private String password;

    @ApiModelProperty(position = 2, example = "10000000000000000000")
    @NotNull
    private BigInteger initialBalance;

}
