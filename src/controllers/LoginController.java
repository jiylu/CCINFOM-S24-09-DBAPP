package controllers;

import view.Frame;
import view.LoginPanel;

import javax.swing.*;

public class LoginController {
    private LoginPanel panel;
    private JButton loginButton;

    public LoginController(Frame frame){
        this.panel = frame.getLoginPanel();
        this.loginButton = panel.getLoginButton();

        frame.showPanel(Frame.LOGIN_PANEL);
    }
}
