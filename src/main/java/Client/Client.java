package Client;

import Client.Gui.LoginSystem;

import javax.swing.*;
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
        SwingUtilities.invokeLater(() -> new LoginSystem());
        try{
            socket = new Socket("localhost", 6000);
            sender = new PrintWriter(socket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e){
            System.out.println("Failed connection to server");
        }
    }
}
