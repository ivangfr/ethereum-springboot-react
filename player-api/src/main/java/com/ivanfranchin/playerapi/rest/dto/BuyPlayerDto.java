package com.ivanfranchin.playerapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class BuyPlayerDto extends BasePlayerDto {

    @Schema(example = "1")
    @NotNull
    @Positive
    private BigInteger playerId;

    @Schema(example = "1000000000000000000")
    @NotNull
    @Positive
    private BigInteger weiValue;
}
