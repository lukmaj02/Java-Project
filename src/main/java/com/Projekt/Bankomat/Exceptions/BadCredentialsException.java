package com.Projekt.Bankomat.Exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(){
        super("Wprowadzone dane są blędne");
    }

}
