package com.Projekt.Bankomat.Exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String nrKarty) {
        super("ERROR,Karty z numerem %s nie znaleziono");
    }
}
