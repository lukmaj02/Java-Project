package Bank.Exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String phoneNumber){
        super("User with Phone Number " + phoneNumber + " already exists");
    }
    public UserExistsException(String email, String nrTelefonu){
        super("User with email: " + email + " or phone number: " + nrTelefonu + " exists");
    }
}
