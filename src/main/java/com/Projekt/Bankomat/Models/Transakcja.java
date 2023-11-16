package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypTranskacji;
import com.Projekt.Bankomat.Enums.TypWaluty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TRANSAKCJA")
public class Transakcja {
    @Id
    @UuidGenerator
    @Column(name = "idTransakcji", nullable = false)
    private String idTransakcji;

    @Column(name = "typTransakcji", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypTranskacji typTranskacji;

    @Column(name = "status")
    private boolean status=false;

    @Column(name = "dataTransakcji", nullable = false)
    private LocalDate dataTransakcji;

    @Column(name = "kwota", nullable = false)
    private BigDecimal kwota;

    @Column(name = "waluta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypWaluty waluta;

    @Column(name = "tytul")
    private String tytul;

    @Column(name = "doKonta",nullable = false)
    private String doKonta;

    @Column(name = "zKonta", nullable = false)
    private String zKonta;
}
