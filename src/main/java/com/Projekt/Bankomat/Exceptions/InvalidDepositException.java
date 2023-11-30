package com.Projekt.Bankomat.Exceptions;

public class InvalidDepositException extends RuntimeException{
    public InvalidDepositException(){
        super("Deposit not created!");
    }
}
