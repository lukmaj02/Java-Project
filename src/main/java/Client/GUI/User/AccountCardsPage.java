package Client.GUI.User;

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

public class AccountCardsPage extends Client {
    @FXML
    public TableView<CardDto> accountCards;
    @FXML
    public TableColumn<CardDto,String> cardNumber;
    @FXML
    public TableColumn<CardDto,String> expirationDate;
    @FXML
    public TableColumn<CardDto,String> cvc;
    @FXML
    public TableColumn<CardDto,String> cardType;
    @FXML
    public TableColumn<CardDto,String> isDiscard;
    @FXML
    public TableColumn<CardDto, String> pin;
    @FXML
    public Button backButton;
    @FXML
    public Button deleteCard;
    @FXML
    public Button discardCard;
    @FXML
    public Button extendValidity;
    private UserDto user;
    private String currentCardNr;
    public TableView.TableViewSelectionModel<CardDto> selectedCard;

    public void initialize(Set<CardDto> bankAccountCards, UserDto user){
        this.user = user;
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        expirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        cvc.setCellValueFactory(new PropertyValueFactory<>("cvc"));
        cardType.setCellValueFactory(new PropertyValueFactory<>("cardType"));
        pin.setCellValueFactory(new PropertyValueFactory<>("pin"));
        isDiscard.setCellValueFactory(new PropertyValueFactory<>("isDiscard"));

        ObservableList<CardDto> list = FXCollections.observableList(bankAccountCards.stream().toList());
        selectedCard = accountCards.getSelectionModel();
        selectedCard.setSelectionMode(SelectionMode.SINGLE);
        accountCards.setItems(list);
        accountCards.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && !newSelection.equals(oldSelection)) {
                currentCardNr = newSelection.getCardNumber();
            }
        });
    }
    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == backButton){
            openUserPage(event,user);
        }
        else if(event.getSource() == discardCard && currentCardNr != null){
            var msg =sendToServerWithResponse("CARD,DISCARD," + currentCardNr);
            if(isResponseValid(msg)) {
                showInfo("DISCARD", "Card was discarded successfully");
                openUserPage(event, user);
            }
        }
        else if (event.getSource() == deleteCard && currentCardNr != null) {
            var msg = sendToServerWithResponse("CARD,DELETE,"+currentCardNr);
            if(isResponseValid(msg)){
                showInfo("DELETE", "Card was deleted successfully");
                openUserPage(event, user);
            }
        }
        else if (event.getSource() == extendValidity && currentCardNr != null) {
            var msg = sendToServerWithResponse("CARD,EXTEND," + currentCardNr);
            if(isResponseValid(msg)){
                showInfo("EXTEND", "Card was extended successfully");
                openUserPage(event, user);
            }
        }
    }
}
