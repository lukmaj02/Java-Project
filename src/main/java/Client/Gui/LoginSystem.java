package Client.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSystem extends MainFrame implements ActionListener {

    /* global variables of elements on screen*/
    JButton loginButton, clearInputButton, signUpButton;
    JTextField emailField;
    JPasswordField passwordField;

    public LoginSystem() {
        super(500,500);
        // Set the background color of the main content panel
        getContentPane().setBackground(Color.WHITE);

        // Create and configure the welcome label
        JPanel welcomePanel = getWelcomePanel();

        // Create and configure the info panel
        JPanel infoPanel = getInfoPanel();
        infoPanel.setBackground(Color.WHITE);

        // Create and configure the button panel
        JPanel buttonPanel = getButtonPanel();
        buttonPanel.setBackground(Color.WHITE);

        // Create and configure the sign-up button
        JPanel signUpPanel = getSignUpPanel();
        signUpPanel.setBackground(Color.WHITE);

        // Add components to the frame
        add(welcomePanel);
        add(infoPanel);
        add(buttonPanel);
        add(signUpPanel);
    }

    private JPanel getSignUpPanel() {
        JPanel signUpPanel = new JPanel(new GridLayout(1, 1));
        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.addActionListener(this);
        signUpPanel.add(signUpButton);
        return signUpPanel;
    }

    private static JPanel getWelcomePanel() {
        JLabel welcomeLabel = new JLabel("Welcome to ATM Management System");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
        JPanel welcomePanel = new JPanel(new GridLayout(1, 1));
        welcomePanel.add(welcomeLabel);
        welcomePanel.setBackground(Color.WHITE);
        return welcomePanel;
    }

    private JPanel getButtonPanel() {
        // TODO change the layout of the button panel, make them smaller, or change approach to setBounds method
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        loginButton = new JButton("Sign In");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        clearInputButton = new JButton("Clear");
        clearInputButton.setBackground(Color.BLACK);
        clearInputButton.setForeground(Color.WHITE);
        clearInputButton.addActionListener(this);
        buttonPanel.add(clearInputButton);
        return buttonPanel;
    }

    private JPanel getInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel cardNumberLabel = new JLabel("Email");
        cardNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        infoPanel.add(cardNumberLabel);

        emailField = new JTextField();
        infoPanel.add(emailField);

        JLabel cardNumberPin = new JLabel("Password");
        cardNumberPin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        infoPanel.add(cardNumberPin);

        passwordField = new JPasswordField();
        infoPanel.add(passwordField);
        return infoPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSystem());
    }

    /**
     * Invoked when an action occurs.
     *
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == loginButton) {
            System.out.println("Login button clicked");
        } else if (actionEvent.getSource() == clearInputButton) {
            emailField.setText("");
            passwordField.setText("");
        } else if (actionEvent.getSource() == signUpButton) {
            System.out.println("Sign up button clicked");
        }
    }
}
