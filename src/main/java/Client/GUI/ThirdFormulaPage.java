package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ThirdFormulaPage extends SceneController {
    @FXML
    private Label formulaLabel;
    @FXML
    private Button previousPageButton;
    @FXML
    private Button nextPageButton;
    @FXML
    private PasswordField userRewritePasswordField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField areaCodeTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField cardPinTextField;

    public ArrayList<String > firstPageData = new ArrayList<>();
    public ArrayList<String > secondPageData = new ArrayList<>();
    public ArrayList<String > thirdPageData = new ArrayList<>();

    public void initializeFormula(Integer number, ArrayList<String> firstFormulaPageData, ArrayList<String> secondFormulaPageData, ArrayList<String> thirdFormulaPageData) {
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
        firstPageData = firstFormulaPageData;
        secondPageData = secondFormulaPageData;
        thirdPageData = thirdFormulaPageData;
        initializeData();
    }

    private void initializeData() {
        if (thirdPageData.isEmpty())
            return;

        emailTextField.setText(thirdPageData.get(0));
        areaCodeTextField.setText(thirdPageData.get(1));
        phoneNumberTextField.setText(thirdPageData.get(2));
        // the rest of the variables user should provide again
    }

    private void fillThirdPageVariables() {
        thirdPageData.clear();

        if (Objects.equals(emailTextField.getText(), "Provide more details."))
            emailTextField.setText("");
        if (Objects.equals(areaCodeTextField.getText(), "Provide more details."))
            areaCodeTextField.setText("");
        if (Objects.equals(phoneNumberTextField.getText(), "Provide more details."))
            phoneNumberTextField.setText("");
        if (Objects.equals(userPasswordField.getText(), "Provide more details."))
            userPasswordField.setText("");
        if (Objects.equals(cardPinTextField.getText(), "Provide more details."))
            cardPinTextField.setText("");

        thirdPageData.addAll(Arrays.asList
                (
                    emailTextField.getText(), areaCodeTextField.getText(), phoneNumberTextField.getText(), userPasswordField.getText(), cardPinTextField.getText()
                )
        );
    }


    private boolean checkIfUserProvideAllData() {
        boolean ifSomethingEmpty = false;

        if (Objects.equals(emailTextField.getText(), "") || Objects.equals(emailTextField.getText(), "Provide more details.")) {
            emailTextField.setText("Provide more details.");
            ifSomethingEmpty = true;
        }
        if (Objects.equals(areaCodeTextField.getText(), "") || Objects.equals(areaCodeTextField.getText(), "Provide more details.")) {
            areaCodeTextField.setText("Provide more details.");
            ifSomethingEmpty = true;
        }
        if (Objects.equals(phoneNumberTextField.getText(), "") || Objects.equals(phoneNumberTextField.getText(), "Provide more details.")) {
            phoneNumberTextField.setText("Provide more details.");
            ifSomethingEmpty = true;
        }
        if (Objects.equals(userPasswordField.getText(), "")) {
            ifSomethingEmpty = true;
        }
        if (Objects.equals(userRewritePasswordField.getText(), "")) {
            ifSomethingEmpty = true;
        }
        if (Objects.equals(cardPinTextField.getText(), "")) {
            ifSomethingEmpty = true;
        }

        return !ifSomethingEmpty;
    }

    private void showPasswordDoesntMatchWarning() {
        userPasswordField.setText("");
        userRewritePasswordField.setText("");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You haven't input same password in two fields.");
        alert.setContentText("Please make sure your password are the same in two fields.");
        alert.show();
    }

    private void showPasswordDoesntHaveEnoughChars() {
        userPasswordField.setText("");
        userRewritePasswordField.setText("");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You haven't put 9 characters in password field.");
        alert.setContentText("Please make sure your password has at least 9 chars.");
        alert.show();
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == previousPageButton) {
            fillThirdPageVariables();

            SecondFormulaPage secondFormulaPage = new SecondFormulaPage();
            secondFormulaPage.firstPageData = firstPageData;
            secondFormulaPage.secondPageData = secondPageData;
            secondFormulaPage.thirdPageData = thirdPageData;

            openSecondFormulaPage(actionEvent, firstPageData, secondPageData, thirdPageData);
        } else if (actionEvent.getSource() == nextPageButton) {
            fillThirdPageVariables();
            if (!checkIfUserProvideAllData()) {
                showProvideDataWarning();
                return;
            }
            if (!Objects.equals(userPasswordField.getText(), userRewritePasswordField.getText())) {
                showPasswordDoesntMatchWarning();
                return;
            }
            if (userPasswordField.getText().length() < 9) {
                showPasswordDoesntHaveEnoughChars();
                return;
            }

            FourthFormulaPage fourthFormulaPage = new FourthFormulaPage();
            fourthFormulaPage.firstPageData = firstPageData;
            fourthFormulaPage.secondPageData = secondPageData;
            fourthFormulaPage.thirdPageData = thirdPageData;

            openFourhtFormulaPage(actionEvent, firstPageData, secondPageData, thirdPageData);
        }
    }

}
