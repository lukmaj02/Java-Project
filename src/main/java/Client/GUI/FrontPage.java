package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;


public class FrontPage extends Application {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField userPasswordPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button logInButton;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FrontPage.fxml"));

        root = loader.load();
        scene = new Scene(root);

        stage.getIcons().add(new Image("bank-icon.png"));
        stage.setTitle("Virutal Banking System.");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    private void openFormula(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FirstFormulaPage.fxml"));
        root = loader.load();

        FirstFormulaPage formulaPage = loader.getController();
        formulaPage.changeFormulaNumber(1); // change randomly|depending on users count

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void returnToFrontPage(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontPage.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == logInButton) {
            // TODO add change scene
            System.out.println("User email: " + emailTextField.getText() + " user password: " + userPasswordPasswordField.getText() + ".");
        } else if (actionEvent.getSource() == signUpButton) {
            openFormula(actionEvent);
        }
    }
}
