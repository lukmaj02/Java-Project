package Bank.Exceptions;

public class InvalidWithdrawException extends RuntimeException{
    public InvalidWithdrawException(){
        super("Not enough money");
    }
}
