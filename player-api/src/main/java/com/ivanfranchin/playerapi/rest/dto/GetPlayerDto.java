package com.ivanfranchin.playerapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetPlayerDto extends BasePlayerDto {

    @Schema(example = "1")
    @NotNull
    @Positive
    private BigInteger playerId;
}
