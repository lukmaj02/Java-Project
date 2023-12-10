package com.Projekt.Bankomat.Exceptions;

public class InvalidExtendingValidityCard extends RuntimeException{
    public InvalidExtendingValidityCard(){
        super("Cannot extend validation date, card is still active");
    }
}
