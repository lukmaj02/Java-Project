package com.Projekt.Bankomat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UZYTKOWNIK")
public class Uzytkownik {
    @Id
    @UuidGenerator
    @Column(name = "idUzytkownika", nullable = false)
    private String idUzytkownika;

    @Column(name = "imie", nullable = false)
    private String imie;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "haslo", nullable = false)
    private String haslo;

    @Column(name = "dataUrodzenia", nullable = false)
    private LocalDate dataUrodzenia;

    @Column(name = "nrTelefonu", nullable = false)
    private String nrTelefonu;

    public Uzytkownik(String idUzytkownika,
                      String imie,
                      String nazwisko,
                      String email,
                      String haslo,
                      LocalDate dataUrodzenia,
                      String nrTelefonu) {
        this.idUzytkownika = idUzytkownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.dataUrodzenia = dataUrodzenia;
        this.nrTelefonu = nrTelefonu;
    }

    @OneToMany(mappedBy = "uzytkownik")
    private Set<KontoBankowe> kontoBankowe;
}
