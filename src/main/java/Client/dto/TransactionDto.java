package Client.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private String transactionId;
    private String transactionType;
    private String isValid;
    private String transactionDate;
    private String amount;
    private String currencyType;
    private String title;
    private String fromAccountNr;
    private String toAccountNr;

    public static Set<TransactionDto> mapper(String data){
        var splitedData = data.split(",,");
        var transactions = new HashSet<TransactionDto>();
        for(String transaction : splitedData){
            var atribute = transaction.split(",");
            transactions.add(TransactionDto.builder()
                    .transactionId(atribute[0])
                    .transactionType(atribute[1])
                    .isValid(atribute[2])
                    .transactionDate(atribute[3])
                    .amount(atribute[4])
                    .currencyType(atribute[5])
                    .title(atribute[6])
                    .fromAccountNr(atribute[7])
                    .toAccountNr(atribute[8])
                    .build());
        }
        return transactions;
    }
}
