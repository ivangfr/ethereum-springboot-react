package com.mycompany.playerapi.controller;

import com.mycompany.playerapi.dto.AddPlayerDto;
import com.mycompany.playerapi.dto.BasePlayerDto;
import com.mycompany.playerapi.dto.BuyPlayerDto;
import com.mycompany.playerapi.dto.GetPlayerDto;
import com.mycompany.playerapi.dto.PlayerDto;
import com.mycompany.playerapi.dto.UpdatePlayerDto;
import com.mycompany.playerapi.service.SoccerManagerService;
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

@RestController
@RequestMapping("/api")
public class PlayerApiController {

    private final SoccerManagerService soccerManagerService;

    public PlayerApiController(SoccerManagerService soccerManagerService) {
        this.soccerManagerService = soccerManagerService;
    }

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

    @ResponseStatus(HttpStatus.OK)
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
        return new PlayerDto(tuple6.getValue1(), tuple6.getValue2(), tuple6.getValue3(),
                tuple6.getValue4(), tuple6.getValue5(), tuple6.getValue6());
    }

    @ResponseStatus(HttpStatus.OK)
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

    @ResponseStatus(HttpStatus.OK)
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/agents/players")
    public List getAgentPlayers(@Valid @RequestBody BasePlayerDto basePlayerDto) throws Exception {
        return soccerManagerService.getPlayersOfAgent(
                basePlayerDto.getPassword(),
                basePlayerDto.getFile(),
                basePlayerDto.getGasPrice(),
                basePlayerDto.getGasLimit());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/players/numberOf")
    public BigInteger getNumberOfPlayers(@Valid @RequestBody BasePlayerDto basePlayerDto) throws Exception {
        return soccerManagerService.getNumberOfPlayers(
                basePlayerDto.getPassword(),
                basePlayerDto.getFile(),
                basePlayerDto.getGasPrice(),
                basePlayerDto.getGasLimit());
    }

}
