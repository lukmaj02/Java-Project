package Bank.Service;

import Bank.Enums.CurrencyType;
import Bank.Enums.TransactionType;
import Bank.Exceptions.InvalidWithdrawException;
import Bank.IService.ITransactionService;
import Bank.Models.Transaction;
import Bank.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Service
public class TransactionService implements ITransactionService {
    private final TransactionRepo transactionRepo;
    private final BankAccountService bankAccountService;
    private final ExecutorService executorService;
    @Autowired
    public TransactionService(TransactionRepo transactionRepo, BankAccountService bankAccountService, ExecutorService executorService) {
        this.transactionRepo = transactionRepo;
        this.bankAccountService = bankAccountService;
        this.executorService = executorService;
    }

    public void createTransaction(String fromAccountNr,
                                  String toAccountNr,
                                  BigDecimal amount,
                                  String title,
                                  CurrencyType currency,
                                  TransactionType transactionType) {

        if(transactionType.equals(TransactionType.BLIK)){
            toAccountNr = bankAccountService.getAccountNrByUserPhoneNumber(toAccountNr, currency);
        }

        var transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionDate(LocalDate.now())
                .title(title)
                .amount(amount)
                .toAccountNr(toAccountNr)
                .isValid(false)
                .fromAccountNr(bankAccountService.getAccountByAccountNr(fromAccountNr))
                .currencyType(currency)
                .transactionType(transactionType)
                .build();
        try{
            bankAccountService.payment(fromAccountNr,toAccountNr,amount);
            transaction.setValid(true);
        } catch (InvalidWithdrawException e){
            throw new InvalidWithdrawException();
        } finally {
            transactionRepo.save(transaction);
        }
    }
    public List<Transaction> getAllTransactionsSentByUser(String email) throws ExecutionException, InterruptedException {
        FutureTask<List<Transaction>> futureTransaction = new FutureTask<>(() ->
                transactionRepo.findAllTransactionsSentByUser(email));
        executorService.submit(futureTransaction);
        return futureTransaction.get();
    }

    public List<Transaction> getAllTransactionsSentToUser(String email) throws ExecutionException, InterruptedException {
        FutureTask<List<Transaction>> futureTransaction = new FutureTask<>(() ->
                transactionRepo.findAllSuccessfullyTransactionsSentToUser(email));
        executorService.submit(futureTransaction);
        return futureTransaction.get();
    }

    public List<Transaction> getAllUserTransactions(String email) throws ExecutionException, InterruptedException {
        var t1 = getAllTransactionsSentByUser(email);
        var t2 = getAllTransactionsSentToUser(email);
        t1.addAll(t2);
        return t1;
    }
}
