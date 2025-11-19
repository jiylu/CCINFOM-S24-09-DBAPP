package controllers.admin;

import dao.CategoriesDAO;
import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.ReportDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import dao.UserDAO;
import dao.UserTableDAO;
import models.EmpUser;
import models.Employees;
import view.Frame;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.DepartmentManagementPanel;
import view.admin.ManageCategoriesPanel;
import view.admin.ReportsDashboardPanel;
import view.admin.UserManagementPanel;

public class AdminDashboardController {
    private EmpUser user;
    private Frame frame;
    private AdminDashboardPanel panel;
    private AddUserPanel addUserPanel;
    private UserTableDAO userTableDAO;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private ReportDAO reportDAO;
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;
    private UserManagementController viewUsersController;
    private DepartmentManagementController departmentManagementController;
    private ManageCategoriesController manageCategoriesController;
    private ReportsDashboardController reportsDashboardController;

    public AdminDashboardController(EmpUser user, Frame frame, UserTableDAO userTableDAO, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO, ReportDAO reportDAO, CategoriesDAO categoriesDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getAdminDashboardPanel();
        this.addUserPanel = panel.getAddUserPanel(); 
        this.userTableDAO = userTableDAO;
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
        ManageCategoriesPanel categoriesPanel = panel.getManageCategoriesPanel();
        ReportsDashboardPanel reportsDashboardPanel = panel.getReportsDashboardPanel();
        DepartmentManagementPanel departmentManagementPanel = panel.getDeptManagementPanel();
        this.viewUsersController = new UserManagementController(user, viewUsersPanel, userTableDAO, userDAO, empDAO, techDAO, deptDAO, ticketsDAO);
        this.manageCategoriesController = new ManageCategoriesController(categoriesPanel, categoriesDAO, ticketsDAO);
        this.reportsDashboardController = new ReportsDashboardController(reportDAO, empDAO, techDAO, deptDAO, ticketsDAO, categoriesDAO, reportsDashboardPanel);
        this.departmentManagementController = new DepartmentManagementController(departmentManagementPanel, deptDAO, empDAO);
        frame.showPanel(Frame.ADMIN_PANEL);

        try {
            Employees adminEmp = empDAO.getEmployeeByUserId(user.getUserID());
            if (adminEmp != null) {
                panel.setAdminName(adminEmp.getFirstName(), adminEmp.getLastName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        initListeners();
    }

    
    private void initListeners(){
        viewUsersController.initListeners();
        manageCategoriesController.initListeners();
        reportsDashboardController.initListeners();
        departmentManagementController.initListener();
        
        panel.getViewUsersButton().addActionListener(e->{
            viewUsersController.init(panel);
        });

        panel.getManageCategoriesButton().addActionListener(e->{
            manageCategoriesController.init(panel);
        });
        
        panel.getManageDepartmentsButton().addActionListener(e->{
            departmentManagementController.init(panel);
        });

        panel.getReportsButton().addActionListener(e->{
            reportsDashboardController.init(panel);
        });

        panel.getLogoutButton().addActionListener(e->{
            frame.getLoginPanel().getUsernameField().setText("");
            frame.getLoginPanel().getPasswordField().setText("");
            frame.showPanel(Frame.LOGIN_PANEL);
            this.user = null;
        });
    }
}