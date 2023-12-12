package Employee;

import Employee.Employee;
import Employee.GUI.EmployeePage;
import Employee.GUI.FrontPage;
import Employee.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class SceneController extends Employee {
    protected void openFrontPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontPage.fxml"));
        _root = loader.load();

        FrontPage frontPage = loader.getController();
        setStage(event);
    }
    protected void openEmployeePage(ActionEvent event, UserDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EmployeePage.fxml"));
        _root = loader.load();

        EmployeePage employeePage = loader.getController();
        setStage(event);
    }
}
