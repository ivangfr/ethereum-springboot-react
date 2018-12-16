package com.mycompany.ethereumapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GetWalletAddressDto {

    @ApiModelProperty(example = "123")
    @NotNull
    @NotEmpty
    private String password;

    @ApiModelProperty(position = 2, example = "/path/to/UTC...")
    @NotNull
    @NotEmpty
    private String file;

}
