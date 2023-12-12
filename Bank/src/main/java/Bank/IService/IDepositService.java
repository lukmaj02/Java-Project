package Bank.IService;

import Bank.Enums.DepositType;
import Bank.Models.Deposit;

import java.math.BigDecimal;
import java.util.List;

public interface IDepositService {
    void createDeposit(String accountNr, DepositType depositType, BigDecimal amount);
    void suspendDeposit(String depositId);
    void finishDeposit(String depositId);
    BigDecimal viewProfit(DepositType depositType, BigDecimal amount);
    List<Deposit> getUserDeposits(String email);
}
