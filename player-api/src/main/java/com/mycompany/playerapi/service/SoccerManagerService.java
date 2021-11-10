package com.mycompany.playerapi.service;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;

import java.math.BigInteger;
import java.util.List;

public interface SoccerManagerService {

    Tuple6<BigInteger, String, BigInteger, String, Boolean, String> getPlayer(BigInteger playerId, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;

    TransactionReceipt addPlayer(String name, BigInteger price, String playerImageUrl, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;

    TransactionReceipt buyPlayer(BigInteger playerId, BigInteger weiValue, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;

    TransactionReceipt updatePlayer(BigInteger playerId, Boolean forSale, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;

    List getPlayersOfAgent(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;

    BigInteger getNumberOfPlayers(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;
}
