package Bank.IService;

import Bank.Enums.CurrencyType;
import Bank.Enums.TransactionType;
import Bank.Models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    void createTransaction(String fromAccountNr,
                                  String toAccountNr,
                                  BigDecimal amount,
                                  String title,
                                  CurrencyType currency,
                                  TransactionType transactionType);
    List<Transaction> getAllTransactionsSentByUser(String email);
    List<Transaction> getAllTransactionsSentToUser(String email);
    List<Transaction> getAllUserTransactions(String email);
}
