package Client.dto;

import javafx.beans.property.StringProperty;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDto {
    private String accountId;
    private String accountNr;
    private String balance;
    private String currencyType;
    private String accountType;

    public static Set<BankAccountDto> mapper(String data){
        var accounts = new HashSet<BankAccountDto>();
        if(data.equals("")) return accounts;
        var splitedData = data.split(",,");
        for (String account : splitedData){
            var atribute = account.split(",");
            accounts.add(BankAccountDto.builder()
                    .accountId(atribute[0])
                    .accountNr(atribute[1])
                    .balance(atribute[2])
                    .currencyType(atribute[3])
                    .accountType(atribute[4])
                    .build());
        }
        return accounts;
    }
}
