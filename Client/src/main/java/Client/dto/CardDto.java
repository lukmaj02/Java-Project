package Client.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {
    private String cardId;
    private String cardNumber;
    private String expirationDate;
    private String cvc;
    private String cardType;
    private String isDiscard;
    private String pin;

    public static Set<CardDto> mapper(String data){
        var cards = new HashSet<CardDto>();
        if(data.equals("")) return cards;
        var splitedData = data.split(",,");
        for(String transaction : splitedData){
            var atribute = transaction.split(",");
            cards.add(CardDto.builder()
                    .cardId(atribute[0])
                    .cardNumber(atribute[1])
                    .expirationDate(atribute[2])
                    .cvc(atribute[3])
                    .cardType(atribute[4])
                    .isDiscard(atribute[5])
                    .pin(atribute[6])
                    .build());
        }
        return cards;
    }

}
