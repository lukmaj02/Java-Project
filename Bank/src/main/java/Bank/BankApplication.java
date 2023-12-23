package Bank;


import Bank.Controller.ClientHandler;
import Bank.util.DecryptionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


@SpringBootApplication
public class BankApplication {
	private final static int SERVER_PORT = 6000;
	private final static ExecutorService executorService = Executors.newCachedThreadPool();
	private static ServerSocket serverSocket;
	public static void main(String[] args) throws IOException {
		//starting server
		try{
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("Server started on port " + SERVER_PORT);
		}
		catch(IOException e){
			System.out.println("Server failed to start on port " + SERVER_PORT);
			System.exit(-1);
		}

		//launching singletons
		ConfigurableApplicationContext appContext = SpringApplication.run(BankApplication.class, args);
		ClientHandler clientHandler = appContext.getBean(ClientHandler.class);
		DecryptionManager decryptionManager = appContext.getBean(DecryptionManager.class);

		//launching decryption manager
		decryptionManager.initFromString();


		//accepting clients
		while(true){
			Socket clientSocket = serverSocket.accept();
			clientHandler.initSocket(clientSocket);
			System.out.println("Client connected " + clientSocket.getInetAddress().getHostAddress());
			FutureTask<String> task = new FutureTask<String>(clientHandler);
			executorService.submit(task);
		}
	}
}

