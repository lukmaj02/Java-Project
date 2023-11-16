package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.TypKonta;
import com.Projekt.Bankomat.Enums.TypWaluty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "KONTO_BANKOWE")
public class KontoBankowe {
    @Id
    @UuidGenerator
    private String idKonta;

    @Column(name = "nrKonta", nullable = false, length = 26)
    private String nrKonta;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "waluta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypWaluty waluta;

    @Column(name = "typKonta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypKonta typKonta;

    @OneToMany(
            mappedBy = "idKonta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<KartaPlatnicza> kartyPlatnicze;

    @ManyToOne
    @JoinColumn(name = "idUzytkownika")
    private Uzytkownik uzytkownik;

    public KontoBankowe(String idKonta,
                        String nrKonta,
                        BigDecimal saldo,
                        TypKonta typKonta,
                        TypWaluty waluta,
                        Uzytkownik uzytkownik) {
        this.idKonta = idKonta;
        this.nrKonta = nrKonta;
        this.saldo = saldo;
        this.typKonta = typKonta;
        this.waluta = waluta;
        this.uzytkownik = uzytkownik;
    }
}
