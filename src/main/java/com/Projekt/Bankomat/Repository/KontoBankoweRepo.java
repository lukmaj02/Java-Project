package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.KontoBankowe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KontoBankoweRepo extends JpaRepository<KontoBankowe,String> {
    boolean existsByNrKonta(String nrKonta);
    Optional<KontoBankowe> findByNrKonta(String nrKonta);
}
