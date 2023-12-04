package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstFormulaPage extends Application {
    @FXML
    public ComboBox<String> maritalStatusComboBox;
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
    private TextField emailTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField stateTextField;
    @FXML
    private TextField pinCodeTextField;
    @FXML
    private Label formulaLabel;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FirstFormulaPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeFormulaNumber(Integer number) {
        formulaLabel.setText("First Formula Page no. " + Integer.toString(number));
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
            //TODO change scene
            System.out.println("Next page button click.");
        } else if (actionEvent.getSource() == previousPageButton) {
            FrontPage frontPage = new FrontPage();
            frontPage.returnToFrontPage(actionEvent);
        }
    }

}
