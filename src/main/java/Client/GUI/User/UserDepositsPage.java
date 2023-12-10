package Client.GUI.User;

import Client.Client;
import Client.dto.BankAccountDto;
import Client.dto.DepositDto;
import Client.dto.UserDto;
import com.Projekt.Bankomat.Models.Deposit;
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

public class UserDepositsPage extends Client {
    @FXML
    public TableView<DepositDto> userDeposits;
    @FXML
    public TableColumn<DepositDto,String> accountNr;
    @FXML
    public TableColumn<DepositDto,String> amount;
    @FXML
    public TableColumn<DepositDto,String> currency;
    @FXML
    public TableColumn<DepositDto,String> finishDate;
    @FXML
    public TableColumn<DepositDto,String> creationDate;
    @FXML
    public TableColumn<DepositDto,String> type;
    @FXML
    public TableColumn<DepositDto,String> status;
    @FXML
    public Button backButton;

    private UserDto user;

    public void initialize(Set<DepositDto> deposits, UserDto user){
        this.user = user;
        accountNr.setCellValueFactory(new PropertyValueFactory<>("accountNr"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        currency.setCellValueFactory(new PropertyValueFactory<>("currency"));
        finishDate.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<DepositDto> list = FXCollections.observableList(deposits.stream().toList());
        userDeposits.setItems(list);
    }
    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == backButton){
            openUserPage(actionEvent,user);
        }
    }
}
