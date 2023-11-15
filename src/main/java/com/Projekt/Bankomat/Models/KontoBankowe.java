package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypKonta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "KONTO_BANKOWE")
public class KontoBankowe {
    @Id
    @UuidGenerator
    private String idKonta;

    @Column(name = "numerKonta", nullable = false)
    private String numerKonta;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "typKonta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypKonta typKonta;

    @OneToMany(mappedBy = "kontoBankowe")
    private Set<KartaPlatnicza> kartyPlatnicze;

    @ManyToOne
    @JoinColumn(name = "idUzytkownika")
    private Uzytkownik uzytkownik;

    @ManyToMany
    @JoinTable(
            name = "kontoTransakcja",
            joinColumns = @JoinColumn(name = "idKonta"),
            inverseJoinColumns = @JoinColumn(name = "idTransakcji")
    )
    private Set<Transakcja> transakcje;

    public KontoBankowe(String idKonta,
                        String numerKonta,
                        BigDecimal saldo,
                        TypKonta typKonta,
                        Uzytkownik uzytkownik) {
        this.idKonta = idKonta;
        this.numerKonta = numerKonta;
        this.saldo = saldo;
        this.typKonta = typKonta;
        this.uzytkownik = uzytkownik;
    }
}
