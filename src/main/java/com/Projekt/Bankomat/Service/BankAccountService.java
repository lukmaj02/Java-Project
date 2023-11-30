package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.AccountType;
import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.DepositType;
import com.Projekt.Bankomat.Exceptions.BankAccountNotFoundException;
import com.Projekt.Bankomat.Exceptions.InvalidAccountDeletionException;
import com.Projekt.Bankomat.Exceptions.InvalidDepositException;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Repository.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountService implements IBankAccountService{
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
        if(acc.isEmpty()) throw new BankAccountNotFoundException();
        return acc.get(0).getAccountNr();
    }

    public boolean isPaymentValid(String zNrKonta, String doNrKonta, BigDecimal kwota, CurrencyType waluta){
        var zKonta = bankAccountRepo.findByAccountNr(zNrKonta)
                .orElseThrow(() -> new BankAccountNotFoundException(zNrKonta));

        var doKonta = bankAccountRepo.findByAccountNr(doNrKonta)
                .orElseThrow(() -> new BankAccountNotFoundException(zNrKonta));

        if(!(waluta.equals(doKonta.getCurrencyType())
                && waluta.equals(zKonta.getCurrencyType())
                && zKonta.getBalance().compareTo(kwota) >= 0)) return false;

        zKonta.setBalance(zKonta.getBalance().subtract(kwota));
        doKonta.setBalance(doKonta.getBalance().add(kwota));
        bankAccountRepo.save(zKonta);
        bankAccountRepo.save(doKonta);
        return true;
    }
    @Override
    public List<BankAccount> getUserBankAccounts(String email){
        return bankAccountRepo.getAllUserAccounts(email);
    }
    @Override
    public void deleteAccount(String nrKonta){
        var konto = bankAccountRepo.findByAccountNr(nrKonta).orElseThrow(() -> new BankAccountNotFoundException(nrKonta));
        if(konto.getBalance().compareTo(BigDecimal.ZERO) > 0 || (!konto.getDeposits().isEmpty())){
            throw new InvalidAccountDeletionException();
        }
        bankAccountRepo.delete(konto);
    }
    @Override
    public void createAccount(String email, AccountType accountType, CurrencyType waluta){
        var konto = BankAccount.builder()
                .accountId(UUID.randomUUID().toString())
                .accountNr(Generators.generateRandomNumber(26))
                .balance(BigDecimal.valueOf(0))
                .accountType(accountType)
                .user(userService.getUser(email))
                .currencyType(waluta)
                .build();
        bankAccountRepo.save(konto);
    }
}
