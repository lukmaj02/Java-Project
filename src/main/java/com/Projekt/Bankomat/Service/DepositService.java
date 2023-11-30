package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.DepositStatus;
import com.Projekt.Bankomat.Enums.DepositType;
import com.Projekt.Bankomat.Exceptions.InvalidDepositException;
import com.Projekt.Bankomat.Models.BankAccount;
import com.Projekt.Bankomat.Models.Deposit;
import com.Projekt.Bankomat.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class DepositService implements IDepositService{
    private final BankAccountService bankAccountService;
    private final DepositRepository depositRepository;

    @Autowired
    public DepositService(BankAccountService bankAccountService, DepositRepository depositRepository) {
        this.bankAccountService = bankAccountService;
        this.depositRepository = depositRepository;
    }

    @Override
    @Transactional
    public void createDeposit(String accountNr, DepositType depositType, BigDecimal amount) {
        var account = bankAccountService.getAccountByAccountNr(accountNr);
        if(account.getBalance().compareTo(amount) < 0) throw new InvalidDepositException();
        account.setBalance(account.getBalance().subtract(amount));
        var deposit = Deposit.builder()
                .depositId(UUID.randomUUID().toString())
                .depositType(depositType)
                .creationDate(LocalDate.now())
                .finishDate(LocalDate.now().plusYears(depositType.getYears()))
                .amount(amount)
                .depositStatus(DepositStatus.ACTIVE)
                .currencyType(account.getCurrencyType())
                .bankAccountDeposit(account)
                .build();
        depositRepository.save(deposit);
    }
    @Override
    public void suspendDeposit(String depositId) {

    }

    @Override
    public void finishDeposit(String depositId) {

    }
}
