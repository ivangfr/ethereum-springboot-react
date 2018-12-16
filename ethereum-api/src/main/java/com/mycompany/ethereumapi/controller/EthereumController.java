package com.mycompany.ethereumapi.controller;

import com.mycompany.ethereumapi.dto.CreateWalletDto;
import com.mycompany.ethereumapi.dto.DeployContractDto;
import com.mycompany.ethereumapi.dto.GetWalletAddressDto;
import com.mycompany.ethereumapi.dto.TransferDto;
import com.mycompany.ethereumapi.dto.WalletDto;
import com.mycompany.ethereumapi.service.EthereumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class EthereumController {

    private final EthereumService ethereumService;

    public EthereumController(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @GetMapping("/accounts")
    public List<String> getAccounts() throws IOException {
        return ethereumService.getAccounts();
    }

    @PostMapping("/wallets/create")
    public WalletDto createWallet(@RequestBody CreateWalletDto createWalletDto) throws Exception {
        String file = ethereumService.createWallet(createWalletDto.getPassword());
        String address = ethereumService.getWallet(createWalletDto.getPassword(), file).getAddress();

        if (createWalletDto.getInitialBalance().signum() == 1) {
            String account0Address = ethereumService.getAccounts().get(0);
            ethereumService.transfer(account0Address, address, createWalletDto.getInitialBalance(),
                    BigInteger.valueOf(20000000000L), BigInteger.valueOf(21000L));
        }

        return new WalletDto(file, address);
    }

    @PostMapping("/wallets/get")
    public Credentials getWallet(@RequestBody GetWalletAddressDto getWalletAddressDto) throws IOException, CipherException {
        return ethereumService.getWallet(getWalletAddressDto.getPassword(), getWalletAddressDto.getFile());
    }

    @GetMapping("/wallets/{address}/balance")
    public BigInteger getWalletBalance(@PathVariable String address) throws IOException {
        return ethereumService.getWalletBalance(address);
    }

    @PostMapping("/wallets/transfer")
    public EthSendTransaction transfer(@RequestBody TransferDto transferDto) throws Exception {
        return ethereumService.transfer(
                transferDto.getFromAddress(),
                transferDto.getToAddress(),
                transferDto.getAmount(),
                transferDto.getGasPrice(),
                transferDto.getGasLimit());
    }

    @PostMapping("/contracts/deploy/soccerManager")
    public String deploySoccerManagerContract(@RequestBody DeployContractDto deployContractDto) throws Exception {
        return ethereumService.deploySoccerManagerContract(
                deployContractDto.getPassword(),
                deployContractDto.getFile(),
                deployContractDto.getGasPrice(),
                deployContractDto.getGasLimit());
    }

}
