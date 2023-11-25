package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){

        try{
            var socket = new Socket("localhost", 5000);
            PrintWriter pr = new PrintWriter(socket.getOutputStream());
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader bf = new BufferedReader(in);
        }
        catch(IOException e){
            System.out.println("Failed connection to server");
        }

        while(true){

        }
    }
}
