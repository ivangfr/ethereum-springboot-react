package com.mycompany.playerapi.exception;

public class ContractAddressNotInformedException extends RuntimeException {

    public ContractAddressNotInformedException() {
        super("'ethereum.contract.soccermanager.address' not informed");
    }
}
