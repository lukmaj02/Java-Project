package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
    boolean existsByAccountNr(String nrKonta);
    Optional<BankAccount> findByAccountNr(String nrKonta);
    @Query(value = "SELECT k FROM BANK_ACCOUNT k JOIN user u " +
            "WHERE u.email = ?1")
    List<BankAccount> findUserBankAccount(String email);

    @Query(value = "SELECT k.accountNr FROM BANK_ACCOUNT k JOIN user u WHERE k.accountType = ?1 and k.currencyType = ?2 and u.email = ?3")
    Optional<String> getAccountNrByUserPhoneNumber(String accountType, String currencyType, String email);
}
