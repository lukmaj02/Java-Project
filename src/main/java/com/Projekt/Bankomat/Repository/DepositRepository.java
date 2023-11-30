package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, String> {
}
