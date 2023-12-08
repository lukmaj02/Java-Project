package Client.GUI.User;

import Client.Client;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AccountCreationPage extends Client {
    @FXML
    public Label frontLabel;
    @FXML
    public Label accountTypeLabel;
    @FXML
    public Label currencyTypeLabel;
    @FXML
    public ChoiceBox<String> accountTypeBox;
    @FXML
    public ChoiceBox<String> currencyTypeBox;
    @FXML
    public Button backButton;
    @FXML
    public Button createAccount;
    private UserDto user;

    public void initialize(UserDto user){
        this.user = user;
        accountTypeBox.getItems().addAll("Savings", "Personal", "Buisness");
        currencyTypeBox.getItems().addAll(
                "PLN",
                "EURO",
                "US DOLLAR",
                "RUBLE",
                "NORWEGIAN_KRONE"
        );
    }
    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == backButton){
            openUserPage(event, user);
        }
        else if(event.getSource() == createAccount){
            var msg = sendToServerWithResponse("BANK_ACCOUNT,CREATE,"+
                    user.getEmail() +
                    accountTypeBox.getValue().toUpperCase() +
                    currencyTypeBox.getValue().toUpperCase()
                    );
            if(isResponseValid(msg)) {
                //todo
            }
        }
    }
}
