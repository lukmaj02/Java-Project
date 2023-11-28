package Client.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MainFrame extends JFrame {
    MainFrame(int DimensionWidth, int DimensionHeight) {
        super("ATM Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 20));

        // Set the background color of the main content panel
        getContentPane().setBackground(Color.WHITE);


        String filePath = ".txt";
        File file = new File(filePath);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("bank-icon.png"));

        setIconImage(icon.getImage());

        // Create and configure the image label
        JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(68, 68, Image.SCALE_DEFAULT)));
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));
        imagePanel.add(imageLabel);
        imagePanel.setBackground(Color.GRAY);

        add(imagePanel);

        setMinimumSize(new Dimension(DimensionWidth, DimensionHeight));
        int screenWidth = (int) getToolkit().getScreenSize().getWidth();
        int screenHeight = (int) getToolkit().getScreenSize().getHeight();
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater((Runnable) new MainFrame(850,850));
    }
}
