package com.mycompany.playerapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdatePlayerDto extends BasePlayerDto {

    @ApiModelProperty(position = 5, example = "1")
    @NotNull
    @Positive
    private BigInteger playerId;

    @ApiModelProperty(position = 6, example = "true")
    @NotNull
    private Boolean forSale;

}
