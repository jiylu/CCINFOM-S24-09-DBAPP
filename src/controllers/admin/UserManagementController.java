package controllers.admin;

import dao.*;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.Employees;
import models.Technicians;
import models.User;
import util.ButtonEditor;
import util.ButtonRenderer;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.UserManagementPanel;
public class UserManagementController {
    private User user;
    private UserManagementPanel panel;    
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;

    private AddUserPanel addUserPanel;
    private AddUserController addUserController;

    public UserManagementController(User user, UserManagementPanel panel, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO){
        this.user = user;
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

            loadEmployeesTable(empList, userList);
        });
    }

    private void loadEmployeesTable(List<Employees> empList, List<User> userList){
        panel.setupEmployeesTable(empList, userList); 
        JTable table = panel.getTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        table.getColumn("Deactivate").setCellRenderer(new ButtonRenderer("Deactivate")); 
        initEditEmp(table);
        initDeactivateEmployee(table);
        panel.shift();
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
    
    private void initEditEmp(JTable table){
        EditUserController euc = new EditUserController(userDAO, empDAO, techDAO, deptDAO);

        euc.setOnSaveCallback(() -> {
        List<Employees> empList = empDAO.getAllEmployees();
            List<User> userList = userDAO.getAllUsers();
            loadEmployeesTable(empList, userList);
        });

        euc.initListener(table);
    }

    private void initEditTech(JTable table){
        EditUserController euc = new EditUserController(userDAO, empDAO, techDAO, deptDAO);

        euc.setOnSaveCallback(() -> {
            List<Technicians> techList = techDAO.getAllTechnicians();
            List<User> userList = userDAO.getAllUsers();
            loadTechniciansTable(techList, userList);
        });

        euc.initListener(table);
    }

    private void initDeactivateEmployee(JTable table){
        table.getColumn("Deactivate").setCellEditor(new ButtonEditor(new JCheckBox(), "Deactivate", row -> {
            int userID = (int) table.getValueAt(row, 0); 
            Object status = table.getValueAt(row, 7); 
            
            // apra di mag self deactivate
            if (userID == user.getUserID()){
                JOptionPane.showMessageDialog(null, "You cannot deactivate yourself.");
                return;
            }
            
            if (status != null && status.toString().equals("Inactive")) {
                JOptionPane.showMessageDialog(null, "User is already inactive.");
                return;
            }


            int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to deactivate this user?","Confirm Deactivation",JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION){
                userDAO.deactivateUser(userID);
                JOptionPane.showMessageDialog(null, "Deactivated Emp ID " + userID);
                
                // refresh table
                List<Employees> empList = empDAO.getAllEmployees();
                List<User> userList = userDAO.getAllUsers();

                loadEmployeesTable(empList, userList);
            }
        }));
    }

    private void initViewTechnicians(){
        panel.getViewTechnicians().addActionListener(e->{
            List<Technicians> techList = techDAO.getAllTechnicians();
            List<User> userList = userDAO.getAllUsers();

            loadTechniciansTable(techList, userList);
        });
    }

    private void loadTechniciansTable(List<Technicians> techList, List<User> userList){
        panel.setupTechniciansTable(techList, userList); 

        JTable table = panel.getTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        table.getColumn("Deactivate").setCellRenderer(new ButtonRenderer("Deactivate")); 

        initDeactivateTechnician(table);
        initEditTech(table);
        panel.revert();
    }

    private void initDeactivateTechnician(JTable table){
        table.getColumn("Deactivate").setCellEditor(new ButtonEditor(new JCheckBox(), "Deactivate", row -> {
            int userID = (int) table.getValueAt(row, 0);
                        
            int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to deactivate this user?","Confirm Deactivation",JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION){
                userDAO.deactivateUser(userID);
                JOptionPane.showMessageDialog(null, "Deactivated User ID " + userID);
                
                // refresh table
                List<Technicians> techList = techDAO.getAllTechnicians();
                List<User> userList = userDAO.getAllUsers();

                loadTechniciansTable(techList, userList);
            }
        }));
    }
}