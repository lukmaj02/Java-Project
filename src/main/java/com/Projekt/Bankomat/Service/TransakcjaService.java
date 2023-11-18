package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.TypTranskacji;
import com.Projekt.Bankomat.Enums.TypWaluty;
import com.Projekt.Bankomat.Exceptions.BlednaTransakcjaException;
import com.Projekt.Bankomat.Exceptions.IdTransakcjiNotFoundException;
import com.Projekt.Bankomat.Models.Transakcja;
import com.Projekt.Bankomat.Repository.TransakcjaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class TransakcjaService {
    private final TransakcjaRepo transakcjaRepo;
    private final ExecutorService executorService;
    private final KontoBankoweService kontoBankoweService;
    @Autowired
    public TransakcjaService(TransakcjaRepo transakcjaRepo, ExecutorService executorService, KontoBankoweService kontoBankoweService) {
        this.transakcjaRepo = transakcjaRepo;
        this.executorService = executorService;
        this.kontoBankoweService = kontoBankoweService;
    }

    @Transactional
    public void utworzTransakcje(String zNrKonta,
                                 String doNrKonta,
                                 BigDecimal kwota,
                                 String tytul,
                                 TypWaluty waluta,
                                 TypTranskacji typTranskacji){
        var transakcja = Transakcja.builder()
                .idTransakcji(UUID.randomUUID().toString())
                .dataTransakcji(LocalDate.now())
                .tytul(tytul)
                .kwota(kwota)
                .zKonta(zNrKonta)
                .doKonta(doNrKonta)
                .waluta(waluta)
                .typTranskacji(typTranskacji)
                .status(kontoBankoweService.czyPlatnoscPoprawna(zNrKonta,doNrKonta,kwota,waluta))
                .build();
        transakcjaRepo.save(transakcja);
        if(!transakcja.isStatus()) throw new BlednaTransakcjaException();

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

    public Future<List<Transakcja>> wyslaneTransakcjeUzytkownika(String email){
        return executorService.submit(() ->{
            Thread.sleep(1000);
            return transakcjaRepo.znajdzWszystkieWyslaneTransakcjeUzytkownika(email);
        });
    }

    public Future<List<Transakcja>> odebraneTransakcjeUzytkownika(String email){
        return executorService.submit(() ->{
            Thread.sleep(1000);
            return transakcjaRepo.znajdzWszystkieOdebraneTransakcjeUzytkownika(email);
        });
    }

    public List<Transakcja> wszystkieTransakcjeUzytkownika(String email) throws ExecutionException, InterruptedException {
        var future1 = wyslaneTransakcjeUzytkownika(email);
        var future2 = odebraneTransakcjeUzytkownika(email);
        var transakcje = future1.get();
        transakcje.addAll(future2.get());
        return transakcje;
    }
}
