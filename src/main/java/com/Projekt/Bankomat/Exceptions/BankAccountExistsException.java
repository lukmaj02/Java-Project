package com.Projekt.Bankomat.Exceptions;

public class BankAccountExistsException extends RuntimeException{
    public BankAccountExistsException(){
        super("ERROR,Konto/Konta istnieją!");
    }
}
