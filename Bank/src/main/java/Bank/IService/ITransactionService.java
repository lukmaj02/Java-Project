package Bank.IService;

import Bank.Enums.CurrencyType;
import Bank.Enums.TransactionType;
import Bank.Models.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ITransactionService {
    void createTransaction(String fromAccountNr,
                                  String toAccountNr,
                                  BigDecimal amount,
                                  String title,
                                  CurrencyType currency,
                                  TransactionType transactionType);
    List<Transaction> getAllTransactionsSentByUser(String email) throws ExecutionException, InterruptedException;
    List<Transaction> getAllTransactionsSentToUser(String email) throws ExecutionException, InterruptedException;
    List<Transaction> getAllUserTransactions(String email) throws ExecutionException, InterruptedException;
}
