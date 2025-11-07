package controllers.admin;

import dao.*;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.UserManagementPanel;
import models.Employees;
import models.Technicians;
import models.User;
public class UserManagementController {
    private UserManagementPanel panel;    
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;

    private AddUserPanel addUserPanel;
    private AddUserController addUserController;

    public UserManagementController(UserManagementPanel panel, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO){
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
        initViewEmpByDepartment();
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
            panel.shift();
        });
    }

    private void initViewEmpByDepartment(){
        panel.getViewByDepartment().addActionListener(e->{
            JComboBox<String> comboBox = new JComboBox<>(deptDAO.getAllDepartmentNames(false).toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Department", JOptionPane.OK_CANCEL_OPTION);
            
            if (res == JOptionPane.OK_OPTION){
                String selectedDept = (String) comboBox.getSelectedItem();
                int deptID = deptDAO.getDepartmentIDByName(selectedDept);
                List<Employees> empList = empDAO.getEmployeesByDepartment(deptID);
                List<User> userList = userDAO.getAllUsers();
                panel.setupEmployeesTable(empList, userList); 
            }
        });
    }
    
    private void initViewTechnicians(){
        panel.getViewTechnicians().addActionListener(e->{
            List<Technicians> techList = techDAO.getAllTechnicians();
            List<User> userList = userDAO.getAllUsers();
            panel.setupTechniciansTable(techList, userList);
            panel.revert();
        });
    }
}
