package com.Projekt.Bankomat.Exceptions;

public class InvalidWithdrawException extends RuntimeException{
    public InvalidWithdrawException(){
        super("Not enough money");
    }
}
