package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.util.ArrayList;


public class FrontPage extends SceneController {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField userPasswordPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button logInButton;

    public ArrayList<String> firstFormulaPageData = new ArrayList<>();



    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FrontPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("bank-icon.png"));
        stage.setTitle("Virutal Banking System.");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == logInButton) {
            System.out.println("User email: " + emailTextField.getText() + " user password: " + userPasswordPasswordField.getText() + ".");
        } else if (actionEvent.getSource() == signUpButton) {
            openFormula(actionEvent, firstFormulaPageData);
        }
    }
}
