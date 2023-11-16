package com.Projekt.Bankomat.Exceptions;

public class EmailUzytkownikaNotFound extends RuntimeException{
    public EmailUzytkownikaNotFound(){
        super("Uzytkownik nie znaleziony");
    }
}
