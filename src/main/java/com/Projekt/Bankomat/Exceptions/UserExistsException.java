package com.Projekt.Bankomat.Exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String email, String nrTelefonu){
        super("User with email: " + email + " or phone number: " + nrTelefonu + " exists");
    }
}
