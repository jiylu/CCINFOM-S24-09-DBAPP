package controllers.admin;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import models.User;
import view.Frame;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.ViewUsersPanel;

public class AdminDashboardController {
    private User user;
    private Frame frame;
    private AdminDashboardPanel panel;
    private AddUserPanel addUserPanel;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;

    private ViewUsersController viewUsersController;

    public AdminDashboardController(User user, Frame frame, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO){
        this.user = user;
        this.frame = frame;
        this.panel = frame.getAdminDashboardPanel();
        this.addUserPanel = panel.getAddUserPanel(); 
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
    }

    public void init(){
        ViewUsersPanel viewUsersPanel = panel.getViewUsersPanel();

        this.viewUsersController = new ViewUsersController(viewUsersPanel, userDAO, empDAO, techDAO, deptDAO);
        
        frame.showPanel(Frame.ADMIN_PANEL);
        initListeners();
    }

    
    private void initListeners(){
        viewUsersController.initListeners();

        panel.getViewUsersButton().addActionListener(e->{
            viewUsersController.init(panel);
        });

        
    }
}