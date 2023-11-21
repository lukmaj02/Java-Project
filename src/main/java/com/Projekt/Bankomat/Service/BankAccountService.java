package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.AccountType;
import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Exceptions.BankAccountNotFoundException;
import com.Projekt.Bankomat.Exceptions.AccountNumberNotFoundException;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.User;
import com.Projekt.Bankomat.Repository.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;

    @Autowired
    public BankAccountService(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
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

    public void deleteAccount(String nrKonta){
        var konto = bankAccountRepo.findByAccountNr(nrKonta).orElseThrow(AccountNumberNotFoundException::new);
        if(konto.getBalance().compareTo(BigDecimal.ZERO) > 0){
            System.out.println("NA KONCIE POZOSTALY PIENIADZE, PRZELEJ JE PRZED USUNIECIEM KONTA");
            throw new RuntimeException();
        }
        bankAccountRepo.delete(konto);
    }
    public void createAccount(User user, AccountType accountType, CurrencyType waluta){
        var konto = BankAccount.builder()
                .accountId(UUID.randomUUID().toString())
                .accountNr(Generators.generateRandomNumber(26))
                .balance(BigDecimal.valueOf(0))
                .accountType(accountType)
                .user(user)
                .currencyType(waluta)
                .build();
        bankAccountRepo.save(konto);
    }
}
