package Employee.GUI;

import Employee.SceneController;
import Employee.dto.CreditDto;
import Employee.dto.EmployeeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeePage extends SceneController {
    @FXML
    public Button registerEmployee;
    @FXML
    public Button viewProcessedCredits;
    @FXML
    public Button logoutButton;
    @FXML
    public Button viewFailedCredits;
    private EmployeeDto employee;

    public void executeAnAction(ActionEvent event) throws IOException {
        if(event.getSource() == logoutButton){
            openFrontPage(event);
        } else if (event.getSource() == registerEmployee) {
            openFormula(event, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), employee);

        } else if(event.getSource() == viewFailedCredits){
            var msg = sendToServerWithResponse("CREDIT,ALL_FAILED,");
            if(isResponseValid(msg)){
                openFailedCreditsPage(event,CreditDto.mapper(msg), employee);
            }

        } else if (event.getSource() == viewProcessedCredits) {
            var msg = sendToServerWithResponse("CREDIT,ALL_PROCESSED,");
            if(isResponseValid(msg)){
                openProcessedCreditsPage(event,CreditDto.mapper(msg), employee);
            }
        }
    }
}
