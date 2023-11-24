package com.Projekt.Bankomat.Controller;

import com.Projekt.Bankomat.Exceptions.*;
import com.Projekt.Bankomat.Service.ITransactionService;
import com.Projekt.Bankomat.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
public class CommandHandler implements Callable<String> {
    private final Socket clientSocket;

    @Autowired
    IUserService userService;
    @Autowired
    ITransactionService transactionService;

    @Autowired
    public CommandHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public String call() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter sender = new PrintWriter(clientSocket.getOutputStream(), true);

            while(true){
                String message = reader.readLine();
                String[] part = message.split(",", 3);
                String controller = part[0];
                String command = part[1];
                String data = part[2];

                try{
                    if(controller.equalsIgnoreCase("TRANSACTION")){
                        var commandType = TransactionCommand.valueOf(command.toUpperCase());
                        sender.println(transactionController(commandType, data));
                    }
                    else if(controller.equalsIgnoreCase("USER")){
                        var commandType = UserCommand.valueOf(command.toUpperCase());
                        sender.println(userController(commandType, data));
                    }
                    else if(controller.equalsIgnoreCase("BANK_ACCOUNT")){
                        var commandType = BankAccountCommand.valueOf(command.toUpperCase());
                        sender.println(bankAccountController(commandType,data));
                    }
                    else if(controller.equalsIgnoreCase("CARD")){
                        var commandType = CardCommand.valueOf(command.toUpperCase());
                        sender.println(cardController(commandType,data));
                    }
                    else {
                        sender.println("ERROR,Zly kontroler");
                    }
                }
                catch(RuntimeException e){
                    sender.println("ERROR," + e.getMessage());
                }
            }
        }
        catch(Exception e) {
            System.out.println("a");
        }
        return "Task completed";
    }

    private String userController(UserCommand command, String data) throws RuntimeException{
        String systemResponse = "OK,";
        switch(command){
            case LOGIN:
                var loginInf = Mapper.toLogin(data);
                systemResponse += userService.login(loginInf[0], loginInf[1]).toString();
                break;
            case REGISTER:
                userService.registerUser(Mapper.toRegister(data));
                break;
            case EDIT_USER:
                //todo _service.editUserInformations();
                break;
            default:
                throw new InvalidCommandException();
        }
        return systemResponse;
    }

    private String transactionController(TransactionCommand command, String data){
        String systemResponse = "OK";
        switch (command){
            case CREATE:

                break;
        }
        return null; //todo
    }

    private String bankAccountController(BankAccountCommand command, String data){
        return null; //todo
    }

    private String cardController(CardCommand command, String data){
        return null; //todo
    }
}
