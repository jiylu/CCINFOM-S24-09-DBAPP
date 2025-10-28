package controllers;

import dao.UserDAO;
import javax.swing.*;
import models.User;
import view.Frame;
import view.LoginPanel;

public class LoginController {
    private UserDAO userDAO;
    private LoginPanel panel;
    private JButton loginButton;

    public LoginController(UserDAO userDAO, Frame frame){
        this.userDAO = userDAO;
        this.panel = frame.getLoginPanel();
        this.loginButton = panel.getLoginButton();

    }

    public void initListeners(){
        loginButton.addActionListener(e->{
            User u = loginUser();
            if (u != null){
                JOptionPane.showMessageDialog(null, "Hellow");
                switch (u.getRole()){
                    case User.Role.ADMIN -> System.out.println("Admin"); // frame.showPanel(Frame.ADMIN_DASHBOARD)
                    case User.Role.EMPLOYEE -> System.out.println("Employee"); // frame.showPanel(Frame.EMP_DASHBOARD)
                    case User.Role.TECHNICIAN -> System.out.println("Technician"); // frame.showPanel(Frame.TECH_DASHBOARD)
                }
            }
        });
    }

    private User loginUser(){
        String usernameInput = panel.getUsernameField().getText().trim();
        String passwordInput = new String(panel.getPasswordField().getPassword()).trim();

        // validate first if username input or password input is not empty
        if (usernameInput.isEmpty()|| passwordInput.isEmpty()){
            JOptionPane.showMessageDialog(null, "Username or Password cannot be empty.", "Invalid", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        User user = userDAO.getUserByLogin(usernameInput, passwordInput);

        if (user == null){
            JOptionPane.showMessageDialog(null, "Username or Password is incorrect.", "Invalid", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return user;
    }
}
