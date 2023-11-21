package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Enums.CurrencyType;
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

    @Query(value = "SELECT k FROM BANK_ACCOUNT k JOIN k.user u WHERE k.accountType = 'OSOBISTE' and k.currencyType = ?1 and u.phoneNumber = ?2")
    List<BankAccount> getAccountNrByUserPhoneNumber(CurrencyType currencyType, String phoneNumber);
}
