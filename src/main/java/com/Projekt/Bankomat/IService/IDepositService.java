package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Enums.DepositType;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.Deposit;

import java.math.BigDecimal;
import java.util.List;

public interface IDepositService {
    void createDeposit(String accountNr, DepositType depositType, BigDecimal amount);
    void suspendDeposit(String depositId);
    void finishDeposit(String depositId);
}
