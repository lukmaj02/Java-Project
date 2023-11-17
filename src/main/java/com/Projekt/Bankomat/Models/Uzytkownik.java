package com.Projekt.Bankomat.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
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

    @Column(name = "nrTelefonu", nullable = false, length = 9)
    private String nrTelefonu;

    @Column(name = "adres")
    private String adres;

    public Uzytkownik(String idUzytkownika,
                      String imie,
                      String nazwisko,
                      String email,
                      String haslo,
                      LocalDate dataUrodzenia,
                      String nrTelefonu,
                      String adres) {
        this.idUzytkownika = idUzytkownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.dataUrodzenia = dataUrodzenia;
        this.nrTelefonu = nrTelefonu;
        this.adres = adres;
    }

    @OneToMany(mappedBy = "uzytkownik", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<KontoBankowe> kontoBankowe;
}
