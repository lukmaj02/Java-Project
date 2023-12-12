package Bank.Exceptions;

import Bank.Controller.Commands.MainCommand;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(){
        super("Invalid command!");
    }
    public InvalidCommandException(MainCommand mainCommand){
        super("Invalid command in " + mainCommand.toString());
    }
}
