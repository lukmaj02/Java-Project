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
}
