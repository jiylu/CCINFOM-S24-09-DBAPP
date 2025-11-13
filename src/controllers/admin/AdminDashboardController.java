package controllers.admin;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.ReportDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import models.User;
import view.Frame;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.ReportsDashboardPanel;
import view.admin.UserManagementPanel;

public class AdminDashboardController {
    private User user;
    private Frame frame;
    private AdminDashboardPanel panel;
    private AddUserPanel addUserPanel;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private ReportDAO reportDAO;
    private UserManagementController viewUsersController;
    private ReportsDashboardController reportsDashboardController;

    public AdminDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, ReportDAO reportDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getAdminDashboardPanel();
        this.addUserPanel = panel.getAddUserPanel(); 
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.reportDAO = reportDAO;
    }

    public void init(){
        UserManagementPanel viewUsersPanel = panel.getViewUsersPanel();
        ReportsDashboardPanel reportsDashboardPanel = panel.getReportsDashboardPanel();
        this.viewUsersController = new UserManagementController(user, viewUsersPanel, userDAO, empDAO, techDAO, deptDAO);
        this.reportsDashboardController = new ReportsDashboardController(reportDAO,reportsDashboardPanel);
        frame.showPanel(Frame.ADMIN_PANEL);
        initListeners();
    }

    
    private void initListeners(){
        viewUsersController.initListeners();
        reportsDashboardController.initListeners();

        panel.getViewUsersButton().addActionListener(e->{
            viewUsersController.init(panel);
        });
        
        panel.getReportsButton().addActionListener(e->{
            reportsDashboardController.init(panel);
        });
    }
}