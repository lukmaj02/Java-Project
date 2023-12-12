package Bank.Exceptions;

public class BankAccountExistsException extends RuntimeException{
    public BankAccountExistsException(){
        super("Account(s) exists!");
    }
}
