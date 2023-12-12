package Client.GUI.User.Transaction;

import Client.SceneController;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TransactionCreatePage extends SceneController {
    @FXML
    public TextField amount;
    @FXML
    public TextField title;
    @FXML
    public TextField toAccount;
    @FXML
    public ChoiceBox<String> type;
    @FXML
    public Button backButton;
    @FXML
    public Button sendTransaction;
    @FXML
    public Label typeLabel;
    private UserDto user;
    private String accountNr;
    private String currency;

    public void initialize(UserDto user, String accountNr,String currency){
        this.user = user;
        this.accountNr = accountNr;
        this.currency = currency;
        type.getItems().addAll("BLIK", "TRADITIONAL_TRANSFER", "EXPRESS_TRANSFER");
        type.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("BLIK")){
                typeLabel.setText("to Phone Number");
            } else typeLabel.setText("to Account Number");
        });
    }
    public boolean areFieldsValid(){
        if(type.getValue() == null || toAccount.getText() == null || title.getText() == null || amount.getText() == null){
            showWarning("Empty fields!");
        } else if(!amount.getText().matches("^[0-9]+(\\.[0-9]{1,2})?$")){
            showWarning("Invalid amount!");
        } else if (type.getValue().equals("BLIK") && (!(type.getValue().matches("[0-9]") || toAccount.getText().length() == 9))) {
            showWarning("Invalid Phone Number!");
        } else if ((!type.getValue().equals("BLIK")) && (!(type.getValue().matches("[0-9]") || toAccount.getText().length() == 26))) {
            showWarning("Invalid Account Number!");
        } else return true;
        return false;
    }



    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == backButton){
            openUserPage(event, user);
        }
        else if(event.getSource() == sendTransaction){
            if(areFieldsValid()){
                var msg = sendToServerWithResponse("TRANSACTION,CREATE,"+
                        accountNr + "," +
                        toAccount.getText() + "," +
                        amount.getText() + "," +
                        title.getText() + "," +
                        currency + "," +
                        type.getValue());
                if(isResponseValid(msg)) {
                    showInfo("CREATED", "Transaction send successfully");
                    openUserPage(event, user);
                }
            }
        }
    }
}
