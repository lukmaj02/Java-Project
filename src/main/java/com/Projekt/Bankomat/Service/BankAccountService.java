package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.AccountType;
import com.Projekt.Bankomat.Enums.CreditStatus;
import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.DepositStatus;
import com.Projekt.Bankomat.Exceptions.*;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.IService.IBankAccountService;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Repository.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.Projekt.Bankomat.Enums.DepositStatus.ACTIVE;

@Service
@Transactional
public class BankAccountService implements IBankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final UserService userService;


    @Autowired
    public BankAccountService(BankAccountRepo bankAccountRepo, UserService userService) {
        this.bankAccountRepo = bankAccountRepo;
        this.userService = userService;
    }

    public BankAccount getAccountByAccountNr(String accountNr){
        return bankAccountRepo.findByAccountNr(accountNr).orElseThrow(BankAccountNotFoundException::new);
    }

    public String getAccountNrByUserPhoneNumber(String phoneNumber, CurrencyType currencyType){
        var acc = bankAccountRepo.getAccountNrByUserPhoneNumber(currencyType, phoneNumber);
        if(acc.isEmpty()) throw new PersonalBankAccountNotFoundException(phoneNumber);
        return acc.get(0).getAccountNr();
    }

    public boolean payment(String fromAccountNr, String toAccountNr, BigDecimal amount){
        var fromAccount = bankAccountRepo.findByAccountNr(fromAccountNr)
                .orElseThrow(() -> new BankAccountNotFoundException(fromAccountNr));

        var toAccount = bankAccountRepo.findByAccountNr(toAccountNr)
                .orElseThrow(() -> new BankAccountNotFoundException(fromAccountNr));

        if(!fromAccount.getCurrencyType().equals(toAccount.getCurrencyType()))
            throw new InvalidCurrencyTypeException(toAccount.getCurrencyType());

        withdrawFromAccount(fromAccount,amount);
        paymentToAccount(toAccount,amount);
        return true;
    }

    public void paymentToAccount(BankAccount bankAccount, BigDecimal amount){
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        bankAccountRepo.save(bankAccount);
    }

    public void withdrawFromAccount(BankAccount bankAccount, BigDecimal amount){
        if(bankAccount.getBalance().compareTo(amount) < 0) throw new InvalidWithdrawException();
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        bankAccountRepo.save(bankAccount);
    }
    @Override
    public List<BankAccount> getUserBankAccounts(String email){
        return bankAccountRepo.getAllUserAccounts(email);
    }

    @Override
    public void createAccount(String email, AccountType accountType, CurrencyType currency){
        var konto = BankAccount.builder()
                .accountId(UUID.randomUUID().toString())
                .accountNr(Generators.generateRandomNumber(26))
                .balance(BigDecimal.valueOf(0))
                .accountType(accountType)
                .user(userService.getUser(email))
                .currencyType(currency)
                .build();
        bankAccountRepo.save(konto);
    }

    @Override
    public void deleteAccount(String accountNr){
        var account = bankAccountRepo.findByAccountNr(accountNr)
                .orElseThrow(() -> new BankAccountNotFoundException(accountNr));
        if(account.getBalance().compareTo(BigDecimal.ZERO) > 0)
            throw new InvalidAccountDeletionException(account.getBalance());
        if(account.getDeposits()
                .stream()
                .anyMatch(deposit -> deposit.getDepositStatus().equals(ACTIVE)))
            throw new InvalidAccountDeletionException(ACTIVE);
        if(account.getCredits()
                .stream()
                .anyMatch(credit -> credit.getCreditStatus().equals(CreditStatus.ACTIVE)
                        || credit.getCreditStatus().equals(CreditStatus.FAILED)))
            throw new InvalidAccountDeletionException(CreditStatus.ACTIVE);

        bankAccountRepo.delete(account);
    }
}
