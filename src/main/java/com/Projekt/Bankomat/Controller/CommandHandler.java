package com.Projekt.Bankomat.Controller;

import com.Projekt.Bankomat.Exceptions.*;
import com.Projekt.Bankomat.Service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
public class CommandHandler implements Callable<String> {
    private final Socket clientSocket;

    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBankAccountService bankAccountService;
    @Autowired
    private ICardService cardService;
    @Autowired
    private IAdminService adminService;

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
            case DELETE:
                userService.deleteUser(data);
                break;
            case REGISTER:
                userService.registerUser(Mapper.toRegister(data));
                break;
            case EDIT:
                //todo userService.editUserInformations();
                break;
            case GET_USER:
                adminService.getUser(data); //todo
                break;
            case GET_ALL:
                adminService.getAllUsers();//todo
                break;
            default:
                throw new InvalidCommandException();
        }
        return systemResponse;
    }

    //todo
    private String transactionController(TransactionCommand command, String data){
        String systemResponse = "OK,";
        switch (command){
            case CREATE:
                //todo
                break;
            case SUC_FROM_ACC:
                //todo
                break;
            case NOT_SUC_FROM_ACC:
                //todo
                break;
            case SUC_TO_ACC:
                //todo
                break;
            case ALL_FROM_ACC:
                //todo
                break;
            case ALL_TO_USER:
                //todo
                break;
            case ALL_FROM_USER:
                //todo
                break;
            case ALL_USER:
                //todo
                break;
            default:
                throw new InvalidCommandException();
        }
        return systemResponse;
    }

    //todo
    private String bankAccountController(BankAccountCommand command, String data){
        String systemResponse = "OK,";
        switch (command){
            case DELETE :
                //todo
                break;
            case CREATE:
                //todo
                break;
            default:
                throw new InvalidCommandException();
        }
        return systemResponse;
    }

    //todo
    private String cardController(CardCommand command, String data){
        String systemResponse = "OK,";
        switch (command){
            case DELETE :
                //todo
                break;
            case DISCARD:
                //todo
                break;
            case CREATE:
                //todo
                break;
            case PAY:
                //todo
                break;
            case EXTEND:
                //todo
                break;
        }
        return systemResponse;
    }
}
