package com.ivanfranchin.ethereumapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetWalletAddressDto {

    @Schema(example = "123")
    @NotBlank
    private String password;

    @Schema(example = "/path/to/UTC...")
    @NotBlank
    private String file;
}
