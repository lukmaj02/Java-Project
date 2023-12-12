package Client.GUI.User.Transaction;

import Client.Client;
import Client.dto.BankAccountDto;
import Client.dto.TransactionDto;
import Client.dto.UserDto;
import com.Projekt.Bankomat.Models.User;
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

public class UserTransactionsPage extends Client {
    public Button backButton;
    @FXML
    public TableView<TransactionDto> userTransactions;
    @FXML
    public TableColumn<TransactionDto, String> fromAccountNr;
    @FXML
    public TableColumn<TransactionDto, String> toAccountNr;
    @FXML
    public TableColumn<TransactionDto, String> amount;
    @FXML
    public TableColumn<TransactionDto, String> currencyType;
    @FXML
    public TableColumn<TransactionDto, String> transactionDate;
    @FXML
    public TableColumn<TransactionDto, String> title;
    @FXML
    public TableColumn<TransactionDto, String> isValid;
    @FXML
    public TableColumn<TransactionDto, String> type;
    @FXML
    public Button viewSentTransactions;
    @FXML
    public Button viewReceivedTransactions;
    @FXML
    public Button viewAllTransactions;


    private Set<TransactionDto> allTransactions;
    private Set<TransactionDto> sentToUserTransactions;
    private Set<TransactionDto> sentByUserTransactions;
    private UserDto user;

    public void initialize(Set<TransactionDto> allTransactions,
                           Set<TransactionDto> sentToUser,
                           Set<TransactionDto> sentByUser,
                           UserDto user){
        this.allTransactions = allTransactions;
        this.sentToUserTransactions = sentToUser;
        this.sentByUserTransactions = sentByUser;
        this.user = user;
        fromAccountNr.setCellValueFactory(new PropertyValueFactory<>("fromAccountNr"));
        toAccountNr.setCellValueFactory(new PropertyValueFactory<>("toAccountNr"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        currencyType.setCellValueFactory(new PropertyValueFactory<>("currencyType"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        isValid.setCellValueFactory(new PropertyValueFactory<>("isValid"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList<TransactionDto> list = FXCollections.observableList(allTransactions.stream().toList());
        userTransactions.setItems(list);
    }
    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()==backButton){
            openUserPage(actionEvent, user);
        }
        else if(actionEvent.getSource()==viewSentTransactions){
            ObservableList<TransactionDto> list = FXCollections.observableList(sentByUserTransactions.stream().toList());
            userTransactions.setItems(list);
        }
        else if(actionEvent.getSource() == viewReceivedTransactions){
            ObservableList<TransactionDto> list = FXCollections.observableList(sentToUserTransactions.stream().toList());
            userTransactions.setItems(list);
        }
        else if(actionEvent.getSource() == viewAllTransactions){
            ObservableList<TransactionDto> list = FXCollections.observableList(allTransactions.stream().toList());
            userTransactions.setItems(list);
        }
    }
}
