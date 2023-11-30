package com.Projekt.Bankomat;


import com.Projekt.Bankomat.Controller.ClientHandler;
import com.Projekt.Bankomat.Enums.DepositType;
import com.Projekt.Bankomat.Models.Deposit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;


@SpringBootApplication
public class BankomatApplication {
	private final static int SERVER_PORT = 6000;
	private static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		try{
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started on port " + SERVER_PORT);
		}
		catch(IOException e){
			System.out.println("Server failed to start on port " + SERVER_PORT);
			System.exit(-1);
		}

		ConfigurableApplicationContext appContext = SpringApplication.run(BankomatApplication.class, args);
		ExecutorService executorService = appContext.getBean(ExecutorService.class);
		ClientHandler clientHandler = appContext.getBean(ClientHandler.class);
		DecryptionManager decryptionManager = appContext.getBean(DecryptionManager.class);

		try{
			decryptionManager.initFromString();
		}catch (Exception e){
			System.out.println("Failed to wire DecryptionManager");
			System.exit(-1);
		}

		while(true){
			Socket clientSocket = serverSocket.accept();
			clientHandler.initSocket(clientSocket);
			if(clientSocket.isConnected()){
				System.out.println("Client connected " + clientSocket.getInetAddress().getHostAddress());
				FutureTask<String> task = new FutureTask<>(clientHandler);
				executorService.submit(task);
			}
		}
	}
}
