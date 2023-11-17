package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.TypTranskacji;
import com.Projekt.Bankomat.Enums.TypWaluty;
import com.Projekt.Bankomat.Exceptions.IdTransakcjiNotFoundException;
import com.Projekt.Bankomat.Models.Transakcja;
import com.Projekt.Bankomat.Repository.TransakcjaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class TransakcjaService {
    private final TransakcjaRepo transakcjaRepo;
    private final ExecutorService executorService;
    @Autowired
    public TransakcjaService(TransakcjaRepo transakcjaRepo, ExecutorService executorService) {
        this.transakcjaRepo = transakcjaRepo;
        this.executorService = executorService;
    }

    public void wyslijTransakcje(TypTranskacji typTranskacji,
                                 BigDecimal kwota,
                                 TypWaluty waluta,
                                 String zKonta,
                                 String doKonta,
                                 boolean stauts){

        var transakcja = Transakcja.builder()
                .idTransakcji(UUID.randomUUID().toString())
                .dataTransakcji(LocalDate.now())
                .kwota(kwota)
                .zKonta(zKonta)
                .doKonta(doKonta)
                .waluta(waluta)
                .status(stauts)
                .typTranskacji(typTranskacji)
                .build();
        transakcjaRepo.save(transakcja);
    }
    public void ponowWyslanieTransakcje(String idTransakcji){
        var transakcja = transakcjaRepo.findById(idTransakcji).orElseThrow(IdTransakcjiNotFoundException::new);
        transakcja.setStatus(true);
        transakcjaRepo.save(transakcja);
    }

    public Future<List<Transakcja>> pomyslnieWyslaneTransakcjeZKonta(String zKonta){
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return transakcjaRepo.znajdzWyslanePomyslnieZKonta(zKonta);
        });

    }

    public Future<List<Transakcja>> niePomyslnieWyslaneTransakcjeZKonta(String zKonta){
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return transakcjaRepo.znajdzNieWyslanePomyslnieZKonta(zKonta);
        });
    }

    public Future<List<Transakcja>> wszystkieTransakcjeZKonta(String zKonta){
        return executorService.submit(() ->{
            Thread.sleep(1000);
            return transakcjaRepo.znajdzWszystkieTransakcjeZKonta(zKonta);
        });
    }

    public Future<List<Transakcja>> pomyslnieWyslaneTransakcjeDoKonta(String doKonta){
        return executorService.submit(() -> {
            Thread.sleep(1000);
            return transakcjaRepo.znajdzWyslanePomyslnieDoKonta(doKonta);
        });
    }
}
