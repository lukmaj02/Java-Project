package com.Projekt.Bankomat.Exceptions;

public class KartaPlatniczaNotFound extends RuntimeException {
    public KartaPlatniczaNotFound(String nrKarty) {
        super("Karty z numerem {nrKarty} nie znaleziono");
    }
}
