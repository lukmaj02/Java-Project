package Bank.util;

import Bank.Controller.Mapper;
import Bank.Enums.*;
import Bank.Repository.DepositRepository;
import Bank.Service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5);
    }
}
