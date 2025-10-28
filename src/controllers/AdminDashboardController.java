package controllers;

import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import models.User;
import view.Frame;

public class AdminDashboardController {
    private User user;
    private Frame frame;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;

    public AdminDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO){
        this.user = user;
        this.frame = frame;
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
    }

    public void init(){
        frame.showPanel(Frame.ADMIN_PANEL);
    } 
}
