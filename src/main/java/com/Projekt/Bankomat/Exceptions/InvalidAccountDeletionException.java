package com.Projekt.Bankomat.Exceptions;

import com.Projekt.Bankomat.Enums.DepositStatus;

import java.math.BigDecimal;

public class InvalidAccountDeletionException extends RuntimeException{
    public InvalidAccountDeletionException(BigDecimal amount){
        super("Konto nie zostało usunięte! Twoj aktualny stan konta to: " + amount.toString() +
                ". Przelej je przed usunieciem konta");
    }
    public InvalidAccountDeletionException(DepositStatus depositStatus){
        super("Nie mozna usunac konta! Na koncie pozostały aktywne lokaty!");
    }
}
