package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.CreditStatus;
import com.Projekt.Bankomat.Enums.CreditType;
import com.Projekt.Bankomat.Exceptions.CreditNotFoundExecption;
import com.Projekt.Bankomat.Exceptions.InvalidCreditCancelling;
import com.Projekt.Bankomat.Exceptions.InvalidWithdrawException;
import com.Projekt.Bankomat.IService.ICreditService;
import com.Projekt.Bankomat.Models.Credit;
import com.Projekt.Bankomat.Repository.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
@Transactional
public class CreditService implements ICreditService {
    private final CreditRepo creditRepo;
    private final BankAccountService bankAccountService;

    @Autowired
    public CreditService(CreditRepo creditRepo, BankAccountService bankAccountService) {
        this.creditRepo = creditRepo;
        this.bankAccountService = bankAccountService;
    }

    @Override
    @Transactional
    public void requestCredit(String accountNr, BigDecimal creditAmount, Integer installmentCount, CreditType creditType) {
        var account = bankAccountService.getAccountByAccountNr(accountNr);
        var credit = Credit.builder()
                .creditId(UUID.randomUUID().toString())
                .installmentCount(installmentCount)
                .lendingRate(creditType.getLendingRate())
                .creditAmount(creditAmount)
                .creditStatus(CreditStatus.PROCESSED)
                .creditType(creditType)
                .installmentAmount(calculateInstallment(creditAmount.doubleValue(),creditType.getLendingRate().doubleValue(), installmentCount))
                .bankAccountCredit(account)
                .build();
        creditRepo.save(credit);
    }

    @Override
    @Transactional
    public void activeCredit(String creditId) {
        var credit = creditRepo.findById(creditId).orElseThrow(CreditNotFoundExecption::new);
        if(credit.getCreditStatus().equals(CreditStatus.PROCESSED)){
            credit.setCreditStatus(CreditStatus.ACTIVE);
            bankAccountService.paymentToAccount(credit.getBankAccountCredit(),credit.getCreditAmount());
            creditRepo.save(credit);
        }
    }

    @Override
    public void refuseCredit(String creditId) {
        var credit = creditRepo.findById(creditId).orElseThrow(CreditNotFoundExecption::new);
        if(credit.getCreditStatus().equals(CreditStatus.PROCESSED)){
            credit.setCreditStatus(CreditStatus.REFUSED);
            creditRepo.save(credit);
        }
    }

    @Override
    public void reactiveCredit(String creditId) {
        var credit = creditRepo.findById(creditId).orElseThrow(CreditNotFoundExecption::new);
        if(credit.getCreditStatus().equals((CreditStatus.FAILED))){
            credit.setCreditStatus(CreditStatus.ACTIVE);
            creditRepo.save(credit);
        }
    }

    @Override
    public void cancelCredit(String creditId) {
        var credit = creditRepo.findById(creditId).orElseThrow(CreditNotFoundExecption::new);
        if(!credit.getCreditStatus().equals((CreditStatus.PROCESSED))) throw new InvalidCreditCancelling();
        credit.setCreditStatus(CreditStatus.CANCELLED);
        creditRepo.save(credit);
    }

    @Override
    public BigDecimal checkInstallmentRate(BigDecimal creditAmount, Integer installmentCount, CreditType creditType) {
        return calculateInstallment(creditAmount.doubleValue(),creditType.getLendingRate().doubleValue(), installmentCount);
    }

    @Override
    public List<Credit> getUserCredits(String email) {
        return creditRepo.getAllUserCredits(email);
    }

    protected BigDecimal calculateInstallment(double creditAmount,
                                           double lendingRate,
                                            int installmentCount) {
        double rata = (creditAmount * lendingRate) / (12 * (1 - Math.pow(12 /(12 +lendingRate),installmentCount)));
        return BigDecimal.valueOf(rata).setScale(2, RoundingMode.HALF_UP);
    }

    @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
    @Transactional
    protected void creditsDay(){
        var credits = creditRepo.findAllByCreditStatus(CreditStatus.ACTIVE);
        for (Credit credit: credits) {
            try{
                bankAccountService.withdrawFromAccount(credit.getBankAccountCredit(), credit.getInstallmentAmount());
                if(credit.getInstallmentCount()==1) credit.setCreditStatus(CreditStatus.FINISHED);
                credit.setInstallmentCount(credit.getInstallmentCount()-1);
            }
            catch(InvalidWithdrawException e){
                credit.setCreditStatus(CreditStatus.FAILED);
            }
            finally {
                creditRepo.save(credit);
            }
        }
    }
}
