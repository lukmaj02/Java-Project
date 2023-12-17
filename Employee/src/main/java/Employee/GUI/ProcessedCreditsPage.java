package Employee.GUI;

import Employee.SceneController;
import Employee.dto.CreditDto;
import Employee.dto.EmployeeDto;
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

public class ProcessedCreditsPage extends SceneController {
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
    public Button activeCredit;
    @FXML
    public Button refuseCredit;
    private EmployeeDto employee;
    private String currentCreditId;
    public TableView.TableViewSelectionModel<CreditDto> selectedCredit;

    public void initialize(Set<CreditDto> credits, EmployeeDto employee){
        this.employee = employee;
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
            openEmployeePage(event, employee);
        } else if (event.getSource() == activeCredit && currentCreditId != null) {
            var msg = sendToServerWithResponse("CREDIT,ACTIVE," + currentCreditId);
            if(isResponseValid(msg)){
                showInfo("ACTIVE", "Credit activated successfully");
                openEmployeePage(event, employee);
            }
        } else if (event.getSource() == refuseCredit && currentCreditId != null) {
            var msg = sendToServerWithResponse("CREDIT,REFUSE," + currentCreditId);
            if(isResponseValid(msg)){
                showInfo("REFUSED", "Credit refused successfully");
                openEmployeePage(event, employee);
            }
        }
    }
}
