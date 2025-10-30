package controllers;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import java.sql.Connection;
import javax.swing.*;
import models.User;
import view.Frame;
import view.LoginPanel;

public class LoginController {
    private Frame frame;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private LoginPanel panel;
    private JButton loginButton;

    public LoginController(Frame frame){
        this.frame = frame;
        this.panel = frame.getLoginPanel();
        this.loginButton = panel.getLoginButton();
    }

    public void init(Connection conn){
        initListener();
        initializeDAO(conn);
        
        frame.showPanel(Frame.LOGIN_PANEL);
    }

    private void initializeDAO(Connection conn){
        this.userDAO = new UserDAO(conn);
        this.empDAO = new EmployeesDAO(conn);
        this.techDAO = new TechniciansDAO(conn);
        this.deptDAO = new DepartmentDAO(conn);

    }

    private void initListener(){
        loginButton.addActionListener(e->{
            User u = loginUser();
            if (u != null){
                JOptionPane.showMessageDialog(null, "Hellow");
                switch (u.getRole()){
                    case User.Role.ADMIN -> redirectToAdminDashboard(u); 
                    case User.Role.EMPLOYEE -> redirectToEmployeeDashboard(u);
                    case User.Role.TECHNICIAN -> redirectToTechDashboard(u);
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

    private void redirectToEmployeeDashboard(User user){
        EmployeeDashboardController empDashboardController = new EmployeeDashboardController(user, frame, userDAO, empDAO);
        empDashboardController.init();
    }

    private void redirectToAdminDashboard(User user){
        AdminDashboardController adminDashboardController = new AdminDashboardController(user, frame, userDAO, empDAO, techDAO, deptDAO);
        adminDashboardController.init(); 
    }

    private void redirectToTechDashboard(User user){
        TechnicianDashboardController technicianDashboardController = new TechnicianDashboardController(user, frame, userDAO, techDAO);
        technicianDashboardController.init();
    }
}
