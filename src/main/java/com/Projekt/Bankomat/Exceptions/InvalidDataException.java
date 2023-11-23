package com.Projekt.Bankomat.Exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(){
        super("ERROR,Wprowadzone dane sa bledne!");
    }
}
