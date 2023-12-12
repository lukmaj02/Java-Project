package Bank.Exceptions;

import Bank.Enums.CurrencyType;

public class InvalidCurrencyTypeException extends RuntimeException{
    public InvalidCurrencyTypeException(CurrencyType currencyType){
        super("Invalid currency! Account you're trying to send money has currency type: " + currencyType.toString());
    }
}
