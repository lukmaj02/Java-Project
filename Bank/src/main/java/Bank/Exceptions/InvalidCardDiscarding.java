package Bank.Exceptions;

public class InvalidCardDiscarding extends RuntimeException {
    public InvalidCardDiscarding(){
        super("Card is already discarded!");
    }
}
