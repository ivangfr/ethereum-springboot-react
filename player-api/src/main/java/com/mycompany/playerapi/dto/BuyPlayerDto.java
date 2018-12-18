package com.mycompany.playerapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class BuyPlayerDto extends BasePlayerDto {

    @ApiModelProperty(position = 5, example = "1")
    @NotNull
    @Positive
    private BigInteger playerId;

    @ApiModelProperty(position = 6, example = "1000000000000000000")
    @NotNull
    @Positive
    private BigInteger weiValue;

}
