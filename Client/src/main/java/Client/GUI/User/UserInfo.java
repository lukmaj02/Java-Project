package Client.GUI.User;

import Client.Client;
import Client.dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    public TextField firstnameText;
    @FXML
    public TextField lastnameText;
    @FXML
    public TextField emailText;
    @FXML
    public TextField addressText;
    @FXML
    public TextField cityText;
    @FXML
    public TextField phonenumberText;
    public ChoiceBox<String> maritalStatus;

    private UserDto user;

    public void initialize(UserDto user){
        this.user = user;
        firstnameText.setText(user.getFirstname());
        lastnameText.setText(user.getLastname());
        emailText.setText(user.getEmail());
        addressText.setText(user.getAddress());
        cityText.setText(user.getCity());
        phonenumberText.setText(user.getPhoneNumber());
        maritalStatus.getItems().addAll("SINGLE", "MARRIED", "WIDOWED", "DIVORCED");
        maritalStatus.setValue(user.getMaritalStatus());
    }

    public void executeAnAction(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == back){
            openUserPage(actionEvent, user);
        } else if (actionEvent.getSource() == changeInformations && areFieldsEdited()) {
            var msg = sendToServerWithResponse(infoToEditUser());
            if(isResponseValid(msg)){
                updateUser();
                showInfo("EDITED", "User information's edited successfully!");
                openUserPage(actionEvent,user);
            }
        }
    }

    private void updateUser(){
        user.setAddress(addressText.getText());
        user.setCity(cityText.getText());
        user.setPhoneNumber(phonenumberText.getText());
        user.setMaritalStatus(maritalStatus.getValue());
    }
    private boolean areFieldsEdited(){
        if(!user.getAddress().equals(addressText.getText())) return true;
        else if(!user.getCity().equals(cityText.getText())) return true;
        else if(!user.getPhoneNumber().equals(phonenumberText.getText())) return true;
        else if(!user.getMaritalStatus().equals(maritalStatus.getValue())) return true;
        showWarning("Fields were not edited!");
        return false;
    }
    private String infoToEditUser(){
        return ("USER,EDIT," +
                user.getEmail() + "," +
                addressText.getText() + "," +
                cityText.getText() + "," +
                phonenumberText.getText() + "," +
                maritalStatus.getValue());
    }
}
