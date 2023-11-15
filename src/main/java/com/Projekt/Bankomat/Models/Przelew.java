package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypWaluty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRZELEW")
public class Przelew {
    @Id
    @UuidGenerator
    @Column(name = "idPrzelewu", nullable = false)
    private String idPrzelewu;
    @Column(name = "kwota", nullable = false)
    private BigDecimal kwota;
    @Column(name = "dataPrzelewu", nullable = false)
    private LocalDate dataPrzelewu;
    @Column(name = "waluta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypWaluty waluta;

    @ManyToOne
    @JoinColumn(name = "idTransakcji")
    private Transakcja transakcja;

}
