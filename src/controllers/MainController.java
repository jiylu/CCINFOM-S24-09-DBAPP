package controllers;

import view.Frame;

public class MainController {
    private LoginController loginController;

    public MainController(Frame frame){
        this.loginController = new LoginController(frame);
    }
}
