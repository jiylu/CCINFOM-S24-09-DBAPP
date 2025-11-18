package view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        setLayout(null);

        // Green background
        setBackground(new Color(200, 245, 200)); // soft green

        setupSystemTitle();
        setupFormBackground();
    }

    // System Title
    private void setupSystemTitle() {
        JLabel systemTitle = new JLabel("IT HelpDesk Ticketing System");
        systemTitle.setFont(new Font("Arial", Font.BOLD, 45));
        systemTitle.setHorizontalAlignment(SwingConstants.CENTER);
        systemTitle.setBounds(200, 80, 800, 60);
        add(systemTitle);
    }

    private void setupFormBackground() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(350, 200, 500, 320);

        // White login box with slight transparency
        formPanel.setBackground(new Color(255, 255, 255, 220));
        formPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        add(formPanel);

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 30));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setBounds(0, 20, 500, 40);
        formPanel.add(loginTitle);

        JLabel uLabel = new JLabel("Username:");
        uLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        uLabel.setBounds(80, 90, 120, 30);
        formPanel.add(uLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 90, 200, 30);
        formPanel.add(usernameField);

        JLabel pLabel = new JLabel("Password:");
        pLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        pLabel.setBounds(80, 150, 120, 30);
        formPanel.add(pLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        formPanel.add(passwordField);

        // Green Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(190, 230, 120, 40);
        loginButton.setFocusPainted(false);

        Color green = new Color(76, 175, 80);      // default green
        Color greenHover = new Color(67, 160, 71); // darker on hover

        loginButton.setBackground(green);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Rounded edges with padding
        loginButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(green, 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // Hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(greenHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(green);
            }
        });

        formPanel.add(loginButton);
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