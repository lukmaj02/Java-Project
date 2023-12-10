package com.Projekt.Bankomat.Service;


import com.Projekt.Bankomat.Enums.DepositType;
import com.Projekt.Bankomat.Exceptions.DepositNotFoundException;
import com.Projekt.Bankomat.Exceptions.InvalidDepositException;
import com.Projekt.Bankomat.IService.IDepositService;
import com.Projekt.Bankomat.Models.Deposit;
import com.Projekt.Bankomat.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.Projekt.Bankomat.Enums.DepositStatus.*;


@Service
public class DepositService implements IDepositService {
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
                .depositStatus(ACTIVE)
                .currencyType(account.getCurrencyType())
                .bankAccountDeposit(account)
                .build();
        depositRepository.save(deposit);
    }
    @Override
    @Transactional
    public void suspendDeposit(String depositId) {
        var deposit = depositRepository.findById(depositId).orElseThrow(DepositNotFoundException::new);
        if(!deposit.getDepositStatus().equals(ACTIVE))
            throw new InvalidDepositException(deposit.getDepositStatus());

        depositRepository.changeDepositStatus(SUSPENDED,depositId);
        bankAccountService.paymentToAccount(deposit.getBankAccountDeposit(),deposit.getAmount());
    }

    @Override
    @Transactional
    public void finishDeposit(String depositId) {
        var deposit = depositRepository.findById(depositId).orElseThrow(DepositNotFoundException::new);
        if(!(deposit.getDepositStatus().equals(ACTIVE)))
            throw new InvalidDepositException(deposit.getDepositStatus());
        if(deposit.getFinishDate().isAfter(LocalDate.now()))
            throw new InvalidDepositException(deposit.getFinishDate());

        depositRepository.changeDepositStatus(FINISHED,depositId);
        var poweredAmount = deposit.getAmount().multiply(deposit.getDepositType().getPercentage());
        bankAccountService.paymentToAccount(deposit.getBankAccountDeposit(),poweredAmount);
    }

    @Override
    public List<Deposit> getUserDeposits(String email) {
        return depositRepository.getAllUserDeposits(email);
    }
}
