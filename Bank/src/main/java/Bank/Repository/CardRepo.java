package Bank.Repository;

import Bank.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, String> {
    Optional<Card> findByCardNr(String nrKarty);

    @Query(value = "SELECT k FROM PAYMENT_CARD k JOIN bankAccount kb JOIN user u " +
            "WHERE k.cardNr = ?1 and k.cvc = ?2 and u.firstName = ?3 and u.lastName = ?4")
    Optional<Card> findUserCard(String nrKarty,
                                String cvc,
                                String imie,
                                String nazwisko);
    @Query(value = "SELECT c FROM PAYMENT_CARD c JOIN bankAccount b WHERE b.accountNr = ?1")
    List<Card> findAccountsByAccountNr(String accountNr);
}
