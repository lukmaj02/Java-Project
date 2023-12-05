package com.Projekt.Bankomat.Exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String accountNr) {
        super("Card with number: " + accountNr + "did not found");
    }
}
