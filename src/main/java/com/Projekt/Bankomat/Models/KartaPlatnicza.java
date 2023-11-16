package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypKarty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "KARTA_PLATNICZA")
public class KartaPlatnicza {
    @Id
    @UuidGenerator
    @Column(name = "idKarty", nullable = false)
    private String idKarty;

    @Column(name = "nrKarty",nullable = false, length = 16)
    private String nrKarty;

    @Column(name = "dataWaznosci", nullable = false)
    private LocalDate dataWaznosci;

    @Column(name = "cvc", nullable = false, length = 3)
    private String cvc;

    @Column(name = "typKarty", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypKarty typKarty;

    @Column(name ="czyZablokowana", nullable = false)
    private boolean czyZablokowana = true;

    @ManyToOne
    @JoinColumn(name = "idKonta")
    private KontoBankowe idKonta;
}
