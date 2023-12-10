package Client.GUI.User;

import Client.Client;
import Client.dto.BankAccountDto;
import Client.dto.UserDto;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.util.Set;

public class UserAccountsPage extends Client {
    @FXML
    public TableColumn<BankAccountDto, String> accountNr;
    @FXML
    public TableColumn<BankAccountDto, String> balance;
    @FXML
    public TableColumn<BankAccountDto, String> currencyType;
    @FXML
    public TableColumn<BankAccountDto, String> accountType;
    @FXML
    public TableView<BankAccountDto> userAccounts;
    @FXML
    public Button backButton;
    @FXML
    public Button createTransaction;
    @FXML
    public Button createDeposit;
    @FXML
    public Button requestCredit;
    @FXML
    public Button deleteAccount;
    private BankAccountDto currentAccount;
    private UserDto user;
    public Set<BankAccountDto> bankAccounts;
    public void initialize(Set<BankAccountDto> bankAccounts, UserDto user){
        this.bankAccounts = bankAccounts;
        this.user = user;
        accountNr.setCellValueFactory(new PropertyValueFactory<>("accountNr"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        currencyType.setCellValueFactory(new PropertyValueFactory<>("currencyType"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        ObservableList<BankAccountDto> list = FXCollections.observableList(bankAccounts.stream().toList());
        userAccounts.setItems(list);
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == backButton){
            openUserPage(actionEvent,user);
        }
        else if(actionEvent.getSource()==createTransaction){
            //todo openCreationTransactionPage(actionEvent, user);
        }
        else if(actionEvent.getSource() == createDeposit){
            //todo openCreationDepositPage(actionEvent,user);
        }
        else if(actionEvent.getSource() == requestCredit){
            //todo openRequestingCreditPage(actionEvent, user);
        } else if (actionEvent.getSource()==deleteAccount) {
            //todo 
        }
    }
}
