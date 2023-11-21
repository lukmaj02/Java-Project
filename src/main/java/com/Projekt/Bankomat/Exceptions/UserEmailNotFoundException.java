package com.Projekt.Bankomat.Exceptions;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException(){
        super("User nie znaleziony");
    }
}
