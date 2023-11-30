package com.Projekt.Bankomat;

import com.Projekt.Bankomat.Enums.DepositType;
import com.Projekt.Bankomat.Service.DepositService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DepositService depositService){
        return args -> {
            //depositService.createDeposit("84327494823748210194574587", DepositType.ANNUAL, BigDecimal.valueOf(1000));
            //depositService.suspendDeposit("b59eeda2-19e9-4c32-96dc-3a06864f95b3");
            depositService.finishDeposit("76d2a80c-1045-4e80-9102-2f15b0f07489");
        };
    }
}
