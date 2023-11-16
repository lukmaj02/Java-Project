package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.KartaPlatnicza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KartaPlatniczaRepo extends JpaRepository<KartaPlatnicza, String> {
    Optional<KartaPlatnicza> findByNrKarty(String nrKarty);

}
