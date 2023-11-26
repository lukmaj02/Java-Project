package com.Projekt.Bankomat.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User nie znaleziony");
    }
}
