package Bank.Exceptions;

public class InvalidCreditCancelling extends RuntimeException {
    public InvalidCreditCancelling(){
        super("Cannot cancel credit");
    }
}
