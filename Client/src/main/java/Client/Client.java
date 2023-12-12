package Client;

import Client.GUI.FrontPage;
import Client.GUI.RegistrationFormula.FirstFormulaPage;
import Client.GUI.RegistrationFormula.FourthFormulaPage;
import Client.GUI.RegistrationFormula.SecondFormulaPage;
import Client.GUI.RegistrationFormula.ThirdFormulaPage;
import Client.GUI.User.*;
import Client.GUI.User.Account.AccountCreationPage;
import Client.GUI.User.Account.UserAccountsPage;
import Client.GUI.User.Card.AccountCardsPage;
import Client.GUI.User.Card.CardCreationPage;
import Client.GUI.User.Credit.RequestCreditPage;
import Client.GUI.User.Credit.UserCreditsPage;
import Client.GUI.User.Deposit.DepositCreationPage;
import Client.GUI.User.Deposit.UserDepositsPage;
import Client.GUI.User.Transaction.TransactionCreatePage;
import Client.GUI.User.Transaction.UserTransactionsPage;
import Client.dto.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Client extends Application {
    protected Stage _stage;
    protected Scene _scene;
    protected Parent _root;

    //connection to server
    protected static PrintWriter sender;
    protected static BufferedReader reader;
    protected static Socket socket;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            socket = new Socket("localhost", 6000);
            sender = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            EncryptionManager.initFromString();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FrontPage.fxml"));

            _root = loader.load();
            _scene = new Scene(_root);
            stage.getIcons().add(new Image("bank-icon.png"));
            stage.setTitle("Virtual Banking System.");

            stage.setScene(_scene);
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException e){
            showWarning("Failed connection to server");
        }
        catch (Exception e) {
            showWarning("Decryption Manager Failed to launch");
        }
    }
    protected void openFrontPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontPage.fxml"));
        _root = loader.load();

        FrontPage frontPage = loader.getController();
        setStage(event);
    }
    protected void openFormula(ActionEvent event, ArrayList<String > firstFormulaPageData, ArrayList<String > secondFormulaPageData, ArrayList<String > thirdFormulaPageData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/FirstFormulaPage.fxml"));
        _root = loader.load();

        FirstFormulaPage formulaPage = loader.getController();
        formulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData); // change randomly|depending on users count
        setStage(event);
    }
    protected void openSecondFormulaPage(ActionEvent event, ArrayList<String> firstFormulaPageData, ArrayList<String > secondFormulaPageData, ArrayList<String > thirdFormulaPageData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/SecondFormulaPage.fxml"));
        _root = loader.load();

        SecondFormulaPage secondFormulaPage = loader.getController();
        secondFormulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData); // change randomly|depending on users count
        setStage(event);
    }
    protected void openThirdFormulaPage(ActionEvent event, ArrayList<String> firstFormulaPageData, ArrayList<String > secondFormulaPageData, ArrayList<String > thirdFormulaPageData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/ThirdFormulaPage.fxml"));
        _root = loader.load();

        ThirdFormulaPage thirdFormulaPage = loader.getController();
        thirdFormulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData); // change randomly|depending on users count
        setStage(event);
    }
    protected void openFourhtFormulaPage(ActionEvent event, ArrayList<String> firstFormulaPageData, ArrayList<String > secondFormulaPageData, ArrayList<String > thirdFormulaPageData) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Registration/FourthFormulaPage.fxml"));
        _root = loader.load();

        FourthFormulaPage fourthFormulaPage = loader.getController();
        fourthFormulaPage.initializeFormula(1, firstFormulaPageData, secondFormulaPageData, thirdFormulaPageData); // change randomly|depending on users count
        setStage(event);
    }
    protected void openUserPage(ActionEvent event, UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/UserPage.fxml"));
        _root = loader.load();

        UserPage userPage = loader.getController();
        userPage.initialize(user);
        setStage(event);
    }
    protected void openUserInfo(ActionEvent event, UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/UserInfo.fxml"));
        _root = loader.load();

        UserInfo userInfo = loader.getController();
        userInfo.initialize(user);
        setStage(event);
    }
    protected void openAccountCreationPage(ActionEvent event, UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BankAccount/AccountCreationPage.fxml"));
        _root = loader.load();

        AccountCreationPage accountCreationPage = loader.getController();
        accountCreationPage.initialize(user);
        setStage(event);
    }
    protected void openUserAccounts(ActionEvent event,
                                    Set<BankAccountDto>accounts,
                                    UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BankAccount/UserAccountsPage.fxml"));
        _root = loader.load();

        UserAccountsPage userAccountsPage = loader.getController();
        userAccountsPage.initialize(accounts,user);
        setStage(event);
    }
    protected void openUserTransactionPage(ActionEvent event,
                                           Set<TransactionDto> allT,
                                           Set<TransactionDto> receivedT,
                                           Set<TransactionDto> sentT,
                                           UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Transaction/UserTransactionsPage.fxml"));
        _root = loader.load();

        UserTransactionsPage UserTransactionsPage = loader.getController();
        UserTransactionsPage.initialize(allT, receivedT, sentT, user);
        setStage(event);
    }
    protected void openUserDepositPage(ActionEvent event, Set<DepositDto> deposits, UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Deposit/UserDepositsPage.fxml"));
        _root = loader.load();

        UserDepositsPage userDepositsPage = loader.getController();
        userDepositsPage.initialize(deposits, user);
        setStage(event);
    }
    protected void openUserCreditPage(ActionEvent event, Set<CreditDto> credits, UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Credit/UserCreditsPage.fxml"));
        _root = loader.load();

        UserCreditsPage userCreditsPage = loader.getController();
        userCreditsPage.initialize(credits, user);
        setStage(event);
    }
    protected void openRequestingCreditPage(ActionEvent event, UserDto user, String accountNr) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Credit/RequestCreditPage.fxml"));
        _root = loader.load();

        RequestCreditPage requestCreditPage = loader.getController();
        requestCreditPage.initialize(user, accountNr);
        setStage(event);
    }
    protected void openAccountCardsPage(ActionEvent event, Set<CardDto> accountCards, UserDto user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Card/AccountCardsPage.fxml"));
        _root = loader.load();

        AccountCardsPage accountCardsPage = loader.getController();
        accountCardsPage.initialize(accountCards, user);
        setStage(event);
    }

    protected void openDepositCreationPage(ActionEvent event, UserDto user, String accountNr) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Deposit/DepositCreationPage.fxml"));
        _root = loader.load();

        DepositCreationPage depositCreationPage = loader.getController();
        depositCreationPage.initialize(user, accountNr);
        setStage(event);
    }
    protected void openCardCreationPage(ActionEvent event, UserDto user, String accountNr) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Card/CardCreationPage.fxml"));
        _root = loader.load();

        CardCreationPage cardCreationPage = loader.getController();
        cardCreationPage.initialize(user, accountNr);
        setStage(event);
    }
    protected void openTransactionCreationPage(ActionEvent event, UserDto user, String accountNr, String currency) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Transaction/TransactionCreationPage.fxml"));
        _root = loader.load();

        TransactionCreatePage transactionCreatePage = loader.getController();
        transactionCreatePage.initialize(user, accountNr, currency);
        setStage(event);
    }
    protected String sendToServerWithResponse(String data) {
        try {
            sender.println(data);
            return reader.readLine();
        } catch (Exception e) {
            return "ERROR, SERVER FAILED TO READ! CHECK LATER AGAIN";
        }
    }
    protected void showProvideDataWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Data warning, you haven't fill all fields.");
        alert.setContentText("Provide more details!");
        alert.show();
    }
    protected void showWarning(String warningInfo){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("WARNING");
        alert.setContentText(warningInfo);
        alert.show();
    }
    protected void showInfo(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    protected boolean isResponseValid(String data){
        if(Objects.equals(data, "")) return true;
        var splitedData = data.split(",",2);
        if(splitedData[0].equals("ERROR")){
            showWarning(splitedData[1]);
            return false;
        }
        return true;
    }
    private void setStage(ActionEvent event){
        _stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        _scene = new Scene(_root);
        _stage.setScene(_scene);
        _stage.show();
        _stage.centerOnScreen();
    }
}

