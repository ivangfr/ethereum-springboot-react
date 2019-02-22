package com.mycompany.ethereumapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetWalletAddressDto {

    @ApiModelProperty(example = "123")
    @NotBlank
    private String password;

    @ApiModelProperty(position = 2, example = "/path/to/UTC...")
    @NotBlank
    private String file;

}
