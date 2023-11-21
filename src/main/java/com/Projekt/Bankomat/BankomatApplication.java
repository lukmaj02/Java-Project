package com.Projekt.Bankomat;


import com.Projekt.Bankomat.Repository.*;
import com.Projekt.Bankomat.Service.BankAccountService;
import com.Projekt.Bankomat.Service.CardService;
import com.Projekt.Bankomat.Service.TransactionService;
import com.Projekt.Bankomat.Service.UzytkownikService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
										  UserRepo userRepo,
										  BankAccountService bankAccountService,
										  TransactionService transactionService,
										  CardService cardService,
										  BankAccountRepo bankAccountRepo,
										  CardRepo cardRepo) {
		return args -> {
//			var realUzytkownik = userRepo.findByEmail("random@example.com").orElseThrow(UserEmailNotFoundException::new);

//			Future<List<Transaction>> future1 = transactionService.getAllNotSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789");
//			Future<List<Transaction>> future2 = transactionService.getAllNotSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789");
//			Future<List<Transaction>> future6 = transactionService.getAllSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789");
//			Future<List<Transaction>> future3 = transactionService.getAllNotSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789");
//			Future<List<Transaction>> future4 = transactionService.wszystkieTransakcjeZKonta("45678901234567890123456789");
//			Future<List<Transaction>> future5 = transactionService.getAllSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789");
//			System.out.println(uzytkownikService.getUser("krzysztof.gonciarz@gmail.com"));
//			System.out.println(uzytkownikService.wyswietlKontaBankowe("krzysztof.gonciarz@gmail.com"));
//			System.out.println(transactionService.getAllNotSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789"));
//			System.out.println(transactionService.getAllSuccessfullySentTransactionsFromBankAccount("45678901234567890123456789"));
//			System.out.println(transactionService.wszystkieTransakcjeZKonta("45678901234567890123456789"));
//			System.out.println(transactionService.getAllSuccessfullySentTransactionsToBankAccount("78190456231890724568903214"));
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
//				List<Transaction> tmp1 = null;
//				List<Transaction> tmp2 = null;
//				List<Transaction> tmp3 = null;
//				List<Transaction> tmp4 = null;
//				List<Transaction> tmp5 = null;
//				List<Transaction> tmp6 = null;
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
//			var user = RegistrarionRequest.builder()
//					.firstName("RandomImie")
//					.lastName("RandomNazwisko")
//					.email("random@example.com")
//					.password("RandomHaslo")
//					.phoneNumber("123456644")
//					.dateOfBirth(LocalDate.of(1990, 1, 1))
//					.address("RandomStreet")
//					.build();
//			uzytkownikService.registerUser(user);
//			var realuzytkownik = uzytkownikService.getUser("random@example.com");
//			System.out.println(realuzytkownik);
//			bankAccountService.createAccount(
//					realuzytkownik.orElseThrow(UserEmailNotFoundException::new),
//					AccountType.OSOBISTE,
//					CurrencyType.EURO);
//			var konto = bankAccountRepo.findByNrKonta("17004046596712662895852028");
//			cardService.createCard(
//					konto.orElseThrow(AccountNumberNotFoundException::new),
//					CardType.DEBETOWA);
//			var karta = cardRepo.findUserCard(
//					"8828063284979948",
//					"201",
//					"RandomImie",
//					"RandomNazwisko");
//			System.out.println(karta);
//			bankAccountService.deleteAccount("17004046596712662895852028");
//			uzytkownikService.deleteUser("random@example.com");
		};
	}
}
