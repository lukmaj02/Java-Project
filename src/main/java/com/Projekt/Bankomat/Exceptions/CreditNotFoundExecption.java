package com.Projekt.Bankomat.Exceptions;

public class CreditNotFoundExecption extends RuntimeException{
    public CreditNotFoundExecption(){
        super("Credit not found!");
    }
}
