package Bank.Exceptions;

import Bank.Enums.CreditStatus;
import Bank.Enums.DepositStatus;

import java.math.BigDecimal;

public class InvalidAccountDeletionException extends RuntimeException{
    public InvalidAccountDeletionException(BigDecimal amount){
        super("Invalid account deletion! Your current balance equals: " + amount.toString() +
                ". Withdraw it first");
    }
    public InvalidAccountDeletionException(DepositStatus depositStatus){
        super("Invalid account deletion! There are still active deposits");
    }

    public InvalidAccountDeletionException(CreditStatus creditStatus) {
        super("Invalid account deletion! There are still active credits!");
    }
}
