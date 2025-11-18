package controllers.admin;

import dao.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.EmpUser;
import models.EmployeeUserTableModel;
import models.TechnicianUserTableModel;
import models.UserAccount;
import util.ButtonEditor;
import util.ButtonRenderer;
import view.admin.AddUserPanel;
import view.admin.AdminDashboardPanel;
import view.admin.UserManagementPanel;
public class UserManagementController {
    private EmpUser user;
    private UserManagementPanel panel;    
    private UserTableDAO userTableDAO;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private TicketsDAO ticketsDAO;

    private AddUserPanel addUserPanel;
    private AddUserController addUserController;

    public UserManagementController(EmpUser user, UserManagementPanel panel, UserTableDAO userTableDAO, UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO){
        this.user = user;
        this.panel = panel;
        this.userTableDAO = userTableDAO;
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
        initViewByUserID();
    }

    private void initAddUsers(){
        panel.getAddUser().addActionListener(e->{
            addUserController.init();
        });
    }

    private void initViewEmployees(){
        panel.getViewEmployees().addActionListener(e->{
            List<EmployeeUserTableModel> empList = userTableDAO.getAllEmployees();
            loadEmployeesTable(empList);
        });
    }

    private void loadEmployeesTable(List<EmployeeUserTableModel> empList){
        panel.setupEmployeesTable(empList);
        JTable table = panel.getTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        table.getColumn("Deactivate").setCellRenderer(new ButtonRenderer("Deactivate")); 
        initEditEmp(table);
        initDeactivateEmployee(table);
        panel.shift();
        panel.showSearchUserId();
    }

    private void initViewByUserID(){
        panel.getSearchButton().addActionListener(e->{
        String userId = panel.getSearchByUserIdField().getText().trim();

        if (userId.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a User ID to search.", "Search Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userID;

        try {
            userID = Integer.parseInt(userId);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid User ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserAccount userD = userDAO.getUserAccountByID(userID);

        if (userD == null){
            JOptionPane.showMessageDialog(null, "User ID " + userID + " not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (userD.getRole().equals("Employee")){ 
                EmployeeUserTableModel emp = userTableDAO.getEmployee(userID);
                List<EmployeeUserTableModel> empList = new ArrayList<>();
                empList.add(emp);
                loadEmployeesTable(empList);

        } else if (userD.getRole().equals("Technician")){
                TechnicianUserTableModel tech = userTableDAO.getTechnicianByUserID(userID);
                List<TechnicianUserTableModel> techList = new ArrayList<>();
                techList.add(tech);
                loadTechniciansTable(techList);

        } else {
            JOptionPane.showMessageDialog(null, "User ID " + userID + " is not an Employee or Technician.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }     
        
    });
 };

    private void initViewEmpByDepartment(){
        panel.getViewByDepartment().addActionListener(e->{
            JComboBox<String> comboBox = new JComboBox<>(deptDAO.getAllDepartmentNames(false).toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Department", JOptionPane.OK_CANCEL_OPTION);
            
            if (res == JOptionPane.OK_OPTION){
                String selectedDept = (String) comboBox.getSelectedItem();
                int deptID = deptDAO.getDepartmentIDByName(selectedDept);
                List<EmployeeUserTableModel> empList = userTableDAO.getAllEmployeesByDepartment(deptID);
                loadEmployeesTable(empList);
            }
        });
    }
    
    private void initEditEmp(JTable table){
        EditUserController euc = new EditUserController(userDAO, empDAO, techDAO, deptDAO);

        euc.setOnSaveCallback(() -> {
            List<EmployeeUserTableModel> empList = userTableDAO.getAllEmployees();
            loadEmployeesTable(empList);
        });

        euc.initListener(table);
    }

    private void initEditTech(JTable table){
        EditUserController euc = new EditUserController(userDAO, empDAO, techDAO, deptDAO);

        euc.setOnSaveCallback(() -> {
            List<TechnicianUserTableModel> techList = userTableDAO.getAllTechnicians();
            loadTechniciansTable(techList);
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

            if (ticketsDAO.hasActiveOrEnqueuedTickets(empID, TicketsDAO.EMP_TICKETS)) {
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
                List<EmployeeUserTableModel> empList = userTableDAO.getAllEmployees();
                loadEmployeesTable(empList);
            }
        }));
    }

    private void initViewTechnicians(){
        panel.getViewTechnicians().addActionListener(e->{
            List<TechnicianUserTableModel> techList = userTableDAO.getAllTechnicians();
            loadTechniciansTable(techList);
        });
    }

    private void loadTechniciansTable(List<TechnicianUserTableModel> techList){
        panel.setupTechniciansTable(techList); 

        JTable table = panel.getTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        table.getColumn("Deactivate").setCellRenderer(new ButtonRenderer("Deactivate")); 

        initDeactivateTechnician(table);
        initEditTech(table);
        panel.showSearchUserId();
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

            if(ticketsDAO.hasActiveOrEnqueuedTickets(technicianID, TicketsDAO.TECH_TICKETS)) {
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
                List<TechnicianUserTableModel> techList = userTableDAO.getAllTechnicians();
                loadTechniciansTable(techList);
            }
        }));
    }
}