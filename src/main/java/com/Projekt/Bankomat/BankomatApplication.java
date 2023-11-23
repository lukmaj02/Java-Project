package com.Projekt.Bankomat;


import com.Projekt.Bankomat.Controller.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


@SpringBootApplication
public class BankomatApplication {
	private final static int SERVER_PORT = 5000;
	private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
	private static ServerSocket serverSocket;

	@Autowired
	static CommandHandler controller;
	public static void main(String[] args) throws IOException {
		try{
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started on port " + SERVER_PORT);
		}
		catch(IOException e){
			System.out.println("Server failed to start on port " + SERVER_PORT);
			System.exit(-1);
		}

		SpringApplication.run(BankomatApplication.class, args);
		while(true){
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client connected " + clientSocket.getInetAddress().getHostAddress());
			FutureTask<String> task = new FutureTask<>(new CommandHandler(clientSocket));
			executorService.submit(task);
		}
	}
}
