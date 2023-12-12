package Client.GUI.User.Credit;

import Client.SceneController;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RequestCreditPage extends SceneController {

    @FXML
    public TextField amount;
    @FXML
    public TextField count;
    @FXML
    public Button creditRateButton;
    @FXML
    public Button requestButton;
    @FXML
    public ChoiceBox<String> type;
    @FXML
    public Button backButton;

    private UserDto user;
    private String accountNr;

    public void initialize(UserDto user, String accountNr){
        this.user = user;
        this.accountNr = accountNr;
        type.getItems().add("CONST");
    }
    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == creditRateButton && isAllFieldsImplemented()){
            var msg = sendToServerWithResponse(
                    "CREDIT,CHECK_RATE," +
                            amount.getText() + "," +
                            count.getText() + "," +
                            type.getValue());
            if(isResponseValid(msg)) {
                showInfo(
                        "INSTALLMENT RATE",
                        "For this credit rate equals " + msg);
            }
        } else if (event.getSource() == requestButton && isAllFieldsImplemented()) {
            var msg = sendToServerWithResponse(
                    "CREDIT,REQUEST,"+
                        accountNr + "," +
                        amount.getText() + "," +
                        count.getText() + "," +
                        type.getValue().toUpperCase());
            if(isResponseValid(msg)){
                showInfo("REQUEST CREDIT", "Credit requested successfully");
                openUserPage(event, user);
            }
        } else if (event.getSource() == backButton) {
            openUserPage(event, user);
        }
    }

    private boolean isAllFieldsImplemented(){
        if(amount.getText() == null || count.getText() == null || type.getValue() == null) {
            showWarning("Fields are empty!");
            return false;
        }
        else if(amount.getText().matches("[0-9]+") && count.getText().matches("[0-9]+")) {
            return true;
        }
        showWarning("Incorrect characters in fields, only numbers allowed");
        return false;
    }
}
