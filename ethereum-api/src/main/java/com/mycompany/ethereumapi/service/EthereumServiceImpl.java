package com.mycompany.ethereumapi.service;

import com.mycompany.ethereumapi.contract.SoccerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
public class EthereumServiceImpl implements EthereumService {

    private final File walletDirectory;
    private final Web3j web3j;

    public EthereumServiceImpl(Web3j web3j) {
        this.web3j = web3j;
        walletDirectory = new File(System.getProperty("user.dir"));
        log.info("Wallet Directory: {}", walletDirectory);
    }

    @Override
    public List<String> getAccounts() throws IOException {
        EthAccounts ethAccounts = web3j.ethAccounts().send();
        return ethAccounts.getAccounts();
    }

    @Override
    public String createWallet(String password) throws Exception {
        String walletFile = WalletUtils.generateLightNewWalletFile(password, walletDirectory);
        return String.format("%s%s%s", walletDirectory.getAbsolutePath(), File.separator, walletFile);
    }

    @Override
    public Credentials getWallet(String password, String file) throws IOException, CipherException {
        return WalletUtils.loadCredentials(password, file);
    }

    @Override
    public BigInteger getWalletBalance(String address) throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        return ethGetBalance.getBalance();
    }

    @Override
    public EthSendTransaction transfer(String fromAddress, String toAddress, BigInteger amount, BigInteger gasPrice, BigInteger gasLimit) throws IOException {
        EthCoinbase coinbase = web3j.ethCoinbase().send();

        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(
                coinbase.getAddress(),
                DefaultBlockParameterName.LATEST).send();

        Transaction transaction = Transaction.createEtherTransaction(
                fromAddress, transactionCount.getTransactionCount(), gasPrice, gasLimit, toAddress, amount);

        return web3j.ethSendTransaction(transaction).send();
    }

    @Override
    public String deploySoccerManagerContract(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials(password, file);

        ContractGasProvider gasContractProvider = new StaticGasProvider(gasPrice, gasLimit);
        SoccerManager soccerManager = SoccerManager.deploy(web3j, credentials, gasContractProvider).send();

        return soccerManager.getContractAddress();
    }
}
