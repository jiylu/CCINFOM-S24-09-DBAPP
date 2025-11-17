package controllers.admin;

import dao.*;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.EmpUser;
import models.Employees;
import models.TechUser;
import models.Technicians;
import models.UserAccount;
import util.ButtonEditor;
import util.ButtonRenderer;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.UserManagementPanel;
public class UserManagementController {
    private EmpUser user;
    private UserManagementPanel panel;    
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private TicketsDAO ticketsDAO;

    private AddUserPanel addUserPanel;
    private AddUserController addUserController;

    public UserManagementController(EmpUser user, UserManagementPanel panel, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO){
        this.user = user;
        this.panel = panel;
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.ticketsDAO = ticketsDAO;
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
            List<UserAccount> userList = userDAO.getAllUserAccounts();

            loadEmployeesTable(empList, userList);
        });
    }

    private void loadEmployeesTable(List<Employees> empList, List<UserAccount> userList){
        List<EmpUser> empUsers = userDAO.getAllEmpUsers();
        panel.setupEmployeesTable(empList, userList, empUsers); 
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
                List<UserAccount> userList = userDAO.getAllUserAccounts();
                List<EmpUser> empUsers = userDAO.getAllEmpUsers();
                panel.setupEmployeesTable(empList, userList, empUsers); 
            }
        });
    }
    
    private void initEditEmp(JTable table){
        EditUserController euc = new EditUserController(userDAO, empDAO, techDAO, deptDAO);

        euc.setOnSaveCallback(() -> {
        List<Employees> empList = empDAO.getAllEmployees();
            List<UserAccount> userList = userDAO.getAllUserAccounts();
            loadEmployeesTable(empList, userList);
        });

        euc.initListener(table);
    }

    private void initEditTech(JTable table){
        EditUserController euc = new EditUserController(userDAO, empDAO, techDAO, deptDAO);

        euc.setOnSaveCallback(() -> {
            List<Technicians> techList = techDAO.getAllTechnicians();
            List<UserAccount> userList = userDAO.getAllUserAccounts();
            loadTechniciansTable(techList, userList);
        });

        euc.initListener(table);
    }

    private void initDeactivateEmployee(JTable table){
        table.getColumn("Deactivate").setCellEditor(new ButtonEditor(new JCheckBox(), "Deactivate", row -> {
            String status = table.getValueAt(row, 7).toString().trim();
            
            if (status.contentEquals("Inactive")){
                JOptionPane.showMessageDialog(null, "User is already deactivated.");
                return;
            }
            
            int userID = (int) table.getValueAt(row, 0);
            int empID = (int) table.getValueAt(row, 1);

            if (userID == user.getUserID()){
                JOptionPane.showMessageDialog(null, "Cannot deactivate yourself.");
                return;
            }

            if (ticketsDAO.hasActiveOrEnqueuedTickets(empID)) {
                JOptionPane.showMessageDialog(null,
                        "This Employee still has Active or Enqueued Tickets.", "Cannot Deactivate Employee",
                                JOptionPane.WARNING_MESSAGE);
                return;
            }
                        
            int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to deactivate this user?",
                    "Confirm Deactivation",JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION){
                empDAO.deactivateEmployee(empID);
                JOptionPane.showMessageDialog(null, "Deactivated User ID " + userID);
                
                // refresh table
                List<Employees> empList = empDAO.getAllEmployees();
                List<UserAccount> userList = userDAO.getAllUserAccounts();

                loadEmployeesTable(empList, userList);
            }
        }));
    }

    private void initViewTechnicians(){
        panel.getViewTechnicians().addActionListener(e->{
            List<Technicians> techList = techDAO.getAllTechnicians();
            List<UserAccount> userList = userDAO.getAllUserAccounts();

            loadTechniciansTable(techList, userList);
        });
    }

    private void loadTechniciansTable(List<Technicians> techList, List<UserAccount> userList){
        List<TechUser> techUsers = userDAO.getAllTechUsers();
        panel.setupTechniciansTable(techList, userList, techUsers); 

        JTable table = panel.getTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        table.getColumn("Deactivate").setCellRenderer(new ButtonRenderer("Deactivate")); 

        initDeactivateTechnician(table);
        initEditTech(table);
        panel.revert();
    }

    private void initDeactivateTechnician(JTable table){
        table.getColumn("Deactivate").setCellEditor(new ButtonEditor(new JCheckBox(), "Deactivate", row -> {
            String status = table.getValueAt(row, 5).toString().trim();
            
            if (status.contentEquals("Inactive")){
                JOptionPane.showMessageDialog(null, "User is already deactivated.");
                return;
            }
            
            int userID = (int) table.getValueAt(row, 0);
            int technicianID = techDAO.getTechnicianIdByUserId(userID);

            if (technicianID == -1) {
                JOptionPane.showMessageDialog(null, "Technician record not found for this user.");
                return;
            }

            if(ticketsDAO.hasActiveOrEnqueuedTickets(technicianID)) {
                JOptionPane.showMessageDialog(null,
                        "This Technician still has Active or Enqueued Tickets.", "Cannot Deactivate Technician",
                                JOptionPane.WARNING_MESSAGE);
                return;
            }
                        
            int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to deactivate this user?",
                    "Confirm Deactivation",JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION){
                techDAO.deactivateTechnician(technicianID);
                JOptionPane.showMessageDialog(null, "Deactivated User ID " + userID);
                
                // refresh table
                List<Technicians> techList = techDAO.getAllTechnicians();
                List<UserAccount> userList = userDAO.getAllUserAccounts();

                loadTechniciansTable(techList, userList);
            }
        }));
    }
}