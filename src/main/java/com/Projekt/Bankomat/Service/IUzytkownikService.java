package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.DtoModels.UzytkownikDto;
import com.Projekt.Bankomat.Models.KontoBankowe;
import com.Projekt.Bankomat.Models.Uzytkownik;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUzytkownikService {
    Optional<Uzytkownik> wyswietlDaneUzytkownika(String email);
    Optional<List<Uzytkownik>> wyswietlDaneWszytskichUzytkownikow();
    void edytujDaneUzytkownika(String email, UzytkownikDto uzytkownikDto);
    Set<KontoBankowe> wyswietlKontaBankoweUzytkownika(String email);
    void zarejestrujUzytkownika(RegistrarionRequest registraionRequest);
    void usunUzytkownika(String email);
}
