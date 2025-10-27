package view;

import java.awt.Font;
import javax.swing.*;

public class Frame extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Frame(){
        super("IT Helpdesk Ticketing System");
        setupFrameComponents();
        setupLoginFields();
        setupButtons();
    }

    private void setupFrameComponents(){
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);
    }

    private void setupLoginFields(){
        // title
        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 120, 800, 80); 
        add(title);

        // username : [input]
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setBounds(270, 250, 120, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(390, 250, 180, 30);
        add(usernameField);

        // pasword: [input]
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordLabel.setBounds(270,300, 120, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(390, 300, 180, 30);
        add(passwordField);
    }

    private void setupButtons(){
        loginButton = new JButton("Login");
        loginButton.setBounds(350, 370, 100, 40);
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
