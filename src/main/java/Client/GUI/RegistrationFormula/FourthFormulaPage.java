package Client.GUI.RegistrationFormula;

import Client.Client;
import com.Projekt.Bankomat.Enums.Gender;
import com.Projekt.Bankomat.Enums.MaritalStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class FourthFormulaPage extends Client {
    @FXML
    public Label cardNumberLabel;
    @FXML
    public Label pinLabel;
    @FXML
    public CheckBox checkBoxTermsAndCondition;
    @FXML
    public Button cancelButton;
    @FXML
    public Button submitButton;
    @FXML
    public Label formulaLabel;

    public ArrayList<String > firstPageData = new ArrayList<>();
    public ArrayList<String > secondPageData = new ArrayList<>();
    public ArrayList<String > thirdPageData = new ArrayList<>();

    public void initializeFormula(Integer number, ArrayList<String> firstFormulaPageData, ArrayList<String> secondFormulaPageData, ArrayList<String> thirdFormulaPageData) {
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));

        firstPageData = firstFormulaPageData;
        secondPageData = secondFormulaPageData;
        thirdPageData = thirdFormulaPageData;

        cardNumberLabel.setText("XXXX-XXXX-XXXX-TEST");
        pinLabel.setText("TEST");
        checkBoxTermsAndCondition.setText("I declare that all data entered by me is true and I have read and agreed to the Terms and Conditions");
    }

    private String getNeededInfoToCreateUser() throws Exception {
        String firstName = firstPageData.get(0);
        String lastName = firstPageData.get(1);
        String email =  thirdPageData.get(0);
        String password = thirdPageData.get(3);
        String phoneNumber = thirdPageData.get(2);
        String address = firstPageData.get(5);
        String city = firstPageData.get(6);

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

        return ("USER," + "REGISTER," + firstName + "," + lastName + "," + email + "," + password + "," +
                phoneNumber + "," + dateOfBirth + "," + address + "," + city + "," + gender + "," + maritalStatus + ",USER");
    }

    public String getInfoToCreateAccount(){
        String email =  thirdPageData.get(0);
        String accountType = secondPageData.get(0).toUpperCase();
        String currency = secondPageData.get(4).toUpperCase();
        return "BANK_ACCOUNT,CREATE,"+email +"," + accountType +"," + currency;
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == submitButton && checkBoxTermsAndCondition.isSelected()) {
            var msg = sendToServerWithResponse(getNeededInfoToCreateUser());
            if(isResponseValid(msg)){
                var account = sendToServerWithResponse(getInfoToCreateAccount());
                if(isResponseValid(account)){
                    showInfo("REGISTRATION", "Registration was created successfully!");
                    openFrontPage(actionEvent);
                }
            }
        }
    }
}
