package project_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class Login extends Hospital {
	
    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
	
    // Login screen method
    void initializeLogin() {
    	
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 25);
        loginPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 20, 200, 25);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 80, 25);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 60, 200, 25);
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 100, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateLogin();
            }
        });
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    // Login validation method
    private void validateLogin() {
        String enteredUsername = usernameField.getText();
        char[] enteredPassword = passwordField.getPassword();
        char[] correctPassword = "1".toCharArray(); // Default password

        if (enteredUsername.equals("1") && Arrays.equals(enteredPassword, correctPassword)) {
            loginFrame.dispose();
            // Check to see if an object of the Hospital class already exists. If one is loaded from the data, take the user to the landing page instead
            Hospital hospital = Hospital.getCurrentHospital();
            if (hospital == null)
            	initialize();
            else
            	new LandingPage().initializeLandingPage();	
        } 
        else {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }

        // Clear username and password fields if login fails
        usernameField.setText("");
        passwordField.setText("");
    }
}
