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
            User u = getUserByLogin();
            if (validateUser(u)){
                // wala pa chill ka muna
                JOptionPane.showMessageDialog(null, "Hellow");
            }
        });
    }

    private User getUserByLogin(){
        String usernameInput =  panel.getUsernameField().getText().trim();
        String passwordInput = new String(panel.getPasswordField().getPassword()).trim();

        // validate first if username input or password input is not empty
        if (usernameInput.isEmpty()|| passwordInput.isEmpty()){
            JOptionPane.showMessageDialog(null, "Username or Password cannot be empty.", "Invalid", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return userDAO.getUserByLogin(usernameInput, passwordInput);
    }

    private boolean validateUser(User user){
        if (user == null){
            JOptionPane.showMessageDialog(null, "Username or Password is incorrect.", "Invalid", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
