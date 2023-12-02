package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;


public class FrontPage extends Application {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField userPasswordPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button logInButton;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FrontPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void executeAnAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == logInButton) {
            // TODO add change scene
            System.out.println("User email: " + emailTextField.getText() + " user password: " + userPasswordPasswordField.getText() + ".");
        } else if (actionEvent.getSource() == signUpButton) {
            // TODO add change scene
            System.out.println("Click sign up button.");
        }
    }
}
