package com.Projekt.Bankomat.Exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String nrKarty) {
        super("Karty z numerem %s nie znaleziono");
    }
}
