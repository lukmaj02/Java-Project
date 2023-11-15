package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Transakcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TransakcjaRepo extends JpaRepository<Transakcja, String> {
}
