package com.Projekt.Bankomat;

import com.Projekt.Bankomat.Enums.*;
import com.Projekt.Bankomat.Repository.DepositRepository;
import com.Projekt.Bankomat.Service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.Projekt.Bankomat.Enums.DepositType.ANNUAL;

@Configuration
public class AppConfig {
    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DepositService depositService,
                                               DepositRepository depositRepository,
                                               UserService userService,
                                               TransactionService transactionService,
                                               BankAccountService bankAccountService,
                                               CreditService creditService){
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
//                transactionService.createTransaction("45678901234567890123456789",
//                        "59013467890234126789012345",
//                        BigDecimal.valueOf(7000000),
//                        "za wczoraj",
//                        CurrencyType.ZLOTY,
//                        TransactionType.PRZELEW_EKSPRESOWY);
            }
            catch(RuntimeException e){
                e.printStackTrace();
            }
        };
    }
}
