package com.Projekt.Bankomat.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("user did not found");
    }
}
