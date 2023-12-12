package Bank.Repository;

import Bank.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, String> {
    @Query(value = "SELECT t FROM _TRANSACTION t join t.fromAccountNr a join a.user u WHERE u.email = ?1")
    List<Transaction> findAllTransactionsSentByUser(String email);

    @Query(value = "SELECT t FROM _TRANSACTION t WHERE t.isValid = TRUE and t.toAccountNr in " +
            "(SELECT k.accountNr from BANK_ACCOUNT k JOIN user u WHERE u.email = ?1)")
    List<Transaction> findAllSuccessfullyTransactionsSentToUser(String email);

}
