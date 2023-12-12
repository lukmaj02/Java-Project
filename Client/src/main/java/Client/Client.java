package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class Client extends Application {
    protected Stage _stage;
    protected Scene _scene;
    protected Parent _root;

    //connection to server
    protected static PrintWriter sender;
    protected static BufferedReader reader;
    protected static Socket socket;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            socket = new Socket("localhost", 6000);
            sender = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            EncryptionManager.initFromString();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FrontPage.fxml"));

            _root = loader.load();
            _scene = new Scene(_root);
            stage.getIcons().add(new Image("bank-icon.png"));
            stage.setTitle("Virtual Banking System.");

            stage.setScene(_scene);
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException e){
            showWarning("Failed connection to server");
        }
        catch (Exception e) {
            showWarning("Decryption Manager Failed to launch");
        }
    }
    protected String sendToServerWithResponse(String data) {
        try {
            sender.println(data);
            return reader.readLine();
        } catch (Exception e) {
            return "ERROR, SERVER FAILED TO READ! CHECK LATER AGAIN";
        }
    }
    protected void showProvideDataWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Data warning, you haven't fill all fields.");
        alert.setContentText("Provide more details!");
        alert.show();
    }
    protected void showWarning(String warningInfo){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("WARNING");
        alert.setContentText(warningInfo);
        alert.show();
    }
    protected void showInfo(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    protected boolean isResponseValid(String data){
        if(Objects.equals(data, "")) return true;
        var splitedData = data.split(",",2);
        if(splitedData[0].equals("ERROR")){
            showWarning(splitedData[1]);
            return false;
        }
        return true;
    }
    void setStage(ActionEvent event){
        _stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setScene(_scene);
        _stage.show();
        _stage.centerOnScreen();
    }
}

