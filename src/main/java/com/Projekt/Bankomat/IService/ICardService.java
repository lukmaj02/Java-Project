package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Enums.CardType;

import java.math.BigDecimal;

public interface ICardService {
    void createCard(String AccountNr, CardType cardType);
    void discardCard(String nrKarty);
    void deleteCard(String nrKarty);
    void extendExpirationDate(String nrKarty);
    void paymentByCard(String doNrKonta,
                       String zNrKarty,
                       String cvc,
                       String imieNadawcy,
                       String nazwiskoNadawcy,
                       BigDecimal kwota);
}
