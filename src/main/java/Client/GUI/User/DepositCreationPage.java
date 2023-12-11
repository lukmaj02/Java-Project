package Client.GUI.User;

import Client.Client;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DepositCreationPage extends Client {

    @FXML
    public ChoiceBox<String> type;
    @FXML
    public TextField amount;
    @FXML
    public Button createDeposit;
    @FXML
    public Button backButton;
    @FXML
    public Button viewProfit;

    private UserDto user;
    private String accountNr;
    public void initialize(UserDto user, String accountNr){
        this.user = user;
        this.accountNr = accountNr;
        type.getItems().addAll("ANNUAL", "TWO_YEAR", "FIVE_YEAR");
    }

    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == backButton){
            openUserPage(event, user);
        } else if (event.getSource() == createDeposit) {
            if(areFieldsNotEmpty()){
                var msg = sendToServerWithResponse(
                    "DEPOSIT,CREATE,"+
                    accountNr + "," +
                    type.getValue() + "," +
                    amount.getText());
                if(isResponseValid(msg)){
                    showInfo("CREATED", "Deposit was created successfully!");
                    openUserPage(event, user);
                }
            }
        } else if (event.getSource() == viewProfit) {
            if(areFieldsNotEmpty()){
                var msg = sendToServerWithResponse(
                        "DEPOSIT,VIEW_PROFIT," +
                        type.getValue() +"," +
                        amount.getText());
                if(isResponseValid(msg)){
                    showInfo("PROFIT" , "Profit of the deposit equals " + msg);
                }
            }
        }
    }
    private boolean areFieldsNotEmpty(){
        if(type.getValue() == null || amount.getText() == null) {
            showWarning("Fields are empty");
            return false;
        }
        else if(amount.getText().matches("[0-9]+")) {
            return true;
        }
        showWarning("Incorrect characters in amount field!");
        return false;
    }
}
