package com.Projekt.Bankomat;


import com.Projekt.Bankomat.DtoModels.RegistrarionRequest;
import com.Projekt.Bankomat.Enums.TypKarty;
import com.Projekt.Bankomat.Enums.TypKonta;
import com.Projekt.Bankomat.Enums.TypWaluty;
import com.Projekt.Bankomat.Exceptions.EmailUzytkownikaNotFound;
import com.Projekt.Bankomat.Exceptions.NrKontaNotFoundException;
import com.Projekt.Bankomat.Models.KartaPlatnicza;
import com.Projekt.Bankomat.Models.Transakcja;
import com.Projekt.Bankomat.Repository.*;
import com.Projekt.Bankomat.Service.KartaPlatniczaSerivce;
import com.Projekt.Bankomat.Service.KontoBankoweService;
import com.Projekt.Bankomat.Service.TransakcjaService;
import com.Projekt.Bankomat.Service.UzytkownikService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@SpringBootApplication
public class BankomatApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankomatApplication.class, args);
	}
	@Bean
	public ExecutorService executorService(){
		return Executors.newSingleThreadExecutor();
	}
	@Bean
	public CommandLineRunner runOnStartup(UzytkownikService uzytkownikService,
										  UzytkownikRepo uzytkownikRepo,
										  KontoBankoweService kontoBankoweService,
										  TransakcjaService transakcjaService,
										  KartaPlatniczaSerivce kartaPlatniczaSerivce,
										  KontoBankoweRepo kontoBankoweRepo) {
		return args -> {
//			var realUzytkownik = uzytkownikRepo.findByEmail("random@example.com").orElseThrow(EmailUzytkownikaNotFound::new);

//			Future<List<Transakcja>> future1 = transakcjaService.pomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789");
//			Future<List<Transakcja>> future2 = transakcjaService.pomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789");
//			Future<List<Transakcja>> future6 = transakcjaService.niePomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789");
//			Future<List<Transakcja>> future3 = transakcjaService.pomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789");
//			Future<List<Transakcja>> future4 = transakcjaService.wszystkieTransakcjeZKonta("45678901234567890123456789");
//			Future<List<Transakcja>> future5 = transakcjaService.niePomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789");
//			System.out.println(uzytkownikService.wyswietlDaneUzytkownika("krzysztof.gonciarz@gmail.com"));
//			System.out.println(uzytkownikService.wyswietlKontaBankowe("krzysztof.gonciarz@gmail.com"));
//			System.out.println(transakcjaService.pomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789"));
//			System.out.println(transakcjaService.niePomyslnieWyslaneTransakcjeZKonta("45678901234567890123456789"));
//			System.out.println(transakcjaService.wszystkieTransakcjeZKonta("45678901234567890123456789"));
//			System.out.println(transakcjaService.pomyslnieWyslaneTransakcjeDoKonta("78190456231890724568903214"));
//			long currentTimeMillis = System.currentTimeMillis();
//			while (!(future1.isDone() && future2.isDone() && future3.isDone() && future4.isDone() && future5.isDone() && future6.isDone())) {
//				System.out.println(
//						String.format(
//								"future1: %s, future2: %s, future3: %s, future4: %s, future5: %s, future6: %s",
//								future1.isDone() ? "done" : "not finished",
//								future2.isDone() ? "done" : "not finished",
//								future3.isDone() ? "done" : "not finished",
//								future4.isDone() ? "done" : "not finished",
//								future5.isDone() ? "done" : "not finished",
//								future6.isDone() ? "done" : "not finished"
//						)
//				);
//				Thread.sleep(300);
//			}
//			System.out.println("TIME CONSUMED: " + (System.currentTimeMillis() - currentTimeMillis));
//				List<Transakcja> tmp1 = null;
//				List<Transakcja> tmp2 = null;
//				List<Transakcja> tmp3 = null;
//				List<Transakcja> tmp4 = null;
//				List<Transakcja> tmp5 = null;
//				List<Transakcja> tmp6 = null;
//				tmp1 = future1.get();
//				tmp2 = future2.get();
//				tmp3 = future3.get();
//				tmp4 = future4.get();
//				tmp5 = future5.get();
//				tmp6 = future6.get();
//				System.out.println(tmp1);
//				System.out.println(tmp2);
//				System.out.println(tmp3);
//				System.out.println(tmp4);
//				System.out.println(tmp5);
//				System.out.println(tmp6);
//			var uzytkownik = RegistrarionRequest.builder()
//					.imie("RandomImie")
//					.nazwisko("RandomNazwisko")
//					.email("random@example.com")
//					.haslo("RandomHaslo")
//					.nrTelefonu("123456644")
//					.dataUrodzenia(LocalDate.of(1990, 1, 1))
//					.adres("RandomStreet")
//					.build();
//			uzytkownikService.zarejestrujUzytkownika(uzytkownik);
			var realuzytkownik = uzytkownikService.wyswietlDaneUzytkownika("random@example.com");
			System.out.println(realuzytkownik);
//			kontoBankoweService.utworzKonto(
//					realuzytkownik.orElseThrow(EmailUzytkownikaNotFound::new),
//					TypKonta.OSOBISTE,
//					TypWaluty.EURO);
//			var konto = kontoBankoweRepo.findByNrKonta("17004046596712662895852028");
//			kartaPlatniczaSerivce.utworzKarte(
//					konto.orElseThrow(NrKontaNotFoundException::new),
//					TypKarty.DEBETOWA);
		};
	}
}
