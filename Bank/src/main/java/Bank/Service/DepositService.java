package Bank.Service;


import Bank.Enums.DepositType;
import Bank.Exceptions.DepositNotFoundException;
import Bank.Exceptions.InvalidDepositException;
import Bank.IService.IDepositService;
import Bank.Models.Deposit;
import Bank.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static Bank.Enums.DepositStatus.*;


@Service
@Transactional
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
    public BigDecimal viewProfit(DepositType depositType, BigDecimal amount) {
        var poweredAmount = amount.multiply(depositType.getPercentage());
        return poweredAmount.subtract(amount);
    }

    @Override
    public List<Deposit> getUserDeposits(String email) {
        return depositRepository.getAllUserDeposits(email);
    }
}
