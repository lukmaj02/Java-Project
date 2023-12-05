package com.Projekt.Bankomat.Exceptions;

import com.Projekt.Bankomat.Models.BankAccount;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String accountNr){
        super("Account with number " + accountNr+ " did not found");
    }
    public BankAccountNotFoundException(){
        super("Account not found");
    }

}
