package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController extends Application {

    protected Stage _stage;
    protected Scene _scene;
    protected Parent _root;


    protected void openFormula(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FirstFormulaPage.fxml"));
        _root = loader.load();

        FirstFormulaPage formulaPage = loader.getController();
        formulaPage.initializeFormula(1); // change randomly|depending on users count

        _stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setScene(_scene);
        _stage.show();
        _stage.centerOnScreen();
    }

    protected void openFrontPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontPage.fxml"));
        _root = loader.load();

        FrontPage frontPage = loader.getController();

        _stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setScene(_scene);
        _stage.show();
        _stage.centerOnScreen();
    }

    protected void openSecondFormulaPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SecondFormulaPage.fxml"));
        _root = loader.load();

        SecondFormulaPage secondFormulaPage = loader.getController();
        secondFormulaPage.initializeFormula(1); // change randomly|depending on users count

        _stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setScene(_scene);
        _stage.show();
        _stage.centerOnScreen();
    }

    protected void openThirdFormulaPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ThirdFormulaPage.fxml"));
        _root = loader.load();

        ThirdFormulaPage thirdFormulaPage = loader.getController();
        thirdFormulaPage.initializeFormula(1); // change randomly|depending on users count

        _stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setScene(_scene);
        _stage.show();
        _stage.centerOnScreen();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FrontPage.fxml"));

        _root = loader.load();
        _scene = new Scene(_root);

        stage.getIcons().add(new Image("bank-icon.png"));
        stage.setTitle("Virutal Banking System.");

        stage.setScene(_scene);
        stage.setResizable(false);
        stage.show();
    }
}

