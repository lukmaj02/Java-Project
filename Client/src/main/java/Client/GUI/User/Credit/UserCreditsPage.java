package Client.GUI.User.Credit;

import Client.SceneController;
import Client.dto.CreditDto;
import Client.dto.UserDto;
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

public class UserCreditsPage extends SceneController {
    @FXML
    public TableView<CreditDto> userCredits;
    @FXML
    public TableColumn<CreditDto,String> installmentCount;
    @FXML
    public TableColumn<CreditDto,String> installmentAmount;
    @FXML
    public TableColumn<CreditDto,String> creditAmount;
    @FXML
    public TableColumn<CreditDto,String> lendingRate;
    @FXML
    public TableColumn<CreditDto,String> creditType;
    @FXML
    public TableColumn<CreditDto,String> accountNumber;
    @FXML
    public TableColumn<CreditDto,String> currency;
    @FXML
    public TableColumn<CreditDto,String> creditStatus;
    @FXML
    public Button backButton;
    @FXML
    public Button cancelCredit;
    private UserDto user;
    private String currentCreditId;
    public TableView.TableViewSelectionModel<CreditDto> selectedCredit;

    public void initialize(Set<CreditDto> credits, UserDto user){
        this.user = user;
        installmentCount.setCellValueFactory(new PropertyValueFactory<>("installmentCount"));
        accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        creditAmount.setCellValueFactory(new PropertyValueFactory<>("creditAmount"));
        lendingRate.setCellValueFactory(new PropertyValueFactory<>("lendingRate"));
        creditType.setCellValueFactory(new PropertyValueFactory<>("creditType"));
        installmentAmount.setCellValueFactory(new PropertyValueFactory<>("installmentAmount"));
        currency.setCellValueFactory(new PropertyValueFactory<>("currency"));
        creditStatus.setCellValueFactory(new PropertyValueFactory<>("creditStatus"));
        ObservableList<CreditDto> list = FXCollections.observableList(credits.stream().toList());
        userCredits.setItems(list);
        userCredits.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && !newSelection.equals(oldSelection)) {
                currentCreditId = newSelection.getCreditId();
            }
        });
    }
    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource()== backButton){
            openUserPage(event,user);
        } else if (event.getSource() == cancelCredit && currentCreditId != null) {
            var msg = sendToServerWithResponse("CREDIT,CANCEL," + currentCreditId);
            if(isResponseValid(msg)){
                showInfo("CANCELLED", "Credit cancelled sucessfully");
                openUserPage(event, user);
            }
        }
    }
}
