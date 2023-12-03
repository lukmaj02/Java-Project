package Client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import static Client.EncryptionManager.*;

public class Client {
    private static Socket socket;
    private static PrintWriter sender;
    private static BufferedReader reader;

    public static void main(String[] args) throws Exception {
        try{
            socket = new Socket("localhost", 6000);
            sender = new PrintWriter(socket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            EncryptionManager.initFromString();
        }
        catch(IOException e){
            System.out.println("Failed connection to server");
        }
        catch (Exception e){
            System.out.println("Decryption Manager Failed to launch");
        }

        String msg = "BANK_ACCOUNT,DELETE,45678901234567890123456789";
        sender.println(encrypt(msg));
        while(true){
            String encryptedMsg = reader.readLine();
            System.out.println(encryptedMsg);
        }
    }
}
