package Client.Gui;

import javax.swing.*;
import java.awt.*;

public class SignupSystem extends MainFrame {

    SignupSystem() {
        super(800,850);
        setLayout(null);
        addApplicationFormLabel(170);
        addTextField("First Name: ", 250, 250);
        addTextField("Last Name: ", 300, 300);
        addDateOfBirthChooser(350);
        addGenderRadioButtons(400);
        addMaritalStatusRadioButtons(450);
        addTextField("Address: ", 500, 500);
        addTextField("City: ", 550, 550);
        addTextField("Email: ", 600, 600);
        addTextField("Password: ", 650, 650);
        addTextField("Repeat Password: ", 700, 700);
        addNextButton(750);
        centerFrameOnScreen();
    }

    private void addApplicationFormLabel(int labelY) {
        JLabel formulaNumberLabel = new JLabel("Sign Up");
        formulaNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
        formulaNumberLabel.setBounds(330,labelY , 600, 40);
        add(formulaNumberLabel);
    }

//    private void addPersonalDetailsLabel() {
//        JLabel personDetails = new JLabel("Page 1: Personal Details");
//        personDetails.setFont(new Font("Times New Roman", Font.BOLD, 22));
//        personDetails.setBounds(300, 80, 600, 30);
//        add(personDetails);
//    }

    private void addDateOfBirthChooser(int labelY) {
        JLabel dateOfBirth = new JLabel("Date of Birth: ");
        dateOfBirth.setFont(new Font("Times New Roman", Font.BOLD, 20));
        dateOfBirth.setBounds(100, labelY, 200, 30);
        add(dateOfBirth);
    }

    private void addGenderRadioButtons(int labelY) {
        addLabelAndRadioButtons("Gender: ", labelY, "Male", "Female");
    }

    private void addMaritalStatusRadioButtons(int labelY) {
        addLabelAndRadioButtons("Marital Status: ", labelY, "Married", "Unmarried", "Other");
    }

    private void addNextButton(int labelY) {
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBounds(600, labelY, 100, 30);
        add(nextButton);
    }

    private void addTextField(String labelText, int labelY, int fieldY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(100, labelY, 200, 30);
        add(label);

        JTextField textField = new JTextField();
        textField.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textField.setBounds(300, fieldY, 400, 30);
        add(textField);
    }

    private void addLabelAndRadioButtons(String labelText, int labelY, String... buttonLabels) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(100, labelY, 200, 30);
        add(label);

        ButtonGroup buttonGroup = new ButtonGroup();
        int x = 350;

        for (String buttonLabel : buttonLabels) {
            JRadioButton radioButton = new JRadioButton(buttonLabel);
            radioButton.setBounds(x, labelY, 120, 30);
            radioButton.setBackground(Color.WHITE);
            add(radioButton);
            buttonGroup.add(radioButton);
            x += 150;
        }
    }

    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignupSystem::new);
    }
}
