package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.TransactionType;
import com.Projekt.Bankomat.Models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    void createTransaction(String fromAccountNr,
                                  String toAccountNr,
                                  BigDecimal amount,
                                  String title,
                                  CurrencyType currency,
                                  TransactionType transactionType);
    List<Transaction> getAllNotSuccessfullySentTransactionsFromBankAccount(String fromAccountNr);
    List<Transaction> getAllSuccessfullySentTransactionsFromBankAccount(String FromAccountNr);
    List<Transaction> getAllTransactionFromAccount(String fromAccountNr);
    List<Transaction> getAllSuccessfullySentTransactionsToBankAccount(String toAccountNr);
    List<Transaction> getAllTransactionsSentByUser(String email);
    List<Transaction> getAllTransactionsSentToUser(String email);
    List<Transaction> getAllUserTransactions(String email);
}
