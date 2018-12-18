package com.mycompany.playerapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddPlayerDto extends BasePlayerDto {

    @ApiModelProperty(position = 5, example = "Ivan")
    @NotNull
    @NotEmpty
    private String playerName;

    @ApiModelProperty(position = 6, example = "1000000000000000000")
    @NotNull
    @Positive
    private BigInteger playerPrice;

    @ApiModelProperty(position = 7, example = "http://...")
    @NotNull
    @NotEmpty
    private String image;

}
