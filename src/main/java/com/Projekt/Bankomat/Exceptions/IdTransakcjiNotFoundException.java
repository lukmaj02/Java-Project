package com.Projekt.Bankomat.Exceptions;

public class IdTransakcjiNotFoundException extends RuntimeException{
    public IdTransakcjiNotFoundException(){
        super("Transakcji nie znaleziono");
    }
}
