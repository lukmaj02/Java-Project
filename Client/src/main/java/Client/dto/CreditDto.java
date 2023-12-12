package Client.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDto {
    private String creditId;
    private String installmentCount;
    private String lendingRate;
    private String creditAmount;
    private String installmentAmount;
    private String currency;
    private String accountNumber;
    private String creditStatus;
    private String creditType;

    public static Set<CreditDto> mapper (String data){
        var credits = new HashSet<CreditDto>();
        if(data.equals("")) return credits;
        var splitedData = data.split(",,");
        for(String transaction : splitedData){
            var atribute = transaction.split(",");
            credits.add(CreditDto.builder()
                    .creditId(atribute[0])
                    .accountNumber(atribute[1])
                    .installmentCount(atribute[2])
                    .lendingRate(atribute[3])
                    .creditAmount(atribute[4])
                    .installmentAmount(atribute[5])
                    .currency(atribute[6])
                    .creditStatus(atribute[7])
                    .creditType(atribute[8])
                    .build());
        }
        return credits;
    }
}
