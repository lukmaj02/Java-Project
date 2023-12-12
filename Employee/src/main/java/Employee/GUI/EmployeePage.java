package Employee.GUI;

import Employee.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EmployeePage extends SceneController {
    @FXML
    public Button registerEmployee;
    @FXML
    public Button viewProcessedCredits;
    @FXML
    public Button logoutButton;
    @FXML
    public Button viewFailedCredits;

    public void executeAnAction(ActionEvent event) {
    }
}
