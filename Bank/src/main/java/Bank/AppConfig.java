package Bank;

import Bank.Enums.*;
import Bank.Repository.DepositRepository;
import Bank.Service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.net.http.HttpClient;

@Configuration
public class AppConfig {
    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }

    @Bean
    public CommandLineRunner commandLineRunner(DepositService depositService,
                                               DepositRepository depositRepository,
                                               UserService userService,
                                               TransactionService transactionService,
                                               BankAccountService bankAccountService,
                                               CreditService creditService,
                                               CardService cardService,
                                               CurrencyTransferService transferService){
        return args -> {
            try{
                //depositService.createDeposit("84327494823748210194574587", DepositType.ANNUAL, BigDecimal.valueOf(1000));
                //depositService.suspendDeposit("b59eeda2-19e9-4c32-96dc-3a06864f95b3");
                //depositService.finishDeposit("76d2a80c-1045-4e80-9102-2f15b0f07489");
                //System.out.println(depositRepository.getActiveAccountDeposits("84327494823748210194574587"));
                //userService.deleteUser("krzysztof.gonciarz@gmail.com");
                //depositService.createDeposit("78190456231890724568903214", ANNUAL, BigDecimal.valueOf(30000));
//                transactionService.createTransaction("78190456231890724568903214",
//                        "59013467890234126789012345",
//                        BigDecimal.valueOf(30000),
//                        "za wczoraj",
//                        CurrencyType.ZLOTY,
//                        TransactionType.PRZELEW_EKSPRESOWY);
                //bankAccountService.deleteAccount("78190456231890724568903214");
                //depositService.suspendDeposit("dc29955b-2d42-4ab7-bee7-a2b40fe0581e");
                //userService.deleteUser("krzysztof.gonciarz@gmail.com");
                //creditService.requestCredit("78190456231890724568903214", BigDecimal.valueOf(1000), 20, CreditType.CONST);
                //creditService.activeCredit("c8038902-de92-4fcf-a9f0-ebc508fc1e5f");
//                transactionService.createTransaction("21299378086789260481191951",
//                        "94830475063686771280990909",
//                        BigDecimal.valueOf(20000),
//                        "za wczoraj",
//                        CurrencyType.PLN,
//                        TransactionType.TRADITIONAL_TRANSFER);
                //System.out.println(depositRepository.getAllUserDeposits("krzysztof.gonciarz@gmail.com"));
                //System.out.println(creditService.getCreditsWithStatus(CreditStatus.PROCESSED));
                System.out.println(transferService.convertCurrencyAmount(CurrencyType.PLN, CurrencyType.EURO, BigDecimal.valueOf(2000)));
            }
            catch(RuntimeException e){
                e.printStackTrace();
            }
        };
    }
}
