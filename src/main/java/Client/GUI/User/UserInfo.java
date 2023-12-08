package Client.GUI.User;

import Client.Client;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class UserInfo extends Client {

    @FXML
    public Label firstnameLabel;
    @FXML
    public Label lastnameLabel;
    @FXML
    public Label addressLabel;
    @FXML
    public Label cityLabel;
    @FXML
    public Label phonenumberLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label maritalStatusLabel;
    @FXML
    public Button back;
    @FXML
    public Button changeInformations;
    @FXML
    public TextArea firstnameText;
    @FXML
    public TextArea lastnameText;
    @FXML
    public TextArea emailText;
    @FXML
    public TextArea addressText;
    @FXML
    public TextArea cityText;
    @FXML
    public TextArea phonenumberText;
    @FXML
    public TextArea maritalStatusText;

    private UserDto user;

    public void initialize(UserDto user){
        this.user = user;
        firstnameText.setText(user.getFirstname());
        lastnameText.setText(user.getLastname());
        emailText.setText(user.getEmail());
        addressText.setText(user.getAddress());
        cityText.setText(user.getCity());
        phonenumberText.setText(user.getPhoneNumber());
        maritalStatusText.setText(user.getMaritalStatus());
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == back){
            openUserPage(actionEvent, user);
        }
    }
}
