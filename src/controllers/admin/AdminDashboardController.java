package controllers.admin;

import dao.CategoriesDAO;
import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.ReportDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import models.User;
import view.Frame;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.DepartmentManagementPanel;
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
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;
    private UserManagementController viewUsersController;
    private DepartmentManagementController departmentManagementController;
    private ReportsDashboardController reportsDashboardController;

    public AdminDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO, ReportDAO reportDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getAdminDashboardPanel();
        this.addUserPanel = panel.getAddUserPanel(); 
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.ticketsDAO = ticketsDAO;
        this.reportDAO = reportDAO;
        this.categoriesDAO = categoriesDAO;
    }

    public void init(){
        UserManagementPanel viewUsersPanel = panel.getViewUsersPanel();
        ReportsDashboardPanel reportsDashboardPanel = panel.getReportsDashboardPanel();
        DepartmentManagementPanel departmentManagementPanel = panel.getDeptManagementPanel();
        this.viewUsersController = new UserManagementController(user, viewUsersPanel, userDAO, empDAO, techDAO, deptDAO, ticketsDAO);
        this.reportsDashboardController = new ReportsDashboardController(reportDAO, empDAO, techDAO, deptDAO, ticketsDAO, categoriesDAO, reportsDashboardPanel);
        this.departmentManagementController = new DepartmentManagementController(departmentManagementPanel, deptDAO);
        frame.showPanel(Frame.ADMIN_PANEL);
        initListeners();
    }

    
    private void initListeners(){
        viewUsersController.initListeners();
        reportsDashboardController.initListeners();
        departmentManagementController.initListener();
        
        panel.getViewUsersButton().addActionListener(e->{
            viewUsersController.init(panel);
        });
        
        panel.getManageDepartmentsButton().addActionListener(e->{
            departmentManagementController.init(panel);
        });

        panel.getReportsButton().addActionListener(e->{
            reportsDashboardController.init(panel);
        });
    }
}