package controllers;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import models.User;
import view.Frame;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;

public class AdminDashboardController {
    private User user;
    private Frame frame;
    private AdminDashboardPanel panel;
    private AddUserPanel addUserPanel;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;

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
        frame.showPanel(Frame.ADMIN_PANEL);
        initListeners();
    }

    
    private void initListeners(){
        addUserDropBoxFunctionality();

        panel.getAddUserButton().addActionListener(e->{
            panel.showPanel(AdminDashboardPanel.ADD_USER);
        });
    }

    private void addUserDropBoxFunctionality(){
        addUserPanel.getRoles().addActionListener(e->{
            String selectedRole = (String) addUserPanel.getRoles().getSelectedItem();
            String[] departmentList = deptDAO.getAllDepartmentNames().toArray(new String[0]);
            
            if (selectedRole.contentEquals("Employee")){
                addUserPanel.transformToEmployeeFields(departmentList);
            } else {
                addUserPanel.revert();
            }

        });
    }
}
