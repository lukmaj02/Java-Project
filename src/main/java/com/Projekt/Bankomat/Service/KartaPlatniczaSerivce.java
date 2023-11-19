package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.TypKarty;
import com.Projekt.Bankomat.Enums.TypTranskacji;
import com.Projekt.Bankomat.Exceptions.KartaPlatniczaNotFoundException;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.Models.KartaPlatnicza;
import com.Projekt.Bankomat.Models.KontoBankowe;
import com.Projekt.Bankomat.Repository.KartaPlatniczaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class KartaPlatniczaSerivce {
    private final KartaPlatniczaRepo kartaPlatniczaRepo;
    private final TransakcjaService transakcjaService;
    @Autowired
    public KartaPlatniczaSerivce(KartaPlatniczaRepo kartaPlatniczaRepo, TransakcjaService transakcjaService) {
        this.kartaPlatniczaRepo = kartaPlatniczaRepo;
        this.transakcjaService = transakcjaService;
    }

    public void utworzKarte(KontoBankowe kontoBankowe, TypKarty typKarty){
        var nowaKarta = KartaPlatnicza.builder()
                .cvc(Generators.generateRandomNumber(3))
                .dataWaznosci(LocalDate.now().plusYears(4))
                .nrKarty(Generators.generateRandomNumber(16))
                .typKarty(typKarty)
                .idKarty(UUID.randomUUID().toString())
                .czyZablokowana(false)
                .kontoBankowe(kontoBankowe)
                .build();
        kartaPlatniczaRepo.save(nowaKarta);
    }

    public void zablokujKarte(String nrKarty){
        var karta = kartaPlatniczaRepo.findByNrKarty(nrKarty).orElseThrow(() -> new KartaPlatniczaNotFoundException(nrKarty));
        karta.setCzyZablokowana(true);
        kartaPlatniczaRepo.save(karta);
    }

    public void usunKarte(String nrKarty){
        var karta = kartaPlatniczaRepo.findByNrKarty(nrKarty).orElseThrow(() -> new KartaPlatniczaNotFoundException(nrKarty));
        kartaPlatniczaRepo.delete(karta);
    }

    public void przedluzWaznoscKarty(String nrKarty){
        var karta = kartaPlatniczaRepo.findByNrKarty(nrKarty)
                .orElseThrow(() -> new KartaPlatniczaNotFoundException(nrKarty));
        karta.setDataWaznosci(karta.getDataWaznosci().plusYears(3));
    }

    public void platnoscKarta(String doNrKonta,
                              String zNrKarty,
                              String cvc,
                              String imieNadawcy,
                              String nazwiskoNadawcy,
                              BigDecimal kwota){
        var kartaNadawcy = kartaPlatniczaRepo
                .znajdzKarteUzytkownika(zNrKarty,cvc,imieNadawcy,nazwiskoNadawcy)
                .orElseThrow(() -> new KartaPlatniczaNotFoundException(zNrKarty));

        transakcjaService.utworzTransakcje(
                kartaNadawcy.getKontoBankowe().getNrKonta(),
                doNrKonta,
                kwota,
                "Platnosc karta",
                kartaNadawcy.getKontoBankowe().getWaluta(),
                TypTranskacji.PRZELEW_KARTA
                );
    }
}
