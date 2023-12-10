package Client.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDto {
    private String depositId;
    private String depositType;
    private String creationDate;
    private String finishDate;
    private String amount;
    private String depositStatus;
    private String currencyType;
    private String accountNr;

    public static Set<DepositDto> mapper(String data){
        var deposits = new HashSet<DepositDto>();
        if(data.equals("")) return deposits;
        var splitedData = data.split(",,");
        for(String deposit : splitedData){
            var atribute = deposit.split(",");
            deposits.add(DepositDto.builder()
                    .depositId(atribute[0])
                    .depositType(atribute[1])
                    .creationDate(atribute[2])
                    .finishDate(atribute[3])
                    .amount(atribute[4])
                    .currencyType(atribute[5])
                    .depositStatus(atribute[6])
                    .accountNr(atribute[7])
                    .build());
        }
        return deposits;
    }
}
