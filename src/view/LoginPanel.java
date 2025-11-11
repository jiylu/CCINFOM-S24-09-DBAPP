package view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel(){
        setLayout(null);
        setupTitle();
        setupUsernameField();
        setupPasswordField();
        setupButtons();
    }

    private void setupTitle(){
        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 200, 800, 80);
        add(title);
    }

    private void setupUsernameField(){
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setBounds(460, 300, 120, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(580, 300, 180, 30);
        add(usernameField);
    }

    private void setupPasswordField(){
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setBounds(460,350, 120, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(580, 350, 180, 30);
        add(passwordField);
    }

    private void setupButtons(){
        loginButton = new JButton("Login");
        loginButton.setBounds(550, 420, 100, 40);
        add(loginButton);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
