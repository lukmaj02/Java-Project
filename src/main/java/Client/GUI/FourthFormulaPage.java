package Client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class FourthFormulaPage extends SceneController {
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

    public void executeAnAction(ActionEvent actionEvent) throws Exception {
        if (actionEvent.getSource() == checkBoxTermsAndCondition) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ&embeds_referring_euri=https%3A%2F%2Fwww.bing.com%2F&embeds_referring_origin=https%3A%2F%2Fwww.bing.com&source_ve_path=MjM4NTE&feature=emb_title"));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
