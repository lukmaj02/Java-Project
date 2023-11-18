package com.Projekt.Bankomat.Exceptions;

public class IllegalWalutaTranzakcjiException extends RuntimeException {
    public IllegalWalutaTranzakcjiException(){
        super("DOCELOWY NR KONTA NIE PRZETWARZA PODANEJ WALUTY!");
    }
}
