package com.Projekt.Bankomat.Controller;

import com.Projekt.Bankomat.Enums.CurrencyType;
import com.Projekt.Bankomat.Enums.TransactionType;
import com.Projekt.Bankomat.Exceptions.*;
import com.Projekt.Bankomat.Generators;
import com.Projekt.Bankomat.Models.User;
import com.Projekt.Bankomat.Service.*;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.concurrent.Callable;
@Configurable
public class CommandHandler implements Callable<String> {
    private final Socket clientSocket;

    private final IUserService userService;
    private final ITransactionService transactionService;
    private final IBankAccountService bankAccountService;
    private final ICardService cardService;
    private final IAdminService adminService;

    public CommandHandler(Socket clientSocket,
                          IUserService userService,
                          ITransactionService transactionService,
                          IBankAccountService bankAccountService,
                          ICardService cardService,
                          IAdminService adminService){
        this.clientSocket = clientSocket;
        this.userService = userService;
        this.transactionService = transactionService;
        this.bankAccountService = bankAccountService;
        this.cardService = cardService;
        this.adminService = adminService;
    }

    @Override
    public String call() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter sender = new PrintWriter(clientSocket.getOutputStream(), true);

            while(true){
                String message = reader.readLine();
                String[] part = message.split(",", 3);
                Command controller = Command.valueOf(part[0].toUpperCase());
                String command = part[1].toUpperCase();
                String data = part[2];


                try{
                    String response = "";
                    switch (controller) {
                        case USER -> {
                            System.out.println("wszedl");
                            response = userController(UserCommand.valueOf(command), data);
                        }
                        case TRANSACTION -> {
                            response = transactionController(TransactionCommand.valueOf(command), data);
                        }
                        case BANK_ACCOUNT -> {
                            response = bankAccountController(BankAccountCommand.valueOf(command), data);
                        }
                        case CARD -> {
                            response = cardController(CardCommand.valueOf(command), data);
                        }
                        case EXIT ->{
                            clientSocket.close();
                        }
                        default -> response = "ERROR,Zly kontroler";
                    }
                    sender.println(Generators.generateHash(response));
                }
                catch(RuntimeException e){
                    sender.println(Generators.generateHash("ERROR," + e.getMessage()));
                }
            }
        }
        catch(Exception e) {
            System.out.println("a");
        }
        return "Task completed";
    }

    private String userController(UserCommand command, String data) throws RuntimeException{
        StringBuilder systemResponse = new StringBuilder("OK,");
        switch (command) {
            case DELETE -> userService.deleteUser(data);
            case REGISTER -> userService.registerUser(Mapper.toUserDto(data));
            case EDIT -> userService.editUserInformations(Mapper.toUserDto(data));
            case GET_USER -> systemResponse.append(adminService.getUser(data));
            case LOGIN -> {
                var loginInf = Mapper.toLogin(data);
                systemResponse.append(userService.login(loginInf[0], loginInf[1]).toString());
            }
            case GET_ALL -> {
                var users = adminService.getAllUsers();
                for (User user : users) {
                    systemResponse.append(user.toString());
                }
            }
            default -> throw new InvalidCommandException();
        }
        return systemResponse.toString();
    }

    private String transactionController(TransactionCommand command, String data){
        StringBuilder systemResponse = new StringBuilder("OK,");
        switch (command) {
            case CREATE -> {
                var info = Mapper.toTransaction(data);
                transactionService.createTransaction(
                        info[0],
                        info[1],
                        new BigDecimal(info[2]),
                        info[3],
                        CurrencyType.valueOf(info[4]),
                        TransactionType.valueOf(info[5]));
            }
            case SUC_FROM_ACC ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllSuccessfullySentTransactionsFromBankAccount(data)));
            case NOT_SUC_FROM_ACC ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllNotSuccessfullySentTransactionsFromBankAccount(data)));
            case SUC_TO_ACC ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllSuccessfullySentTransactionsToBankAccount(data)));
            case ALL_FROM_ACC ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllTransactionFromAccount(data)));
            case ALL_TO_USER ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllTransactionsSentToUser(data)));
            case ALL_FROM_USER ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllTransactionsSentByUser(data)));
            case ALL_USER ->
                    systemResponse.append(Mapper.TransactionsToString(
                    transactionService.getAllUserTransactions(data)));
            default ->
                    throw new InvalidCommandException();
        }
        return systemResponse.toString();
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
