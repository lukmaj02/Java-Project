package com.Projekt.Bankomat.Exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(){
        super("Wprowadzone dane sa bledne!");
    }
}
