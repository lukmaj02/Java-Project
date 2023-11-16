package com.Projekt.Bankomat;

import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.Enums.TypKarty;
import com.Projekt.Bankomat.Enums.TypKonta;
import com.Projekt.Bankomat.Enums.TypTranskacji;
import com.Projekt.Bankomat.Enums.TypWaluty;
import com.Projekt.Bankomat.Exceptions.EmailUzytkownikaNotFound;
import com.Projekt.Bankomat.Models.*;
import com.Projekt.Bankomat.Repository.*;
import com.Projekt.Bankomat.Service.KartaPlatniczaSerivce;
import com.Projekt.Bankomat.Service.KontoBankoweService;
import com.Projekt.Bankomat.Service.TransakcjaService;
import com.Projekt.Bankomat.Service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BankomatApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankomatApplication.class, args);
	}

	@Bean
	public CommandLineRunner runOnStartup(UzytkownikService uzytkownikService,
										  UzytkownikRepo uzytkownikRepo,
										  KontoBankoweService kontoBankoweService,
										  TransakcjaService transakcjaService) {
		return args -> {
			var uzytkownik = RegistrarionRequest.builder()
					.imie("RandomImie")
					.nazwisko("RandomNazwisko")
					.email("random@example.com")
					.haslo("RandomHaslo")
					.nrTelefonu("123456644")
					.dataUrodzenia(LocalDate.of(1990, 1, 1))
					.adres("RandomStreet")
					.build();
			uzytkownikService.zarejestrujUzytkownika(uzytkownik);
			var realUzytkownik = uzytkownikRepo.findByEmail("random@example.com").orElseThrow(EmailUzytkownikaNotFound::new);
			System.out.println(uzytkownikService.wyswietlDaneUzytkownika("krzysztof.gonciarz@gmail.com"));
			System.out.println(uzytkownikService.wyswietlKontaBankowe("krzysztof.gonciarz@gmail.com"));
			System.out.println(transakcjaService.pomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789"));
			System.out.println(transakcjaService.niePomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789"));
			System.out.println(transakcjaService.wszystkieTransakcjeZKonta("45678901234567890123456789"));
			System.out.println(transakcjaService.pomyslnieWyslaneTransakcjeDoKonta("78190456231890724568903214"));
		};
	}
}
