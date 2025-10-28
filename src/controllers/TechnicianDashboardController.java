package controllers;

import dao.TechniciansDAO;
import dao.UserDAO;
import models.User;
import view.Frame;

public class TechnicianDashboardController {
    private User user;
    private Frame frame;
    private UserDAO userDAO;
    private TechniciansDAO techniciansDAO;

    public TechnicianDashboardController(User user, Frame frame, UserDAO userDAO, TechniciansDAO techniciansDAO){
        this.user = user;
        this.frame = frame;
        this.userDAO = userDAO;
        this.techniciansDAO = techniciansDAO;
    }

    public void init(){
        frame.showPanel(Frame.TECHNICIAN_PANEL);
    }
}
