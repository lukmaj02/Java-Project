package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UzytkownikRepo extends JpaRepository<Uzytkownik,String> {
    boolean existsByNrTelefonu(String nrTelefonu);
    boolean existsByEmail(String email);
    Optional<Uzytkownik> findByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM UZYTKOWNIK u WHERE u.email = ?1")
    void deleteByEmail(String email);
}
