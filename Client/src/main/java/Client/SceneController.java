package Client;

import Client.GUI.FrontPage;
import Client.GUI.RegistrationFormula.FirstFormulaPage;
import Client.GUI.RegistrationFormula.FourthFormulaPage;
import Client.GUI.RegistrationFormula.SecondFormulaPage;
import Client.GUI.RegistrationFormula.ThirdFormulaPage;
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
import Client.GUI.User.UserInfo;
import Client.GUI.User.UserPage;
import Client.dto.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class SceneController extends Client{
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
                                    Set<BankAccountDto> accounts,
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
}
