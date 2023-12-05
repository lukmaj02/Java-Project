package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ThirdFormulaPage extends SceneController {
    @FXML
    private Label formulaLabel;
    @FXML
    private Button previousPageButton;
    @FXML
    private Button nextPageButton;
    @FXML
    private PasswordField userRewritePasswordField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField areaCodeTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField cardPinTextField;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ThirdFormulaPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public void initializeFormula(Integer number) {
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == previousPageButton) {
            openSecondFormulaPage(actionEvent, new ArrayList<>(),new ArrayList<>());
        }
    }

}
