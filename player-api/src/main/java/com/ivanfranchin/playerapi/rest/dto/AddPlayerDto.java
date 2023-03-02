package com.ivanfranchin.playerapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddPlayerDto extends BasePlayerDto {

    @Schema(example = "Ivan")
    @NotBlank
    private String playerName;

    @Schema(example = "1000000000000000000")
    @NotNull
    private BigInteger playerPrice;

    @Schema(example = "http://...")
    @NotBlank
    private String image;
}
