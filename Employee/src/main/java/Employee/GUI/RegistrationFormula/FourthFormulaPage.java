package Employee.GUI.RegistrationFormula;

import Employee.SceneController;
import Employee.dto.EmployeeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class FourthFormulaPage extends SceneController {
    @FXML
    public Button cancelButton;
    @FXML
    public Button submitButton;
    @FXML
    public Label formulaLabel;
    @FXML
    public TextField firstname;
    @FXML
    public TextField lastname;
    @FXML
    public TextField email;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField dateOfBirth;
    @FXML
    public TextField address;
    @FXML
    public TextField city;
    @FXML
    public TextField accountType;
    @FXML
    public TextField accountCurrency;

    public ArrayList<String > firstPageData = new ArrayList<>();
    public ArrayList<String > secondPageData = new ArrayList<>();
    public ArrayList<String > thirdPageData = new ArrayList<>();
    private EmployeeDto employee;
    private String password;


    public void initializeFormula(Integer number,
                                  ArrayList<String> firstFormulaPageData,
                                  ArrayList<String> secondFormulaPageData,
                                  ArrayList<String> thirdFormulaPageData,
                                  EmployeeDto employee) {
        this.employee = employee;
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
        firstPageData = firstFormulaPageData;
        secondPageData = secondFormulaPageData;
        thirdPageData = thirdFormulaPageData;
        firstname.setText(firstPageData.get(0));
        lastname.setText(firstPageData.get(1));
        dateOfBirth.setText(firstPageData.get(2));
        email.setText(thirdPageData.get(0));
        phoneNumber.setText(thirdPageData.get(2));
        address.setText(firstPageData.get(5));
        city.setText(firstPageData.get(6));
        accountType.setText(secondPageData.get(0).toUpperCase());
        accountCurrency.setText(secondPageData.get(3).toUpperCase());
        password = thirdPageData.get(2);
    }

    private String getNeededInfoToCreateUser() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        LocalDate dateOfBirth = LocalDate.parse(firstPageData.get(2), dateTimeFormatter);
        String maritalStatus;
        String gender;

        if (Objects.equals(firstPageData.get(3), "Male")) {
            gender = "MALE";
        }else {
            gender = "FEMALE";
        }
        if (Objects.equals(firstPageData.get(4), "Single"))
            maritalStatus = "SINGLE";
        else if (Objects.equals(firstPageData.get(4), "Married"))
            maritalStatus = "MARRIED";
        else if (Objects.equals(firstPageData.get(4), "Divorced"))
            maritalStatus = "DIVORCED";
        else
            maritalStatus = "WIDOWED";

        return ("USER," + "REGISTER," +
                firstname.getText() + "," +
                lastname.getText() + "," +
                email.getText() + "," +
                password + "," +
                phoneNumber.getText() + "," +
                dateOfBirth + "," +
                address.getText() + "," +
                city.getText() + "," +
                gender + "," +
                maritalStatus +
                ",EMPLOYEE");
    }

    public String getInfoToCreateAccount(){
        return ("BANK_ACCOUNT,CREATE,"+
                email.getText() +"," +
                accountType.getText() +"," +
                accountCurrency.getText());
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == submitButton) {
            var msg = sendToServerWithResponse(getNeededInfoToCreateUser());
            if(isResponseValid(msg)){
                var account = sendToServerWithResponse(getInfoToCreateAccount());
                if(isResponseValid(account)){
                    showInfo("REGISTRATION", "Registration was successfully!");
                    openFrontPage(actionEvent);
                }
            }
        } else if (actionEvent.getSource() == cancelButton) {
            openThirdFormulaPage(actionEvent, firstPageData, secondPageData, thirdPageData, employee);
        }
    }
}
