package com.mycompany.playerapi.service;

import com.mycompany.playerapi.contract.SoccerManager;
import com.mycompany.playerapi.exception.ContractAddressNotInformedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SoccerManagerServiceImpl implements SoccerManagerService {

    private final Web3j web3j;

    private String contractAddress;

    @Value("${ethereum.contract.soccermanager.address}")
    public void setContractAddress(String contractAddress) {
        if (contractAddress.isEmpty()) {
            throw new ContractAddressNotInformedException();
        }
        this.contractAddress = contractAddress;
    }

    @Override
    public Tuple6<BigInteger, String, BigInteger, String, Boolean, String> getPlayer(BigInteger playerId, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        SoccerManager soccerManager = loadSoccerManager(password, file, gasPrice, gasLimit);
        return soccerManager.getPlayer(playerId).send();
    }

    @Override
    public TransactionReceipt addPlayer(String playerName, BigInteger playerPrice, String playerImageUrl, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        SoccerManager soccerManager = loadSoccerManager(password, file, gasPrice, gasLimit);
        return soccerManager.addPlayer(playerName, playerPrice, playerImageUrl).send();
    }

    @Override
    public TransactionReceipt buyPlayer(BigInteger playerId, BigInteger weiValue, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        SoccerManager soccerManager = loadSoccerManager(password, file, gasPrice, gasLimit);
        return soccerManager.buyPlayer(playerId, weiValue).send();
    }

    @Override
    public TransactionReceipt updatePlayer(BigInteger playerId, Boolean forSale, String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        SoccerManager soccerManager = loadSoccerManager(password, file, gasPrice, gasLimit);
        return soccerManager.updatePlayer(playerId, forSale).send();
    }

    @Override
    public List getPlayersOfAgent(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        SoccerManager soccerManager = loadSoccerManager(password, file, gasPrice, gasLimit);
        return soccerManager.getPlayersOfAgent().send();
    }

    @Override
    public BigInteger getNumberOfPlayers(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        SoccerManager soccerManager = loadSoccerManager(password, file, gasPrice, gasLimit);
        return soccerManager.getNumberOfPlayers().send();
    }

    private SoccerManager loadSoccerManager(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws IOException, CipherException {
        Credentials credentials = WalletUtils.loadCredentials(password, file);
        log.debug("Wallet credentials loaded! Address: {}", credentials.getAddress());

        SoccerManager soccerManager = SoccerManager.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
        log.debug("SoccerManager contract loaded! Address: {}", soccerManager.getContractAddress());

        return soccerManager;
    }
}
