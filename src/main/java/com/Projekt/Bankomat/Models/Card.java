package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PAYMENT_CARD")
public class Card {
    @Id
    @UuidGenerator
    @Column(name = "cardId", nullable = false)
    private String cardId;

    @Column(name = "cardNr",nullable = false, length = 16)
    private String cardNr;

    @Column(name = "expirationDate", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "cvc", nullable = false, length = 3)
    private String cvc;

    @Column(name = "cardType", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name ="isDiscard", nullable = false)
    private boolean isDiscard = true;

    @ManyToOne
    @JoinColumn(name = "accountId")
    @JsonIgnore
    private BankAccount bankAccount;

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", cardNr='" + cardNr + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvc='" + cvc + '\'' +
                ", cardType=" + cardType +
                ", isDiscard=" + isDiscard +
                '}';
    }
}
