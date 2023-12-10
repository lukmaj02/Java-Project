package com.Projekt.Bankomat.IService;

import com.Projekt.Bankomat.Enums.CardType;
import com.Projekt.Bankomat.Models.Card;

import java.math.BigDecimal;
import java.util.List;

public interface ICardService {
    void createCard(String AccountNr, CardType cardType);
    void discardCard(String cardNr);
    void deleteCard(String cardNr);
    List<Card> getAccountCards(String accountNr);
    void extendExpirationDate(String cardNr);
    void paymentByCard(String toAccountNr,
                       String fromAccountNr,
                       String cvc,
                       String userFirstname,
                       String userLastname,
                       BigDecimal amount);
}
