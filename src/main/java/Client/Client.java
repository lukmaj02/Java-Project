package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static PrintWriter sender;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {

        try{
            socket = new Socket("localhost", 6000);
            sender = new PrintWriter(socket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e){
            System.out.println("Failed connection to server");
        }
        sender.println("TRANSACTION,SUC_FROM_ACC,78190456231890724568903214");
        socket.close();
    }
}
