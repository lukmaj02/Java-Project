package com.Projekt.Bankomat.Exceptions;

public class InvalidAccountDeletionException extends RuntimeException{
    public InvalidAccountDeletionException(){
        super("Konto nie zostało usunięte, na koncie pozostały pieniadze");
    }
}
