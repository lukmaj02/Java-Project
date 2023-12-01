package com.Projekt.Bankomat.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Getter
@AllArgsConstructor
public enum CreditType {
    CONST(BigDecimal.valueOf(0.08));

    private final BigDecimal lendingRate;
    private static BigDecimal getLendingRateForCredit(CreditType creditType){
        return creditType.getLendingRate();
    }
}
