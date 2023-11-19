package com.Projekt.Bankomat.Exceptions;

public class KontoBankoweExistsException extends RuntimeException{
    public KontoBankoweExistsException(){
        super("Konto/Konta istnieją!");
    }
}
