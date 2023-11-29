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
    private static EncryptionManager encryptionManager;

    public static void main(String[] args) throws Exception {
        try{
            socket = new Socket("localhost", 6000);
            sender = new PrintWriter(socket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            encryptionManager = new EncryptionManager();
            encryptionManager.initFromString();
        }
        catch(IOException e){
            System.out.println("Failed connection to server");
        }
        catch (Exception e){
            System.out.println("Decryption Manager Failed to launch");
        }

        String msg = "TRANSACTION,SUC_FROM_ACC,78190456231890724568903214";
        sender.println(encryptionManager.encrypt(msg));
        while(true){
            String encryptedMsg = reader.readLine();
            System.out.println(encryptedMsg);
        }

        //SwingUtilities.invokeLater(() -> new LoginSystem());
    }
}
