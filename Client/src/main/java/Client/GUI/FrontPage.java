package Client.GUI;

import Client.SceneController;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == logInButton) {
            var msg = sendToServerWithResponse("USER,LOGIN,"+emailTextField.getText()+","+ userPasswordPasswordField.getText());
            if(isResponseValid(msg)) openUserPage(actionEvent, UserDto.mapper(msg));

        } else if (actionEvent.getSource() == signUpButton) {
            openFormula(actionEvent, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }
}
