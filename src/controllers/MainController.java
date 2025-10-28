package controllers;

import dao.UserDAO;
import java.sql.Connection;
import view.Frame;

public class MainController {
    private LoginController loginController;

    public MainController(Connection conn, Frame frame){
        this.loginController = new LoginController(new UserDAO(conn), frame);
        frame.showPanel(Frame.LOGIN_PANEL);
        loginController.initListeners();
    }
}
