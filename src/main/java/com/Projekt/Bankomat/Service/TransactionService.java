package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.TransactionType;
import com.Projekt.Bankomat.Exceptions.InvalidTransactionException;
import com.Projekt.Bankomat.Exceptions.TransactionIdNotFoundException;
import com.Projekt.Bankomat.Models.Transaction;
import com.Projekt.Bankomat.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final ExecutorService executorService;
    private final BankAccountService bankAccountService;
    @Autowired
    public TransactionService(TransactionRepo transactionRepo, ExecutorService executorService, BankAccountService bankAccountService) {
        this.transactionRepo = transactionRepo;
        this.executorService = executorService;
        this.bankAccountService = bankAccountService;
    }

    @Transactional
    public void createTransaction(String fromAccountNr,
                                  String toAccountNr,
                                  BigDecimal amount,
                                  String title,
                                  CurrencyType currency,
                                  TransactionType transactionType){

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

    public Future<List<Transaction>> getAllNotSuccessfullySentTransactionsFromBankAccount(String fromAccountNr){
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return transactionRepo.findAllNotSuccessfullySentTransactionsFromBankAccount(fromAccountNr);
        });

    }

    public Future<List<Transaction>> getAllSuccessfullySentTransactionsFromBankAccount(String FromAccountNr){
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return transactionRepo.findAllSuccessfullyTransactionsFromBankAccount(FromAccountNr);
        });
    }

    public Future<List<Transaction>> wszystkieTransakcjeZKonta(String fromAccountNr){
        return executorService.submit(() ->{
            Thread.sleep(1000);
            return transactionRepo.findAllTransactionsFromBankAccount(fromAccountNr);
        });
    }

    public Future<List<Transaction>> getAllSuccessfullySentTransactionsToBankAccount(String toAccountNr){
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return transactionRepo.findAllSuccessfullySentTransactionsToBankAccount(toAccountNr);
        });
    }

    public Future<List<Transaction>> getAllTransactionsSentByUser(String email){
        return executorService.submit(() ->{
            Thread.sleep(1000);
            return transactionRepo.findAllTransactionsSentByUser(email);
        });
    }

    public Future<List<Transaction>> getAllTransactionsSentToUser(String email){
        return executorService.submit(() ->{
            Thread.sleep(1000);
            return transactionRepo.findAllSuccessfullyTransactionsSentToUser(email);
        });
    }

    public List<Transaction> getAllUserTransactions(String email) throws ExecutionException, InterruptedException {
        var future1 = getAllTransactionsSentByUser(email);
        var future2 = getAllTransactionsSentToUser(email);
        var transakcje = future1.get();
        transakcje.addAll(future2.get());
        return transakcje;
    }
}
