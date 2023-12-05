package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private ArrayList<String > firstPageData = new ArrayList<>();
    private ArrayList<String > secondPageData = new ArrayList<>();
    private  ArrayList<String > thirdPageData = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ThirdFormulaPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public void initializeFormula(Integer number, ArrayList<String> firstFormulaPageData, ArrayList<String> secondFormulaPageData, ArrayList<String> thirdFormulaPageData) {
        formulaLabel.setText("Formula Page no. " + Integer.toString(number));
        firstPageData = firstFormulaPageData;
        secondPageData = secondFormulaPageData;
        initializeData(thirdFormulaPageData);
    }

    private void initializeData(ArrayList<String> thirdPageFormulaDate) {
        if (thirdPageFormulaDate.isEmpty())
            return;

        emailTextField.setText(thirdPageFormulaDate.get(0));
        areaCodeTextField.setText(thirdPageFormulaDate.get(1));
        phoneNumberTextField.setText(thirdPageFormulaDate.get(2));
        // the rest of the variables user should provide again
    }

    private void fillThirdPageVariables() {
        thirdPageData.clear();

        thirdPageData.addAll(Arrays.asList
                (
                    emailTextField.getText(), areaCodeTextField.getText(), phoneNumberTextField.getText()
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
        }
    }

}
