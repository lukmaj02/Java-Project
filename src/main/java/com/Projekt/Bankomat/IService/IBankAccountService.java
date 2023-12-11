package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Enums.AccountType;
import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Models.BankAccount;

import java.util.List;

public interface IBankAccountService {
    void deleteAccount(String accountNr);
    void createAccount(String email, AccountType accountType, CurrencyType currency);
    List<BankAccount> getUserBankAccounts(String email);
}
