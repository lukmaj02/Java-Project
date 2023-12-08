package Client.GUI.User;

import Client.Client;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserPage extends Client {

    @FXML
    public Button deleteUser;
    @FXML
    public Button viewDeposits;
    @FXML
    public Button viewTransactions;
    @FXML
    public Button viewBankAccounts;
    @FXML
    public Button accountCreation;
    @FXML
    public Button userInformations;

    private UserDto user;

    public void initialize(String data) {
    }

    public void executeAnAction(ActionEvent actionEvent) {
    }
}
