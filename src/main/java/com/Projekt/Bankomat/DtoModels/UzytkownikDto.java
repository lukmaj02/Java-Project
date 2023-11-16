package com.Projekt.Bankomat.DtoModels;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UzytkownikDto {
private String imie;
private String nazwisko;
private String email;
private String haslo;
private String nrTelefonu;
private String adres;
}
