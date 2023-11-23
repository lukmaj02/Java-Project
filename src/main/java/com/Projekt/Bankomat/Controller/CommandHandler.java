package com.Projekt.Bankomat.Controller;

import com.Projekt.Bankomat.Exceptions.BadCredentialsException;
import com.Projekt.Bankomat.Exceptions.UserExistsException;
import com.Projekt.Bankomat.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
public class CommandHandler implements Callable<String> {
    private final Socket clientSocket;
    BufferedReader reader;
    PrintWriter sender;


    @Autowired
    IUserService _service;
    public CommandHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public String call() {
        try{
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sender = new PrintWriter(clientSocket.getOutputStream(), true);

            while(true){
                String message = reader.readLine();
                String[] part = message.split(",", 3);
                String controller = part[0];
                String command = part[1];
                String data = part[2];
                var commandType = Command.valueOf(command.toUpperCase());


                if(controller.equalsIgnoreCase("TRANSACTION")){

                }
                else if(controller.equalsIgnoreCase("USER")){

                }
                else if(controller.equalsIgnoreCase("BANK_ACCOUNT")){

                }
                else if(controller.equalsIgnoreCase("CARD")){

                }
                else {

                }
            }
        }
        catch(Exception e) {
            System.out.println("a");
        }
        return "Task completed";
    }

    private String userController(Command command, String data){
        String systemResponse = "OK,";
        switch(command){
            case LOGIN:
                try{
                    var loginInf = Mapper.toLogin(data);
                    systemResponse += _service.login(loginInf[0], loginInf[1]).toString();
                }
                catch(BadCredentialsException e){
                    return e.getMessage();
                }
                break;

            case REGISTER:
                try{
                    var registerInf = Mapper.toRegister(data);
                    _service.registerUser(registerInf);
                }
                catch(UserExistsException e){
                    return e.getMessage();
                }
                break;
            default:
                //todo
        }
        return systemResponse;
    }
}
