package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.KartaPlatnicza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KartaPlatniczaRepo extends JpaRepository<KartaPlatnicza, String> {
    Optional<KartaPlatnicza> findByNrKarty(String nrKarty);
//    @Query(value = "SELECT k FROM KARTA_PLATNICZA k JOIN kontoBankowe kb " +
//            "WHERE k.nrKarty = ?1 and k.cvc = ?2 and kb.idUzytkownika = " +
//            "(SELECT u.idUzytkownika FROM UZYTKOWNIK u WHERE u.imie = ?3 and u.nazwisko = ?4)")
//    Optional<KartaPlatnicza> znajdzKarteUzytkownika(String nrKarty,
//                                                    String cvc,
//                                                    String imie,
//                                                    String nazwisko);

    @Query(value = "SELECT k FROM KARTA_PLATNICZA k JOIN kontoBankowe kb JOIN uzytkownik u " +
            "WHERE k.nrKarty = ?1 and k.cvc = ?2 and u.imie = ?3 and u.nazwisko = ?4")
    Optional<KartaPlatnicza> znajdzKarteUzytkownika(String nrKarty,
                                                    String cvc,
                                                    String imie,
                                                    String nazwisko);

}
