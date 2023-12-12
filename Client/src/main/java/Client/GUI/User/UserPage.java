package Client.GUI.User;

import Client.SceneController;
import Client.dto.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Optional;

public class UserPage extends SceneController {

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
    @FXML
    public Button viewCredits;
    @FXML
    public Button logOut;

    private UserDto user;

    public void initialize(UserDto user) {
        this.user = user;
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == userInformations){
            openUserInfo(actionEvent,user);
        }
        else if(actionEvent.getSource() == deleteUser){
            var msg = sendToServerWithResponse("USER,DELETE," + user.getEmail());
            if(isResponseValid(msg)) {
                showInfo("DELETION", "Profile deleted successfully");
                openFrontPage(actionEvent);
            }
        }
        else if (actionEvent.getSource()== logOut) {
            openFrontPage(actionEvent);
        }
        else if(actionEvent.getSource() == accountCreation){
            openAccountCreationPage(actionEvent, user);
        }
        else if(actionEvent.getSource() == viewBankAccounts){
            var msg = sendToServerWithResponse("BANK_ACCOUNT,USER_ACCOUNTS," + user.getEmail());
            if(isResponseValid(msg)) openUserAccounts(actionEvent, BankAccountDto.mapper(msg), user);
        }
        else if(actionEvent.getSource() == viewTransactions){
            var allT = sendToServerWithResponse("TRANSACTION,ALL_USER," + user.getEmail());
            var receivedT = sendToServerWithResponse("TRANSACTION,ALL_TO_USER,"+user.getEmail());
            var sentT = sendToServerWithResponse("TRANSACTION,ALL_FROM_USER," + user.getEmail());
            if(isResponseValid(allT) && isResponseValid(receivedT) && isResponseValid(sentT)) {
                openUserTransactionPage(actionEvent,
                        TransactionDto.mapper(allT),
                        TransactionDto.mapper(receivedT),
                        TransactionDto.mapper(sentT),
                        user);
            }
        }
        else if (actionEvent.getSource() == viewDeposits){
            var msg = sendToServerWithResponse("DEPOSIT,ALL_USER," + user.getEmail());
            if(isResponseValid(msg)){
                openUserDepositPage(actionEvent, DepositDto.mapper(msg), user);
            }
        }
        else if (actionEvent.getSource() == viewCredits) {
            var msg = sendToServerWithResponse("CREDIT,ALL_USER," + user.getEmail());
            if(isResponseValid(msg)) openUserCreditPage(actionEvent, CreditDto.mapper(msg), user);
        }
    }
}
