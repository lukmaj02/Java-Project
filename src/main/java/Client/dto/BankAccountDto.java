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
        if(data.equals("")) return new HashSet<>();
        var splitedData = data.split(",,");
        var accounts = new HashSet<BankAccountDto>();
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

    public List<String> toList(BankAccountDto bankAccount) {
        List<String> resultList = new ArrayList<>();
        resultList.add(bankAccount.getAccountNr());
        resultList.add(bankAccount.getBalance());
        resultList.add(bankAccount.getCurrencyType());
        resultList.add(bankAccount.getAccountType());
        return resultList;
    }

}
