package com.Projekt.Bankomat.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public enum DepositType {
    ANNUAL(BigDecimal.valueOf(1.03), 1),
    TWO_YEAR(BigDecimal.valueOf(1.0625),2),
    FIVE_YEAR(BigDecimal.valueOf(1.15), 5);

    private final BigDecimal percentage;
    private final int years;

    public static BigDecimal getPercentageForDeposit(DepositType depositType){
        return depositType.getPercentage();
    }
    public static int getYearsForDeposit(DepositType depositType){
        return depositType.getYears();
    }
}
