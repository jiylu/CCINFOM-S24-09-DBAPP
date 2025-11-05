package controllers.admin;

import dao.*;
import java.util.List;

import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.ViewUsersPanel;
import models.Employees;
import models.Technicians;
import models.User;
public class ViewUsersController {
    private ViewUsersPanel panel;    
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;

    private AddUserPanel addUserPanel;
    private AddUserController addUserController;

    public ViewUsersController(ViewUsersPanel panel, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO){
        this.panel = panel;
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.addUserController = new AddUserController(userDAO, empDAO, techDAO, deptDAO);
    }

    public void init(AdminDashboardPanel adminPanel){
        adminPanel.showPanel(AdminDashboardPanel.VIEW_USERS);
    }

    public void initListeners(){
        addUserController.initListeners();
        initAddUsers();
        initViewEmployees();
        initViewTechnicians();
    }

    private void initAddUsers(){
        panel.getAddUser().addActionListener(e->{
            addUserController.init();
        });
    }

    private void initViewEmployees(){
        panel.getViewEmployees().addActionListener(e->{
            List<Employees> empList = empDAO.getAllEmployees();
            List<User> userList = userDAO.getAllUsers();
            panel.setupEmployeesTable(empList, userList); 
        });
    }

    private void initViewTechnicians(){
        panel.getViewTechnicians().addActionListener(e->{
            List<Technicians> techList = techDAO.getAllTechnicians();
            List<User> userList = userDAO.getAllUsers();
            panel.setupTechniciansTable(techList, userList);
        });
    }
}
