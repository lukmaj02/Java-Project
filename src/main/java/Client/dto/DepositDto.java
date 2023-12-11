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
    private String type;
    private String creationDate;
    private String finishDate;
    private String amount;
    private String status;
    private String currency;
    private String accountNr;

    public static Set<DepositDto> mapper(String data){
        var deposits = new HashSet<DepositDto>();
        if(data.equals("")) return deposits;
        var splitedData = data.split(",,");
        for(String deposit : splitedData){
            var atribute = deposit.split(",");
            deposits.add(DepositDto.builder()
                    .depositId(atribute[0])
                    .type(atribute[1])
                    .creationDate(atribute[2])
                    .finishDate(atribute[3])
                    .amount(atribute[4])
                    .currency(atribute[5])
                    .status(atribute[6])
                    .accountNr(atribute[7])
                    .build());
        }
        return deposits;
    }
}
