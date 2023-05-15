package com.banking.accountledger.enums;

public enum ETransactionType {

    CREDIT( "CREDIT"),
    DEBIT( "DEBIT");
    private final String description;

    ETransactionType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
