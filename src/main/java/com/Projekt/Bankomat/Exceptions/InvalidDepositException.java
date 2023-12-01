package com.Projekt.Bankomat.Exceptions;

import com.Projekt.Bankomat.Enums.DepositStatus;
import com.Projekt.Bankomat.Service.DepositService;

import java.time.LocalDate;

public class InvalidDepositException extends RuntimeException{
    public InvalidDepositException(){
        super("Deposit not created!");
    }

    public InvalidDepositException(DepositStatus depositStatus){
        super ("Deposit is already " +depositStatus.toString());
    }

    public InvalidDepositException(LocalDate localDate){
        super("Deposit can be withdraw at: " + localDate);
    }
}
