package Bank.Exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(){
        super("Invalid credentials!");
    }

}
