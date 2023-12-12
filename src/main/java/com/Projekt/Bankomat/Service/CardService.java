package com.Projekt.Bankomat.Service;

import com.Projekt.Bankomat.Enums.CardType;
import com.Projekt.Bankomat.Enums.TransactionType;
import com.Projekt.Bankomat.Exceptions.BankAccountNotFoundException;
import com.Projekt.Bankomat.Exceptions.CardNotFoundException;
import com.Projekt.Bankomat.Exceptions.InvalidCardDiscarding;
import com.Projekt.Bankomat.Exceptions.InvalidExtendingValidityCard;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.IService.ICardService;
import com.Projekt.Bankomat.Models.Card;
import com.Projekt.Bankomat.Repository.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CardService implements ICardService {
    private final CardRepo cardRepo;
    private final TransactionService transactionService;
    private final BankAccountService bankAccountService;
    @Autowired
    public CardService(CardRepo cardRepo, TransactionService transactionService, BankAccountService bankAccountService) {
        this.cardRepo = cardRepo;
        this.transactionService = transactionService;
        this.bankAccountService = bankAccountService;
    }

    public void createCard(String accountNr, CardType cardType, String pin) throws BankAccountNotFoundException {
        var nowaKarta = Card.builder()
                .cvc(Generators.generateRandomNumber(3))
                .expirationDate(LocalDate.now().plusYears(4))
                .cardNr(Generators.generateRandomNumber(16))
                .cardType(cardType)
                .cardId(UUID.randomUUID().toString())
                .isDiscard(false)
                .bankAccount(bankAccountService.getAccountByAccountNr(accountNr))
                .pin(pin)
                .build();
        cardRepo.save(nowaKarta);
    }

    public void discardCard(String cardNr){
        var karta = cardRepo.findByCardNr(cardNr).orElseThrow(() -> new CardNotFoundException(cardNr));
        if(karta.isDiscard()) throw new InvalidCardDiscarding();
        karta.setDiscard(true);
        cardRepo.save(karta);
    }
    public void deleteCard(String cardNr){
        var karta = cardRepo.findByCardNr(cardNr).orElseThrow(() -> new CardNotFoundException(cardNr));
        cardRepo.delete(karta);
    }

    public List<Card> getAccountCards(String accountNr) {
        return cardRepo.findAccountsByAccountNr(accountNr);
    }

    public void extendExpirationDate(String cardNr){
        var karta = cardRepo.findByCardNr(cardNr).orElseThrow(() -> new CardNotFoundException(cardNr));
        if(karta.getExpirationDate().isAfter(LocalDate.now())) throw new InvalidExtendingValidityCard();
        karta.setExpirationDate(karta.getExpirationDate().plusYears(3));
    }
    public void paymentByCard(String toAccountNr,
                              String fromAccountNr,
                              String cvc,
                              String userFirstname,
                              String userLastname,
                              BigDecimal amount) {
        var kartaNadawcy = cardRepo.findUserCard(fromAccountNr,cvc, userFirstname, userLastname)
                .orElseThrow(() -> new CardNotFoundException(fromAccountNr));
        var kontoKarty = kartaNadawcy.getBankAccount();

        transactionService.createTransaction(
                kontoKarty.getAccountNr(),
                toAccountNr,
                amount,
                "Platnosc karta",
                kontoKarty.getCurrencyType(),
                TransactionType.CREDIT_CARD_TRANSFER
                );
    }
}
