package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.CardType;
import com.Projekt.Bankomat.Models.BankAccount;

public interface ICardService {
    void createCard(String AccountNr, CardType cardType);

}
