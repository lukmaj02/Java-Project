package com.Projekt.Bankomat.Exceptions;

public class InvalidCreditCancelling extends RuntimeException {
    public InvalidCreditCancelling(){
        super("Cannot cancel credit");
    }
}
