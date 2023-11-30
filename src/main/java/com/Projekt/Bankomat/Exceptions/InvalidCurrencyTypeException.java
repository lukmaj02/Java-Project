package com.Projekt.Bankomat.Exceptions;

import com.Projekt.Bankomat.Enums.CurrencyType;
import org.springframework.beans.propertyeditors.CurrencyEditor;

public class InvalidCurrencyTypeException extends RuntimeException{
    public InvalidCurrencyTypeException(CurrencyType currencyType){
        super("Invalid currency! Account you're trying to send money has currency type: " + currencyType.toString());
    }
}
