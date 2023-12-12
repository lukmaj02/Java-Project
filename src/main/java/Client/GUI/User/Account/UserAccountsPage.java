package Client.GUI.User.Account;

import Client.Client;
import Client.dto.BankAccountDto;
import Client.dto.CardDto;
import Client.dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
import java.util.Set;

public class UserAccountsPage extends Client {
    @FXML
    public TableView<BankAccountDto> userAccounts;
    @FXML
    public TableColumn<BankAccountDto, String> accountNr;
    @FXML
    public TableColumn<BankAccountDto, String> balance;
    @FXML
    public TableColumn<BankAccountDto, String> currencyType;
    @FXML
    public TableColumn<BankAccountDto, String> accountType;
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
    @FXML
    public Button viewCards;
    @FXML
    public Button createCard;
    private String currentAccountNr;
    private UserDto user;
    public TableView.TableViewSelectionModel<BankAccountDto> selectedAccount;
    private String currentCurrency;

    public void initialize(Set<BankAccountDto> bankAccounts, UserDto user){
        this.user = user;
        accountNr.setCellValueFactory(new PropertyValueFactory<>("accountNr"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        currencyType.setCellValueFactory(new PropertyValueFactory<>("currencyType"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        ObservableList<BankAccountDto> list = FXCollections.observableList(bankAccounts.stream().toList());
        selectedAccount = userAccounts.getSelectionModel();
        selectedAccount.setSelectionMode(SelectionMode.SINGLE);
        userAccounts.setItems(list);
        userAccounts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && !newSelection.equals(oldSelection)) {
                currentAccountNr = newSelection.getAccountNr();
                currentCurrency = newSelection.getCurrencyType();
            }
        });
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == backButton) {
            openUserPage(actionEvent, user);
        } else if (actionEvent.getSource() == createTransaction) {
            openTransactionCreationPage(actionEvent, user, currentAccountNr, currentCurrency);
        } else if (actionEvent.getSource() == requestCredit) {
            openRequestingCreditPage(actionEvent, user, currentAccountNr);
        } else if (actionEvent.getSource() == createCard && currentAccountNr != null) {
            openCardCreationPage(actionEvent, user, currentAccountNr);
        } else if (actionEvent.getSource() == viewCards && currentAccountNr != null) {
            var msg = sendToServerWithResponse("CARD,ACCOUNT_CARDS," + currentAccountNr);
            if (isResponseValid(msg)) {
                openAccountCardsPage(actionEvent, CardDto.mapper(msg), user);
            }
        } else if (actionEvent.getSource() == createDeposit && currentAccountNr != null) {
            openDepositCreationPage(actionEvent, user, currentAccountNr);
        } else if (actionEvent.getSource() == deleteAccount && currentAccountNr != null) {
            var msg = sendToServerWithResponse("BANK_ACCOUNT,DELETE," + currentAccountNr);
            if (isResponseValid(msg)) {
                openUserPage(actionEvent, user);
                showInfo("DELETION", "Bank account deleted successfully!");
            }
        }
    }
}
