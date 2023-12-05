package com.Projekt.Bankomat.Exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(){
        super("Transaction was sent unsuccessfully");
    }

}
