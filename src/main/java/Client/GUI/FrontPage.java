package Client.GUI;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import java.util.ArrayList;


public class FrontPage extends Client {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField userPasswordPasswordField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button logInButton;

    public ArrayList<String> firstFormulaPageData = new ArrayList<>();


    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == logInButton) {
            var msg = sendToServer(
                    "USER,LOGIN,"+emailTextField.getText()+","+ userPasswordPasswordField.getText())
                    .split(",",2);
            if(msg[0].equals("OK")){
                openUserPage(actionEvent,msg[1]);
            } else {
                showWarning(msg[1]);
            }
        } else if (actionEvent.getSource() == signUpButton) {
            openFormula(actionEvent, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }
}
