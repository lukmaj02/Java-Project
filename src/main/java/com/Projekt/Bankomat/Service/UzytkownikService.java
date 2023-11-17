package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.DtoModels.UzytkownikDto;
import com.Projekt.Bankomat.Exceptions.EmailUzytkownikaNotFound;
import com.Projekt.Bankomat.Exceptions.UzytkownikExistsException;
import com.Projekt.Bankomat.Models.KontoBankowe;
import com.Projekt.Bankomat.Models.Uzytkownik;
import com.Projekt.Bankomat.Repository.UzytkownikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UzytkownikService implements IUzytkownikService {
    private final UzytkownikRepo uzytkownikRepo;
    @Autowired
    public UzytkownikService(UzytkownikRepo uzytkownikRepo) {
        this.uzytkownikRepo = uzytkownikRepo;
    }

    public Optional<Uzytkownik> wyswietlDaneUzytkownika(String email){
         var uzytkownik = uzytkownikRepo.findByEmail(email);
         if(uzytkownik.isEmpty())
         {
             throw  new EmailUzytkownikaNotFound();
         }
         return uzytkownik;
    }

    public Optional<List<Uzytkownik>> wyswietlDaneWszytskichUzytkownikow(){
        var uzytkownicy = uzytkownikRepo.findAll();
        return Optional.of(uzytkownicy);
    }


    public void edytujDaneUzytkownika(String email, UzytkownikDto uzytkownikDto){
        var uzytkownik = uzytkownikRepo.findByEmail(email).orElseThrow(EmailUzytkownikaNotFound::new);
        //todo
    }
    public Set<KontoBankowe> wyswietlKontaBankoweUzytkownika(String email){
        var uzytkownik = uzytkownikRepo.findByEmail(email).orElseThrow(EmailUzytkownikaNotFound::new);
        return uzytkownik.getKontoBankowe();
    }

    public void zarejestrujUzytkownika(RegistrarionRequest registraionRequest){
        if(uzytkownikRepo.existsByEmail(registraionRequest.getEmail()) || uzytkownikRepo.existsByNrTelefonu(registraionRequest.getNrTelefonu())){
            throw new UzytkownikExistsException(registraionRequest.getEmail(), registraionRequest.getNrTelefonu());
        }
        var uzytkownik = Uzytkownik.builder()
                .idUzytkownika(UUID.randomUUID().toString())
                .adres(registraionRequest.getAdres())
                .dataUrodzenia(registraionRequest.getDataUrodzenia())
                .imie(registraionRequest.getImie())
                .nazwisko(registraionRequest.getNazwisko())
                .haslo(registraionRequest.getHaslo())
                .email(registraionRequest.getEmail())
                .nrTelefonu(registraionRequest.getNrTelefonu())
                .build();
        uzytkownikRepo.save(uzytkownik);
    }
    public void usunUzytkownika(String email){
        var uzytkownik = uzytkownikRepo.findByEmail(email).orElseThrow(EmailUzytkownikaNotFound::new);
        uzytkownikRepo.deleteByEmail(email);
        //todo
    }
}
