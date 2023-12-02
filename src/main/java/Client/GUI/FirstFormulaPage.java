package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirstFormulaPage extends Application {
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
    private RadioButton marriedRadioButton;
    @FXML
    private RadioButton unmarriedRadioButton;
    @FXML
    private RadioButton otherMaritalStatusRadioButton;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField stateTextField;
    @FXML
    private TextField pinCodeTextField;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FirstFormulaPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void executeAnAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == maleRadioButton) {
            if (femaleRadioButton.isSelected())
                femaleRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == femaleRadioButton) {
            if (maleRadioButton.isSelected())
                maleRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == marriedRadioButton) {
             if (unmarriedRadioButton.isSelected())
                 unmarriedRadioButton.setSelected(false);
             if (otherMaritalStatusRadioButton.isSelected())
                 otherMaritalStatusRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == unmarriedRadioButton){
            if (marriedRadioButton.isSelected())
                marriedRadioButton.setSelected(false);
            if (otherMaritalStatusRadioButton.isSelected())
                otherMaritalStatusRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == otherMaritalStatusRadioButton) {
            if (marriedRadioButton.isSelected())
                marriedRadioButton.setSelected(false);
            if (unmarriedRadioButton.isSelected())
                unmarriedRadioButton.setSelected(false);
        } else if (actionEvent.getSource() == nextPageButton) {
            //TODO change scene
            System.out.println("Next page button click.");
        } else if (actionEvent.getSource() == previousPageButton) {
            //TODO change Scene
            System.out.println("Previous page button click.");
        }
    }

}
