package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.TypKonta;
import com.Projekt.Bankomat.Enums.TypWaluty;
import com.Projekt.Bankomat.Exceptions.NrKontaNotFoundException;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.Models.KontoBankowe;
import com.Projekt.Bankomat.Models.Transakcja;
import com.Projekt.Bankomat.Models.Uzytkownik;
import com.Projekt.Bankomat.Repository.KontoBankoweRepo;
import com.Projekt.Bankomat.Repository.TransakcjaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class KontoBankoweService {
    private final KontoBankoweRepo kontoBankoweRepo;

    @Autowired
    public KontoBankoweService(KontoBankoweRepo kontoBankoweRepo) {
        this.kontoBankoweRepo = kontoBankoweRepo;
    }
    public void usunKonto(String nrKonta){
        var konto = kontoBankoweRepo.findByNrKonta(nrKonta).orElseThrow(NrKontaNotFoundException::new);
        if(konto.getSaldo().compareTo(BigDecimal.ZERO) > 0){
            System.out.println("NA KONCIE POZOSTALY PIENIADZE, PRZELEJ JE PRZED USUNIECIEM KONTA");
            throw new RuntimeException();
        }
        kontoBankoweRepo.delete(konto);
    }
    public void utworzKonto(Uzytkownik uzytkownik, TypKonta typKonta, TypWaluty waluta){
        var konto = KontoBankowe.builder()
                .idKonta(UUID.randomUUID().toString())
                .nrKonta(Generators.generateUniqueRandomDigitNumber(26))
                .saldo(BigDecimal.valueOf(0))
                .typKonta(typKonta)
                .uzytkownik(uzytkownik)
                .waluta(waluta)
                .build();
        kontoBankoweRepo.save(konto);
    }
}
