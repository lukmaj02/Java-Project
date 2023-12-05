package com.Projekt.Bankomat.Exceptions;

public class BankAccountExistsException extends RuntimeException{
    public BankAccountExistsException(){
        super("Account(s) exists!");
    }
}
