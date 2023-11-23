package com.Projekt.Bankomat.Exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String email, String nrTelefonu){
        super("ERROR,User o emailu = " + email + " lub telefonie = " + nrTelefonu + " istnieje");
    }
}
