package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Enums.CreditType;

import java.math.BigDecimal;

public interface ICreditService {
    void requestCredit(String accountNr,
                       BigDecimal creditAmount,
                       Integer installmentCount,
                       CreditType creditType);

    void activeCredit(String creditId);
    void refuseCredit(String creditId);
    void reactiveCredit(String creditId);
}
