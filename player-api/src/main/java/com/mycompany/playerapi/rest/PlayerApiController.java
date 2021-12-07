package com.mycompany.playerapi.rest;

import com.mycompany.playerapi.rest.dto.AddPlayerDto;
import com.mycompany.playerapi.rest.dto.BasePlayerDto;
import com.mycompany.playerapi.rest.dto.BuyPlayerDto;
import com.mycompany.playerapi.rest.dto.GetPlayerDto;
import com.mycompany.playerapi.rest.dto.PlayerDto;
import com.mycompany.playerapi.rest.dto.UpdatePlayerDto;
import com.mycompany.playerapi.service.SoccerManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlayerApiController {

    private final SoccerManagerService soccerManagerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/players/add")
    public TransactionReceipt addPlayer(@Valid @RequestBody AddPlayerDto addPlayerDto) throws Exception {
        return soccerManagerService.addPlayer(
                addPlayerDto.getPlayerName(),
                addPlayerDto.getPlayerPrice(),
                addPlayerDto.getImage(),
                addPlayerDto.getPassword(),
                addPlayerDto.getFile(),
                addPlayerDto.getGasPrice(),
                addPlayerDto.getGasLimit());
    }

    @PostMapping("/players/get")
    public PlayerDto getPlayer(@Valid @RequestBody GetPlayerDto getPlayerDto) throws Exception {
        return mapToPlayerDto(soccerManagerService.getPlayer(
                getPlayerDto.getPlayerId(),
                getPlayerDto.getPassword(),
                getPlayerDto.getFile(),
                getPlayerDto.getGasPrice(),
                getPlayerDto.getGasLimit())
        );
    }

    private PlayerDto mapToPlayerDto(Tuple6<BigInteger, String, BigInteger, String, Boolean, String> tuple6) {
        return new PlayerDto(tuple6.component1(), tuple6.component2(), tuple6.component3(),
                tuple6.component4(), tuple6.component5(), tuple6.component6());
    }

    @PostMapping("/players/buy")
    public TransactionReceipt buyPlayer(@Valid @RequestBody BuyPlayerDto buyPlayerDto) throws Exception {
        return soccerManagerService.buyPlayer(
                buyPlayerDto.getPlayerId(),
                buyPlayerDto.getWeiValue(),
                buyPlayerDto.getPassword(),
                buyPlayerDto.getFile(),
                buyPlayerDto.getGasPrice(),
                buyPlayerDto.getGasLimit());
    }

    @PostMapping("/players/update")
    public TransactionReceipt updatePlayer(@Valid @RequestBody UpdatePlayerDto updatePlayerDto) throws Exception {
        return soccerManagerService.updatePlayer(
                updatePlayerDto.getPlayerId(),
                updatePlayerDto.getForSale(),
                updatePlayerDto.getPassword(),
                updatePlayerDto.getFile(),
                updatePlayerDto.getGasPrice(),
                updatePlayerDto.getGasLimit());
    }

    @PostMapping("/agents/players")
    public List getAgentPlayers(@Valid @RequestBody BasePlayerDto basePlayerDto) throws Exception {
        return soccerManagerService.getPlayersOfAgent(
                basePlayerDto.getPassword(),
                basePlayerDto.getFile(),
                basePlayerDto.getGasPrice(),
                basePlayerDto.getGasLimit());
    }

    @PostMapping("/players/numberOf")
    public BigInteger getNumberOfPlayers(@Valid @RequestBody BasePlayerDto basePlayerDto) throws Exception {
        return soccerManagerService.getNumberOfPlayers(
                basePlayerDto.getPassword(),
                basePlayerDto.getFile(),
                basePlayerDto.getGasPrice(),
                basePlayerDto.getGasLimit());
    }
}
