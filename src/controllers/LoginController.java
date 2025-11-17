package controllers;

import controllers.admin.AdminDashboardController;
import dao.*;
import java.sql.Connection;
import javax.swing.*;
import models.EmpUser;
import models.Employees;
import models.TechUser;
import models.Technicians;
import models.UserAccount;
import view.Frame;
import view.LoginPanel;

public class LoginController {
    private Frame frame;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private CategoriesDAO categoriesDAO;
    private TicketsDAO ticketsDAO;
    private ReportDAO reportDAO;
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
        this.categoriesDAO = new CategoriesDAO(conn);
        this.ticketsDAO = new TicketsDAO(conn);
        this.reportDAO = new ReportDAO(conn);
    }

    private void initListener(){
        loginButton.addActionListener(e->{
            UserAccount u = loginUser();
            if (u != null){
                System.out.println("Role returned: '" + u.getRole() + "'");
                switch (u.getRole()){
                    case UserAccount.ADMIN_ROLE -> redirectToAdminDashboard(u); 
                    case UserAccount.EMP_ROLE -> redirectToEmployeeDashboard(u);
                    case UserAccount.TECH_ROLE -> redirectToTechDashboard(u);
                }
            }
        });
    }

    private UserAccount loginUser(){
        String usernameInput = panel.getUsernameField().getText().trim();
        String passwordInput = new String(panel.getPasswordField().getPassword()).trim();

        if (usernameInput.isEmpty()|| passwordInput.isEmpty()){
            JOptionPane.showMessageDialog(null, "Username or Password cannot be empty.", "Invalid", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        UserAccount user = userDAO.getUserByLogin(usernameInput, passwordInput);

        if (user == null){
            JOptionPane.showMessageDialog(null, "Username or Password is incorrect.", "Invalid", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (user.getRole().contentEquals(UserAccount.EMP_ROLE) || user.getRole().contentEquals(UserAccount.ADMIN_ROLE)){
            Employees emp = empDAO.getEmployeeByUserId(user.getUserID());
            if (!emp.isActive()){
                JOptionPane.showMessageDialog(null, "User is deactivated.", "Invalid", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        if (user.getRole().contentEquals(UserAccount.TECH_ROLE)){
            Technicians tech = techDAO.getTechnicianByUserID(user.getUserID());
            if (!tech.isActive()){
                JOptionPane.showMessageDialog(null, "User is deactivated.", "Invalid", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        return user;
    }
    

    private void redirectToEmployeeDashboard(UserAccount u){
        EmpUser user = userDAO.getEmpUserByID(u.getUserID());
        EmployeeDashboardController empDashboardController = new EmployeeDashboardController(user, frame, userDAO, empDAO, techDAO, ticketsDAO, categoriesDAO);
        empDashboardController.init();
    }

    private void redirectToAdminDashboard(UserAccount u){
        EmpUser user = userDAO.getEmpUserByID(u.getUserID());
        AdminDashboardController adminDashboardController = new AdminDashboardController(user, frame, userDAO, empDAO, techDAO, deptDAO, ticketsDAO, reportDAO, categoriesDAO);
        adminDashboardController.init(); 
    }

    private void redirectToTechDashboard(UserAccount u){
        TechUser user = userDAO.getTechUserByID(u.getUserID());
        TechnicianDashboardController technicianDashboardController = new TechnicianDashboardController(user, frame, ticketsDAO, techDAO, categoriesDAO);
        technicianDashboardController.init();
    }
}
