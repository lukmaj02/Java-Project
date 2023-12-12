package Bank.Repository;

import Bank.Enums.DepositStatus;
import Bank.Models.Deposit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, String> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE DEPOSIT d SET d.depositStatus = ?1 where d.depositId = ?2")
    void changeDepositStatus(DepositStatus depositStatus, String depositId);

    @Query(value = "SELECT d FROM DEPOSIT d JOIN d.bankAccountDeposit b JOIN b.user u WHERE u.email = ?1")
    List<Deposit> getAllUserDeposits(String email);
}
