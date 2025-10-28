package controllers;

import java.sql.Connection;
import view.Frame;

public class MainController {
    private LoginController loginController;

    public MainController(Connection conn, Frame frame){
        this.loginController = new LoginController(frame);
        frame.showPanel(Frame.LOGIN_PANEL);
        loginController.init(conn);
    }
}
