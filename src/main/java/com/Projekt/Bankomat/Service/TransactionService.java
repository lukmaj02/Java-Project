package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.TransactionType;
import com.Projekt.Bankomat.Exceptions.InvalidTransactionException;
import com.Projekt.Bankomat.Models.Transaction;
import com.Projekt.Bankomat.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements ITransactionService{
    private final TransactionRepo transactionRepo;
    private final BankAccountService bankAccountService;
    @Autowired
    public TransactionService(TransactionRepo transactionRepo, BankAccountService bankAccountService) {
        this.transactionRepo = transactionRepo;
        this.bankAccountService = bankAccountService;
    }

    @Transactional
    public void createTransaction(String fromAccountNr,
                                  String toAccountNr,
                                  BigDecimal amount,
                                  String title,
                                  CurrencyType currency,
                                  TransactionType transactionType) {

        if(transactionType.equals(TransactionType.BLIK)){
            toAccountNr = bankAccountService.getAccountNrByUserPhoneNumber(toAccountNr, currency);
        }

        var transakcja = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionDate(LocalDate.now())
                .title(title)
                .amount(amount)
                .toAccountNr(fromAccountNr)
                .fromAccountNr(toAccountNr)
                .currencyType(currency)
                .transactionType(transactionType)
                .isValid(bankAccountService.isPaymentValid(fromAccountNr,toAccountNr,amount,currency))
                .build();
        transactionRepo.save(transakcja);
        if(!transakcja.isValid()) throw new InvalidTransactionException();

    }

    public List<Transaction> getAllNotSuccessfullySentTransactionsFromBankAccount(String fromAccountNr){
            return transactionRepo.findAllNotSuccessfullySentTransactionsFromBankAccount(fromAccountNr);
        }

    public List<Transaction> getAllSuccessfullySentTransactionsFromBankAccount(String FromAccountNr){
            return transactionRepo.findAllSuccessfullyTransactionsFromBankAccount(FromAccountNr);
        }

    public List<Transaction> getAllTransactionFromAccount(String fromAccountNr){
        return transactionRepo.findAllTransactionsFromBankAccount(fromAccountNr);
    }

    public List<Transaction> getAllSuccessfullySentTransactionsToBankAccount(String toAccountNr){
        return transactionRepo.findAllSuccessfullySentTransactionsToBankAccount(toAccountNr);
    }

    public List<Transaction> getAllTransactionsSentByUser(String email){
        return transactionRepo.findAllTransactionsSentByUser(email);
    }

    public List<Transaction> getAllTransactionsSentToUser(String email){
        return transactionRepo.findAllSuccessfullyTransactionsSentToUser(email);
    }

    public List<Transaction> getAllUserTransactions(String email) {
        var t1 = getAllTransactionsSentByUser(email);
        var t2 = getAllTransactionsSentToUser(email);
        t1.addAll(t2);
        return t1;
    }
}
