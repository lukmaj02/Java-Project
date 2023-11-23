package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.AccountType;
import com.Projekt.Bankomat.Enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BANK_ACCOUNT")
public class BankAccount {
    @Id
    @UuidGenerator
    private String accountId;

    @Column(name = "accountNr", nullable = false, length = 26)
    private String accountNr;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currencyType", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "accountType", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(
            mappedBy = "bankAccount",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Card> cards;

    @ManyToOne
    @JoinColumn(name = "userId")
    //@JsonBackReference
    private User user;
}
