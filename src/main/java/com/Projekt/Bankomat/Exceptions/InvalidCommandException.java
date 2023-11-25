package com.Projekt.Bankomat.Exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(){
        super("Bledna komenda!");
    }
}
