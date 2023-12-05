package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

        thirdPageData.addAll(Arrays.asList
                (
                    emailTextField.getText(), areaCodeTextField.getText(), phoneNumberTextField.getText(), userPasswordField.getText(), cardPinTextField.getText()
                )
        );
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

            FourthFormulaPage fourthFormulaPage = new FourthFormulaPage();
            fourthFormulaPage.firstPageData = firstPageData;
            fourthFormulaPage.secondPageData = secondPageData;
            fourthFormulaPage.thirdPageData = thirdPageData;

            openFourhtFormulaPage(actionEvent, firstPageData, secondPageData, thirdPageData);
        }

    }

}
