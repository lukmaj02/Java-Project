package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Exceptions.NrKontaNotFoundException;
import com.Projekt.Bankomat.Models.Transakcja;
import com.Projekt.Bankomat.Repository.KontoBankoweRepo;
import com.Projekt.Bankomat.Repository.TransakcjaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class KontoService {
    private final KontoBankoweRepo kontoBankoweRepo;
    private final TransakcjaRepo transakcjaRepo;
    @Autowired
    public KontoService(KontoBankoweRepo kontoBankoweRepo, TransakcjaRepo transakcjaRepo) {
        this.kontoBankoweRepo = kontoBankoweRepo;
        this.transakcjaRepo = transakcjaRepo;
    }

    public void usunKonto(String nrKonta){
        var konto = kontoBankoweRepo.findByNrKonta(nrKonta).orElseThrow(NrKontaNotFoundException::new);
        if(konto.getSaldo().compareTo(BigDecimal.ZERO) > 0){
            System.out.println("NA KONCIE POZOSTALY PIENIADZE, PRZELEJ JE PRZED USUNIECIEM KONTA");
            throw new RuntimeException();
        }
    }

    public Set<Transakcja> pomyslnieWyslaneTransakcje(String zKonta){
        return transakcjaRepo.znajdzWyslanePomyslnieZKonta(zKonta);
    }

    public Set<Transakcja> niePomyslnieWyslaneTransakcje(String zKonta){
        return transakcjaRepo.znajdzNieWyslanePomyslnieZKonta(zKonta);
    }

    public Set<Transakcja> wszystkieTransakcje(String zKonta){
        return transakcjaRepo.znajdzWszystkieTransakcjeZKonta(zKonta);
    }

}
