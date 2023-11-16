package com.Projekt.Bankomat.Exceptions;

public class NrKontaNotFoundException extends RuntimeException{
    public NrKontaNotFoundException(){
        super("Numeru Konta nie znaloziono");
    }
}
