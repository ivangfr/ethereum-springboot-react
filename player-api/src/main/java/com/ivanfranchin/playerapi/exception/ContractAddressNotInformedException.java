package com.ivanfranchin.playerapi.exception;

public class ContractAddressNotInformedException extends RuntimeException {

    public ContractAddressNotInformedException() {
        super("'ethereum.contract.soccermanager.address' not informed");
    }
}
