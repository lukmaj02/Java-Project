package com.Projekt.Bankomat.Exceptions;

public class InvalidWithdrawException extends RuntimeException{
    public InvalidWithdrawException(){
        super("Na koncie nie ma wystarczających pieniędzy");
    }
}
