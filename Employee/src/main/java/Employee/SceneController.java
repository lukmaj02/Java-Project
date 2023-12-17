package Employee;

import Employee.GUI.EmployeePage;
import Employee.GUI.FailedCreditsPage;
import Employee.GUI.FrontPage;
import Employee.GUI.ProcessedCreditsPage;
import Employee.GUI.RegistrationFormula.FirstFormulaPage;
import Employee.GUI.RegistrationFormula.FourthFormulaPage;
import Employee.GUI.RegistrationFormula.SecondFormulaPage;
import Employee.GUI.RegistrationFormula.ThirdFormulaPage;
import Employee.dto.CreditDto;
import Employee.dto.EmployeeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class SceneController extends Employee {
    protected void openFrontPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontPage.fxml"));
        _root = loader.load();

        FrontPage frontPage = loader.getController();
        setStage(event);
    }
    protected void openEmployeePage(ActionEvent event, EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EmployeePage.fxml"));
        _root = loader.load();

        EmployeePage employeePage = loader.getController();
        setStage(event);
    }
    protected void openFormula(ActionEvent event, ArrayList<String > firstFormulaPageData, ArrayList<String > secondFormulaPageData, ArrayList<String > thirdFormulaPageData, EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/FirstFormulaPage.fxml"));
        _root = loader.load();

        FirstFormulaPage formulaPage = loader.getController();
        formulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData, employee); // change randomly|depending on users count
        setStage(event);
    }
    protected void openSecondFormulaPage(ActionEvent event,
                                         ArrayList<String> firstFormulaPageData,
                                         ArrayList<String > secondFormulaPageData,
                                         ArrayList<String > thirdFormulaPageData,
                                         EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/SecondFormulaPage.fxml"));
        _root = loader.load();

        SecondFormulaPage secondFormulaPage = loader.getController();
        secondFormulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData, employee); // change randomly|depending on users count
        setStage(event);
    }
    protected void openThirdFormulaPage(ActionEvent event,
                                        ArrayList<String> firstFormulaPageData,
                                        ArrayList<String > secondFormulaPageData,
                                        ArrayList<String > thirdFormulaPageData,
                                        EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/ThirdFormulaPage.fxml"));
        _root = loader.load();

        ThirdFormulaPage thirdFormulaPage = loader.getController();
        thirdFormulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData, employee); // change randomly|depending on users count
        setStage(event);
    }
    protected void openFourhtFormulaPage(ActionEvent event,
                                         ArrayList<String> firstFormulaPageData,
                                         ArrayList<String > secondFormulaPageData,
                                         ArrayList<String > thirdFormulaPageData,
                                         EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/FourthFormulaPage.fxml"));
        _root = loader.load();

        FourthFormulaPage fourthFormulaPage = loader.getController();
        fourthFormulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData, employee); // change randomly|depending on users count
        setStage(event);
    }
    protected void openFailedCreditsPage(ActionEvent event, Set<CreditDto> credits, EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FailedCreditsPage.fxml"));
        _root = loader.load();

        FailedCreditsPage failedCreditsPage = loader.getController();
        failedCreditsPage.initialize(credits,employee); // change randomly|depending on users count
        setStage(event);
    }
    protected void openProcessedCreditsPage(ActionEvent event, Set<CreditDto> credits, EmployeeDto employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProcessedCreditsPage.fxml"));
        _root = loader.load();

        ProcessedCreditsPage processedCreditsPage = loader.getController();
        processedCreditsPage.initialize(credits,employee); // change randomly|depending on users count
        setStage(event);
    }
    protected void showProvideDataWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Data warning, you haven't fill all fields.");
        alert.setContentText("Provide more details!");
        alert.show();
    }
}
