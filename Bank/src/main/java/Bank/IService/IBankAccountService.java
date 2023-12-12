package Bank.IService;

import Bank.Enums.AccountType;
import Bank.Enums.CurrencyType;
import Bank.Models.BankAccount;

import java.util.List;

public interface IBankAccountService {
    void deleteAccount(String accountNr);
    void createAccount(String email, AccountType accountType, CurrencyType currency);
    List<BankAccount> getUserBankAccounts(String email);
}
