package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypTranskacji;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "transakcja")
    private Set<Przelew> przelewy;

    @ManyToMany(mappedBy = "transakcje")
    Set<KontoBankowe> kontoBankowe;

    public Transakcja(String idTransakcji,
                      TypTranskacji typTranskacji,
                      boolean status,
                      LocalDate data,
                      KontoBankowe kontoBankowe) {
        this.idTransakcji = idTransakcji;
        this.typTranskacji = typTranskacji;
        this.status = status;
        this.data = data;

    }
}
