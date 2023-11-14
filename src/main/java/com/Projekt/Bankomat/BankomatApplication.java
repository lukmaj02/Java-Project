package com.Projekt.Bankomat;

import com.Projekt.Bankomat.Models.Transakcja;
import com.Projekt.Bankomat.Repository.TransakcjaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

@SpringBootApplication
public class BankomatApplication implements CommandLineRunner{
	public static void main(String[] args) {
		new SpringApplicationBuilder(BankomatApplication.class).headless(false).run(args);
	}
	@Autowired
	TransakcjaRepo transakcjaRepo;
	@Override
	public void run(String... args) {
		//DLA TESTU CZY DZIALA
		var transakcja = new Transakcja(UUID.randomUUID(), 1);
		transakcjaRepo.save(transakcja);

		JFrame frame = new JFrame("Spring Boot Swing App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		JPanel panel = new JPanel(new BorderLayout());
		JTextField text = new JTextField(transakcjaRepo.findByLiczba(1).toString());
		panel.add(text, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}


}
