package controllers;

import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import models.User;
import view.Frame;
import view.admin.AdminDashboardPanel;

public class AdminDashboardController {
    private User user;
    private Frame frame;
    private AdminDashboardPanel panel;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;

    public AdminDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getAdminDashboardPanel();
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
    }

    public void init(){
        frame.showPanel(Frame.ADMIN_PANEL);
        initListeners();
    }

    
    private void initListeners(){
        panel.getAddUserButton().addActionListener(e->{
            panel.showPanel(AdminDashboardPanel.ADD_USER);
        });
    }
}
