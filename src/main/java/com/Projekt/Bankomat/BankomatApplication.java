package com.Projekt.Bankomat;

import com.Projekt.Bankomat.Enums.TypKarty;
import com.Projekt.Bankomat.Enums.TypKonta;
import com.Projekt.Bankomat.Enums.TypTranskacji;
import com.Projekt.Bankomat.Enums.TypWaluty;
import com.Projekt.Bankomat.Models.*;
import com.Projekt.Bankomat.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class BankomatApplication{
	public static void main(String[] args) {
		SpringApplication.run(BankomatApplication.class, args);
	}
//	testing if every object is correctly saved to db
//	@Bean
//	CommandLineRunner commandLineRunner(UzytkownikRepo uzytkownikRepo,
//										KartaPlatniczaRepo kartaPlatniczaRepo,
//										KontoBankoweRepo kontoBankoweRepo,
//										PrzelewRepo przelewRepo,
//										TransakcjaRepo transakcjaRepo){
//		return args -> {
//			var uzytkownik = new Uzytkownik(
//					UUID.randomUUID().toString(),
//					"Test",
//					"Test",
//					"test@gmail.com",
//					"password",
//					LocalDate.of(2000,12,12),
//					"+48 912 837 475");
//			uzytkownikRepo.save(uzytkownik);
//			var konto = new KontoBankowe(
//					UUID.randomUUID().toString(),
//					"482842350325",
//					BigDecimal.valueOf(20000.00),
//					TypKonta.OSOBISTE,
//					uzytkownik
//			);
//			kontoBankoweRepo.save(konto);
//			var karta = new KartaPlatnicza(
//					UUID.randomUUID().toString(),
//					1938217424,
//					LocalDate.of(2025,12,12),
//					343,
//					TypKarty.DEBETOWA,
//					konto
//			);
//			var karta1 = new KartaPlatnicza(
//					UUID.randomUUID().toString(),
//					429557923,
//					LocalDate.of(2024,11,11),
//					123,
//					TypKarty.KREDYTOWA,
//					konto
//			);
//			kartaPlatniczaRepo.save(karta1);
//			kartaPlatniczaRepo.save(karta);
//
//			var transakcja = new Transakcja(
//					UUID.randomUUID().toString(),
//					TypTranskacji.PRZELEW_TRADYCYJNY,
//					true,
//					LocalDate.of(2023,11,11)
//			);
//			transakcjaRepo.save(transakcja);
//
//			var przelew = new Przelew(
//					UUID.randomUUID().toString(),
//					BigDecimal.valueOf(150.00),
//					LocalDate.of(2023,9,9),
//					TypWaluty.EURO,
//					transakcja
//			);
//
//			var przelew1 = new Przelew(
//					UUID.randomUUID().toString(),
//					BigDecimal.valueOf(170.00),
//					LocalDate.of(2023,6,12),
//					TypWaluty.EURO,
//					transakcja
//			);
//			przelewRepo.save(przelew);
//			przelewRepo.save(przelew1);
//		};
//	}
}
