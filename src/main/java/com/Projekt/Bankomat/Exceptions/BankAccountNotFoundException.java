package com.Projekt.Bankomat.Exceptions;

import com.Projekt.Bankomat.Models.BankAccount;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String nrKonta){
        super("ERROR,Konto z numerem " + nrKonta+ " nie znalezione");
    }
    public BankAccountNotFoundException(){
        super("Brak konta sprawdz ponownie wprowadzone dane");
    }

}
