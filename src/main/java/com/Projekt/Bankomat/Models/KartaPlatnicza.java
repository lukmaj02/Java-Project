package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypKarty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "KARTA_PLATNICZA")
public class KartaPlatnicza {
    @Id
    @UuidGenerator
    @Column(name = "idKarty", nullable = false)
    private String idKarty;

    @Column(name = "numerKarty",nullable = false)
    private Integer numerKarty;

    @Column(name = "dataWaznosci", nullable = false)
    private LocalDate dataWaznosci;

    @Column(name = "cvc", nullable = false, length = 3)
    private Integer cvc;

    @Column(name = "typKarty", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypKarty typKarty;

    @ManyToOne
    @JoinColumn(name = "idKonta")
    private KontoBankowe kontoBankowe;
}
