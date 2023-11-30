package com.Projekt.Bankomat.Models;

import com.Projekt.Bankomat.Enums.DepositStatus;
import com.Projekt.Bankomat.Enums.DepositType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "DEPOSIT")
public class Deposit {
    @Id
    @UuidGenerator
    @Column(name = "depositId", nullable = false)
    private String depositId;

    @Column(name = "depositType", nullable = false)
    @Enumerated(EnumType.STRING)
    private DepositType depositType;

    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;

    @Column(name = "finishDate", nullable = false)
    private LocalDate finishDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "depositStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private DepositStatus depositStatus;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private BankAccount bankAccountDeposit;

    @Override
    public String toString() {
        return depositId + "," +
                depositType.toString() + "," +
                creationDate.toString() + "," +
                finishDate.toString() + "," +
                amount.toString() + "," +
                depositStatus.toString() + ",";
    }
}
