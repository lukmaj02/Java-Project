package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.AccountType;
import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Models.BankAccount;

import java.util.List;

public interface IBankAccountService {
    void deleteAccount(String nrKonta);
    void createAccount(String email, AccountType accountType, CurrencyType waluta);
    List<BankAccount> getUserBankAccounts(String email);
}
