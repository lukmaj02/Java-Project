package com.Projekt.Bankomat.DtoModels;

import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private String fromAccountNr;
    private String toAccountNr;
    private BigDecimal amount;
    private String title;
    private CurrencyType currencyType;
    private TransactionType transactionType;

    @Override
    public String toString() {
        return fromAccountNr + "," +
                toAccountNr + ","  +
                amount + "," +
                title + "," +
                currencyType.toString() + "," +
                transactionType.toString() + ",";
    }
}
