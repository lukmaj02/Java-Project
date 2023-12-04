package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

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
    private ComboBox <String> cardTypeComboBox;
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

    public void initializeFormula(Integer number) {
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
        initializeComboBox();
    }
    public void initializeComboBox() {
        educationalComboBox.getItems().removeAll(educationalComboBox.getItems());
        educationalComboBox.getItems().addAll("Non-Graduate","Graduate","Post-Graduate","Doctrate","Others");

        categoryComboBox.getItems().removeAll(categoryComboBox.getItems());
        categoryComboBox.getItems().addAll("Savings", "Personal", "Buisness");

        occupationComboBox.getItems().removeAll(occupationComboBox.getItems());
        occupationComboBox.getItems().addAll("Salaried","Self-Employmed","Business","Student","Retired","Others");


        cardTypeComboBox.getItems().removeAll(cardTypeComboBox.getItems());
        cardTypeComboBox.getItems().addAll("Credit", "Debit");


        currencyTypeComboBox.getItems().removeAll(currencyTypeComboBox.getItems());
        currencyTypeComboBox.getItems().addAll("PLN", "Euro", "US dollar", "Ruble", "Norwegian krone");

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SecondFormulaPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == nextPageButton) {
            openThirdFormulaPage(actionEvent);
        } else if (actionEvent.getSource() == previousPageButton) {
            openFormula(actionEvent);
        } else if (actionEvent.getSource() == seniorCitizenYesRadioButton) {
            if (seniorCitizenNoRadioButton.isSelected())
                seniorCitizenNoRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == seniorCitizenNoRadioButton) {
            if (seniorCitizenYesRadioButton.isSelected())
                seniorCitizenYesRadioButton.setSelected(false);
        }
    }


}
