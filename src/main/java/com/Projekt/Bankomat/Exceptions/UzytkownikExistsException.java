package com.Projekt.Bankomat.Exceptions;

public class UzytkownikExistsException extends RuntimeException{
    public UzytkownikExistsException(String email, String nrTelefonu){
        super("Uzytkownik o emailu = " + email + " lub telefonie = " + nrTelefonu + " istnieje");
    }
}
