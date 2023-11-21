package com.Projekt.Bankomat.Exceptions;

public class AccountNumberNotFoundException extends RuntimeException{
    public AccountNumberNotFoundException(){
        super("Numeru Konta nie znaloziono");
    }
}
