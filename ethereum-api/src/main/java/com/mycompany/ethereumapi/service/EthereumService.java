package com.mycompany.ethereumapi.service;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public interface EthereumService {

    List<String> getAccounts() throws IOException;

    String createWallet(String password) throws Exception;

    Credentials getWallet(String password, String file) throws IOException, CipherException;

    BigInteger getWalletBalance(String address) throws IOException;

    EthSendTransaction transfer(String fromAddress, String toAddress, BigInteger amount, BigInteger gasPrice, BigInteger gasLimit) throws IOException;

    String deploySoccerManagerContract(String password, String file, BigInteger gasPrice, BigInteger gasLimit) throws Exception;
}
