package controllers;

import java.sql.Connection;
import view.Frame;

public class MainController {
    private Connection conn;
    private LoginController loginController;
    private Frame frame;

    public MainController(Connection conn, Frame frame){
        this.loginController = new LoginController(frame);
        this.frame = frame;
        this.conn = conn;

    }

    public void init(){
        frame.showPanel(Frame.LOGIN_PANEL);
        loginController.init(conn);
    }
}
