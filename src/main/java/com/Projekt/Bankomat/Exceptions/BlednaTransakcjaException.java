package com.Projekt.Bankomat.Exceptions;

public class BlednaTransakcjaException extends RuntimeException {
    public BlednaTransakcjaException(){
        super("Transakcja wysłana niepomyślnie");
    }

}
