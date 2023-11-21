package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, String> {
    Set<Transaction> findAllByFromAccountNr(String zKonta);
    Set<Transaction> findAllByToAccountNr(String doKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.toAccountNr =?1 and t.isValid = TRUE")
    List<Transaction> findAllSuccessfullyTransactionsFromBankAccount(String zKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.toAccountNr =?1 and t.isValid = FALSE")
    List<Transaction> findAllNotSuccessfullySentTransactionsFromBankAccount(String zKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.fromAccountNr =?1 and t.isValid = TRUE")
    List<Transaction> findAllSuccessfullySentTransactionsToBankAccount(String doKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.toAccountNr = ?1")
    List<Transaction> findAllTransactionsFromBankAccount(String zKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.toAccountNr in " +
            "(SELECT k.accountNr from KONTO_BANKOWE k JOIN user u WHERE u.email = ?1)")
    List<Transaction> findAllTransactionsSentByUser(String email);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.isValid = TRUE and t.fromAccountNr in " +
            "(SELECT k.accountNr from KONTO_BANKOWE k JOIN user u WHERE u.email = ?1)")
    List<Transaction> findAllSuccessfullyTransactionsSentToUser(String email);



}
