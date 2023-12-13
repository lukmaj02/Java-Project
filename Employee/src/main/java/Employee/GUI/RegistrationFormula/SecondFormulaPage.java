package Employee.GUI.RegistrationFormula;

import Employee.SceneController;
import Employee.dto.EmployeeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SecondFormulaPage extends SceneController {

    @FXML
    private Label formulaLabel;
    @FXML
    private ComboBox <String> educationalComboBox;
    @FXML
    private ComboBox <String> categoryComboBox;
    @FXML
    private ComboBox <String> occupationComboBox;

    @FXML
    private ComboBox <String> currencyTypeComboBox;
    @FXML
    private RadioButton seniorCitizenYesRadioButton;
    @FXML
    private RadioButton seniorCitizenNoRadioButton;
    @FXML
    private Button previousPageButton;
    @FXML
    private Button nextPageButton;

    public ArrayList<String> firstPageData = new ArrayList<>();
    public ArrayList<String > secondPageData = new ArrayList<>();
    public ArrayList<String > thirdPageData = new ArrayList<>();
    private EmployeeDto employee;

    public void initializeFormula(Integer number, ArrayList<String> firstFormulaPageData, ArrayList<String> secondFormulaPageData, ArrayList<String> thirdFormulaPageData, EmployeeDto employee) {
        this.employee = employee;
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
        firstPageData = firstFormulaPageData;
        thirdPageData = thirdFormulaPageData;
        initializeComboBox();
        initalizeData(secondFormulaPageData);
    }

    public void initializeComboBox() {
        educationalComboBox.getItems().removeAll(educationalComboBox.getItems());
        educationalComboBox.getItems().addAll("Non-Graduate","Graduate","Post-Graduate","Doctrate","Others");

        categoryComboBox.getItems().removeAll(categoryComboBox.getItems());
        categoryComboBox.getItems().addAll("Savings", "Personal", "Buisness");

        occupationComboBox.getItems().removeAll(occupationComboBox.getItems());
        occupationComboBox.getItems().addAll("Salaried","Self-Employmed","Business","Student","Retired","Others");

        currencyTypeComboBox.getItems().removeAll(currencyTypeComboBox.getItems());
        currencyTypeComboBox.getItems().addAll("PLN", "Euro", "US dollar", "Ruble", "Norwegian krone");
    }

    private void fillSecondPageVariables() {
        secondPageData.clear();

        String categoryAsString = "";
        if(!categoryComboBox.getSelectionModel().isEmpty())
            categoryAsString = categoryComboBox.getValue();

        String educationalQualificationAsString = "";
        if(!educationalComboBox.getSelectionModel().isEmpty())
            educationalQualificationAsString = educationalComboBox.getValue();

        String occupationAsString = "";
        if(!occupationComboBox.getSelectionModel().isEmpty())
            occupationAsString = occupationComboBox.getValue();

        String currencyTypeAsString = "";
        if(!currencyTypeComboBox.getSelectionModel().isEmpty())
            currencyTypeAsString = currencyTypeComboBox.getValue();

        String seniorCitizenAsString = "";
        if (seniorCitizenYesRadioButton.isSelected() || seniorCitizenNoRadioButton.isSelected())
            seniorCitizenAsString = seniorCitizenYesRadioButton.isSelected() ? "Yes": "No";



        secondPageData.addAll(Arrays.asList(
                categoryAsString,
                educationalQualificationAsString,
                occupationAsString,
                currencyTypeAsString,
                seniorCitizenAsString));
    }

    private boolean checkIfUserProvideAllData() {
        boolean ifSomethingEmpty = false;

        if (categoryComboBox.getSelectionModel().isEmpty())
            ifSomethingEmpty = true;
        if (educationalComboBox.getSelectionModel().isEmpty())
            ifSomethingEmpty = true;
        if (occupationComboBox.getSelectionModel().isEmpty())
            ifSomethingEmpty = true;
        if (currencyTypeComboBox.getSelectionModel().isEmpty())
            ifSomethingEmpty = true;
        if (!seniorCitizenNoRadioButton.isSelected() && !seniorCitizenYesRadioButton.isSelected())
            ifSomethingEmpty = true;

        return !ifSomethingEmpty;
    }

    private void initalizeData(ArrayList<String> secondPageFormulaDate) {
        if (secondPageFormulaDate.isEmpty())
            return;
        if (! Objects.equals(secondPageFormulaDate.get(0), ""))
            categoryComboBox.setValue(secondPageFormulaDate.get(0));
        if (! Objects.equals(secondPageFormulaDate.get(1), ""))
            educationalComboBox.setValue(secondPageFormulaDate.get(1));
        if (! Objects.equals(secondPageFormulaDate.get(2), ""))
            occupationComboBox.setValue(secondPageFormulaDate.get(2));
        if (! Objects.equals(secondPageFormulaDate.get(3), ""))
            currencyTypeComboBox.setValue(secondPageFormulaDate.get(3));
        if (Objects.equals(secondPageFormulaDate.get(4), "Yes"))
            seniorCitizenYesRadioButton.setSelected(true);
        else if (Objects.equals(secondPageFormulaDate.get(5), "No"))
            seniorCitizenNoRadioButton.setSelected(true);
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == nextPageButton) {
            fillSecondPageVariables();
            if (!checkIfUserProvideAllData()) {
                showProvideDataWarning();
                return;
            }

            openThirdFormulaPage(actionEvent, firstPageData, secondPageData, thirdPageData,employee);
        } else if (actionEvent.getSource() == previousPageButton) {
            fillSecondPageVariables();

            FirstFormulaPage firstFormulaPage = new FirstFormulaPage();
            firstFormulaPage.firstPageData = firstPageData;
            firstFormulaPage.secondPageData = secondPageData;
            firstFormulaPage.thirdPageData = thirdPageData;

            openFormula(actionEvent, firstPageData, secondPageData, thirdPageData,employee);
        } else if (actionEvent.getSource() == seniorCitizenYesRadioButton) {
            if (seniorCitizenNoRadioButton.isSelected())
                seniorCitizenNoRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == seniorCitizenNoRadioButton) {
            if (seniorCitizenYesRadioButton.isSelected())
                seniorCitizenYesRadioButton.setSelected(false);
        }
    }


}
