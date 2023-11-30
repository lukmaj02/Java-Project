package com.Projekt.Bankomat.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum DepositType {
    ANNUAL(BigDecimal.valueOf(1.03)),
    TWO_YEAR(BigDecimal.valueOf(1.0625)),
    FIVE_YEAR(BigDecimal.valueOf(1.15)),
    SIX_MONTHS(BigDecimal.valueOf(1.0125));

    private final BigDecimal percentage;

    public static BigDecimal getPercentageForDeposit(DepositType depositType){
        return depositType.getPercentage();
    }
}
