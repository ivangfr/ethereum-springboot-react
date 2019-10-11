package com.mycompany.ethereumapi.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetWalletAddressDto {

    @ApiModelProperty(example = "123")
    @NotBlank
    private String password;

    @ApiModelProperty(position = 1, example = "/path/to/UTC...")
    @NotBlank
    private String file;

}
