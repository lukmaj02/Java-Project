package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Enums.DepositStatus;
import com.Projekt.Bankomat.Models.Deposit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DepositRepository extends JpaRepository<Deposit, String> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE DEPOSIT d SET d.depositStatus = ?1 where d.depositId = ?2")
    void changeDepositStatus(DepositStatus depositStatus, String depositId);
}
