package com.Projekt.Bankomat.DtoModels;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarionRequest {
    private String imie;
    private String nazwisko;
    private String email;
    private String haslo;
    private String nrTelefonu;
    private LocalDate dataUrodzenia;
    private String adres;
}
