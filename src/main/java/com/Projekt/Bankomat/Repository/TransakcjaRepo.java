package com.Projekt.Bankomat.Repository;

import com.Projekt.Bankomat.Models.Transakcja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface TransakcjaRepo extends JpaRepository<Transakcja, String> {
    Set<Transakcja> findAllByzKonta(String zKonta);
    Set<Transakcja> findAllByDoKonta(String doKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.zKonta =?1 and t.status = TRUE")
    List<Transakcja> znajdzNieWyslanePomyslnieZKonta(String zKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.zKonta =?1 and t.status = FALSE")
    List<Transakcja> znajdzWyslanePomyslnieZKonta(String zKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.doKonta =?1 and t.status = TRUE")
    List<Transakcja> znajdzWyslanePomyslnieDoKonta(String doKonta);

    @Query(value = "SELECT t FROM TRANSAKCJA t WHERE t.zKonta = ?1")
    List<Transakcja> znajdzWszystkieTransakcjeZKonta(String zKonta);
}
