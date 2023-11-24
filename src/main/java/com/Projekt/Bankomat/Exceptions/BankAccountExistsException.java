package com.Projekt.Bankomat.Exceptions;

public class BankAccountExistsException extends RuntimeException{
    public BankAccountExistsException(){
        super("Konto/Konta istnieją!");
    }
}
