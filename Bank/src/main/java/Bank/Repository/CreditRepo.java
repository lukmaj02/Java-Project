package Bank.Repository;

import Bank.Enums.CreditStatus;
import Bank.Models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepo extends JpaRepository <Credit, String> {
    List<Credit> findAllByCreditStatus(CreditStatus creditStatus);
    @Query(value = "SELECT c FROM CREDIT c JOIN c.bankAccountCredit b JOIN b.user u WHERE u.email = ?1")
    List<Credit> getAllUserCredits(String email);
}
