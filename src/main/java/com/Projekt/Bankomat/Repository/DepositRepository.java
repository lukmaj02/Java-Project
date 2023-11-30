package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepositRepository extends JpaRepository<Deposit, String> {
    @Query(value = "UPDATE DEPOSIT d " + "Set d.depositStatus = 'SUSPENDED' where d.depositId = ?1")
    void suspendDeposit(String depositId);
}
