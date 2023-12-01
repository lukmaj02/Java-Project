package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Enums.CreditStatus;
import com.Projekt.Bankomat.Models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepo extends JpaRepository <Credit, String> {
    List<Credit> findAllByCreditStatus(CreditStatus creditStatus);
}
