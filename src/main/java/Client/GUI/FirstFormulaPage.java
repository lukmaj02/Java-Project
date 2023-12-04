package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FirstFormulaPage extends Application {
    @FXML
    private ComboBox<String> maritalStatusComboBox;
    @FXML
    private Button nextPageButton;
    @FXML
    private Button previousPageButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField fathersNameTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField stateTextField;
    @FXML
    private Label formulaLabel;

    public FrontPage frontPage = new FrontPage(); // to use function from this class


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FirstFormulaPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public void initializeFormula(Integer number) {
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
        initalizeComboBox();
    }

    public void initalizeComboBox() {
        maritalStatusComboBox.getItems().removeAll(maritalStatusComboBox.getItems());
        maritalStatusComboBox.getItems().addAll("Single", "Married", "Divorced", "Widowed");
    }

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == maleRadioButton) {
            if (femaleRadioButton.isSelected())
                femaleRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == femaleRadioButton) {
            if (maleRadioButton.isSelected())
                maleRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == maritalStatusComboBox) {
            System.out.println(maritalStatusComboBox.getValue());
            // to check if everything is okay.
        } else if (actionEvent.getSource() == nextPageButton) {
            frontPage.openSecondFormulaPage(actionEvent);
        } else if (actionEvent.getSource() == previousPageButton) {
            frontPage.returnToFrontPage(actionEvent);
        }
    }

}
