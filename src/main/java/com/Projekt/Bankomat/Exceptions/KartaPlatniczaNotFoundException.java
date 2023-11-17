package com.Projekt.Bankomat.Exceptions;

public class KartaPlatniczaNotFoundException extends RuntimeException {
    public KartaPlatniczaNotFoundException(String nrKarty) {
        super("Karty z numerem {nrKarty} nie znaleziono");
    }
}
