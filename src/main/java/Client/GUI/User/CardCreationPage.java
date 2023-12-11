package Client.GUI.User;

import Client.Client;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CardCreationPage extends Client {
    @FXML
    public ChoiceBox<String> type;
    @FXML
    public TextField pin;
    @FXML
    public Button backButton;
    @FXML
    public Button createCard;
    private String accountNr;
    private UserDto user;
    public void initialize(UserDto user, String accountNr){
        this.user = user;
        this.accountNr = accountNr;
        type.getItems().addAll("CREDIT", "DEBIT");
    }

    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == createCard && areFieldsValid()){
            var msg = sendToServerWithResponse("CARD,CREATE,"+
                    accountNr + "," +
                    type.getValue() + "," +
                    pin.getText());
            if(isResponseValid(msg)){
                showInfo("CREATED", "Card was created successfully");
                openUserPage(event, user);
            }
        }
        else if(event.getSource() == backButton){
            openUserPage(event, user);
        }
    }
    private boolean areFieldsValid(){
        if(type.getValue() == null || pin == null) {
            showWarning("Pin or card type is empty");
            return false;
        }
        else if(pin.getText().matches("\\d{4}")) return true;
        showWarning("Invalid pin number, check if it is 4digit number!");
        return false;
    }
}
