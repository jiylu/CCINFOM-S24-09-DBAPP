package view;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private Image backgroundImage;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {

        backgroundImage = new ImageIcon(getClass().getResource("/view/login_bg.png")).getImage();

        setLayout(null);
        setOpaque(true);

        setupSystemTitle();
        setupFormBackground();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // System Title
    private void setupSystemTitle() {
        JLabel systemTitle = new JLabel("IT HelpDesk Ticketing System");
        systemTitle.setFont(new Font("Arial", Font.BOLD, 45));
        systemTitle.setHorizontalAlignment(SwingConstants.CENTER);
        systemTitle.setForeground(Color.WHITE);   // Visible on darkened bg
        systemTitle.setBounds(200, 80, 800, 60);
        add(systemTitle);
    }

    // Transparent Login Form
    private void setupFormBackground() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(350, 200, 500, 320);

        formPanel.setOpaque(false);

        add(formPanel);

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 30));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        loginTitle.setForeground(Color.WHITE);
        loginTitle.setBounds(0, 20, 500, 40);
        formPanel.add(loginTitle);

        JLabel uLabel = new JLabel("Username:");
        uLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        uLabel.setForeground(Color.WHITE);
        uLabel.setBounds(80, 90, 120, 30);
        formPanel.add(uLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 90, 200, 30);
        formPanel.add(usernameField);

        JLabel pLabel = new JLabel("Password:");
        pLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        pLabel.setForeground(Color.WHITE);
        pLabel.setBounds(80, 150, 120, 30);
        formPanel.add(pLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        formPanel.add(passwordField);

        // Green Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(190, 230, 120, 40);
        loginButton.setFocusPainted(false);

        Color green = new Color(76, 175, 80);
        Color greenHover = new Color(67, 160, 71);

        loginButton.setBackground(green);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginButton.setBorder(BorderFactory.createLineBorder(green, 2, true));

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

    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
}
