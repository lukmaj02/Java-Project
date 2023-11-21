package com.Projekt.Bankomat.Exceptions;
public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String nrKonta){
        super("Konto z numerem %s nie znalezione");
    }
}
