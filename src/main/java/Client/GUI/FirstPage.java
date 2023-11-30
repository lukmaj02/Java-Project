package Client.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class FirstPage extends Application {
    @FXML
    private Button button1;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FirstPage.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void test(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button1) {
            System.out.println("Hello world!");
        }
    }
}
