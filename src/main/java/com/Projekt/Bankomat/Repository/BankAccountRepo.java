package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
    boolean existsByAccountNr(String nrKonta);
    Optional<BankAccount> findByAccountNr(String nrKonta);
    @Query(value = "SELECT k FROM KONTO_BANKOWE k JOIN user u " +
            "WHERE u.email = ?1")
    List<BankAccount> findUserBankAccount(String email);
}