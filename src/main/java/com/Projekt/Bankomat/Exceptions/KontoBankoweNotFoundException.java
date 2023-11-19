package com.Projekt.Bankomat.Exceptions;
public class KontoBankoweNotFoundException extends RuntimeException {
    public KontoBankoweNotFoundException(String nrKonta){
        super("Konto z numerem %s nie znalezione");
    }
}
