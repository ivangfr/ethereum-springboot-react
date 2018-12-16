package com.mycompany.playerapi.controller;

import com.mycompany.playerapi.dto.AddPlayerDto;
import com.mycompany.playerapi.dto.BasePlayerDto;
import com.mycompany.playerapi.dto.BuyPlayerDto;
import com.mycompany.playerapi.dto.GetPlayerDto;
import com.mycompany.playerapi.dto.UpdatePlayerDto;
import com.mycompany.playerapi.service.SoccerManagerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerApiController {

    private final SoccerManagerService soccerManagerService;

    public PlayerApiController(SoccerManagerService soccerManagerService) {
        this.soccerManagerService = soccerManagerService;
    }

    @PostMapping("/players/add")
    public TransactionReceipt addPlayer(@RequestBody AddPlayerDto addPlayerDto) throws Exception {
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
    public Tuple5<String, BigInteger, String, Boolean, String> getPlayer(@RequestBody GetPlayerDto getPlayerDto) throws Exception {
        return soccerManagerService.getPlayer(
                getPlayerDto.getPlayerId(),
                getPlayerDto.getPassword(),
                getPlayerDto.getFile(),
                getPlayerDto.getGasPrice(),
                getPlayerDto.getGasLimit());
    }

    @PostMapping("/players/buy")
    public TransactionReceipt buyPlayer(@RequestBody BuyPlayerDto buyPlayerDto) throws Exception {
        return soccerManagerService.buyPlayer(
                buyPlayerDto.getPlayerId(),
                buyPlayerDto.getWeiValue(),
                buyPlayerDto.getPassword(),
                buyPlayerDto.getFile(),
                buyPlayerDto.getGasPrice(),
                buyPlayerDto.getGasLimit());
    }

    @PostMapping("/players/update")
    public TransactionReceipt updatePlayer(@RequestBody UpdatePlayerDto updatePlayerDto) throws Exception {
        return soccerManagerService.updatePlayer(
                updatePlayerDto.getPlayerId(),
                updatePlayerDto.getForSale(),
                updatePlayerDto.getPassword(),
                updatePlayerDto.getFile(),
                updatePlayerDto.getGasPrice(),
                updatePlayerDto.getGasLimit());
    }

    @PostMapping("/agents/players")
    public List getAgentPlayers(@RequestBody BasePlayerDto basePlayerDto) throws Exception {
        return soccerManagerService.getAgentPlayers(
                basePlayerDto.getPassword(),
                basePlayerDto.getFile(),
                basePlayerDto.getGasPrice(),
                basePlayerDto.getGasLimit());
    }

    @PostMapping("/players/numberOf")
    public BigInteger getNumberOfPlayers(@RequestBody BasePlayerDto basePlayerDto) throws Exception {
        return soccerManagerService.getNumberOfPlayers(
                basePlayerDto.getPassword(),
                basePlayerDto.getFile(),
                basePlayerDto.getGasPrice(),
                basePlayerDto.getGasLimit());
    }

}
