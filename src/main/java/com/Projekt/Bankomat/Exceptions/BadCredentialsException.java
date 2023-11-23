package com.Projekt.Bankomat.Exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(){
        super("ERROR,Wprowadzone dane są blędne");
    }

}
