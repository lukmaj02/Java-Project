package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.TypKarty;
import com.Projekt.Bankomat.Exceptions.KartaPlatniczaNotFound;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.Models.KartaPlatnicza;
import com.Projekt.Bankomat.Models.KontoBankowe;
import com.Projekt.Bankomat.Repository.KartaPlatniczaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class KartaPlatniczaSerivce {
    private final KartaPlatniczaRepo kartaPlatniczaRepo;
    @Autowired
    public KartaPlatniczaSerivce(KartaPlatniczaRepo kartaPlatniczaRepo) {
        this.kartaPlatniczaRepo = kartaPlatniczaRepo;
    }

    public void utworzKarte(KontoBankowe kontoBankowe, TypKarty typKarty){
        var nowaKarta = KartaPlatnicza.builder()
                .cvc(Generators.generateUniqueRandomDigitNumber(3))
                .dataWaznosci(LocalDate.now().plusYears(4))
                .nrKarty(Generators.generateUniqueRandomDigitNumber(16))
                .typKarty(typKarty)
                .idKarty(UUID.randomUUID().toString())
                .czyZablokowana(false)
                .build();
        kartaPlatniczaRepo.save(nowaKarta);
    }

    public void zablokujKarte(String nrKarty){
        var karta = kartaPlatniczaRepo.findByNrKarty(nrKarty).orElseThrow(() -> new KartaPlatniczaNotFound(nrKarty));
        karta.setCzyZablokowana(true);
        kartaPlatniczaRepo.save(karta);
    }

    public void usunKarte(String nrKarty){
        var karta = kartaPlatniczaRepo.findByNrKarty(nrKarty).orElseThrow(() -> new KartaPlatniczaNotFound(nrKarty));
        kartaPlatniczaRepo.delete(karta);
    }
}
