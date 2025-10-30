package controllers;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import javax.swing.JOptionPane;
import models.Employees;
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
        saveUserFunctionality();
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

    private void saveUserFunctionality(){
        addUserPanel.getSaveButton().addActionListener(e->{
            StringBuilder errors = new StringBuilder();
            String selectedRole = (String) addUserPanel.getRoles().getSelectedItem();

            if (selectedRole.contentEquals("Technician")){
                System.out.println("Hello!");
            } else {
                insertToEmployee(selectedRole,errors);
            }

        });
    }

    private Integer insertToUsers(StringBuilder errors){
        String username = addUserPanel.getUsernameField().getText().trim();
        String password = addUserPanel.getPasswordField().getText().trim();
        String selectedRole = (String) addUserPanel.getRoles().getSelectedItem();
        User.Role role = User.Role.valueOf(selectedRole.toUpperCase());

        if (!isValidInput(errors, username, password)){
            JOptionPane.showMessageDialog(null, errors.toString(),"Input Error",JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // placeholder for values
        User u = new User(0,username,password,role);

        return userDAO.insertUser(u);
    }

    private boolean isValidInput(StringBuilder errors, String username, String password){        
        if (username.length() < 3 || username.length() > 20){
            errors.append("Username must be 3-20 characters long.\n");
        }

        if (password.length() < 3 || username.length() > 15){
            errors.append("Password must be 3-15 characters long.\n");
        }

        if (isUsernameExisting(username)){
            errors.append("Username already exists.");
            return false;
        }

        return errors.length() == 0;
    }

    private boolean isUsernameExisting(String username){
        return userDAO.getUserByUsername(username) != null;
    }

    private void insertToEmployee(String selectedRole, StringBuilder errors){
        Integer userID = insertToUsers(errors);

        if (userID == null){
            return;
        }

        String lastName = addUserPanel.getLastNameField().getText();
        String firstName = addUserPanel.getFirstNameField().getText();

        if (selectedRole.contentEquals("Admin")){
            Employees emp = new Employees(0, userID, lastName, firstName, 7, "Admin", true);
            empDAO.insertEmployee(emp);
            JOptionPane.showMessageDialog(null, "Successfully inserted " + firstName + " " + lastName);
            return;
        }
        
        if (addUserPanel.getEmployeeRole().getText() == null || addUserPanel.getEmployeeRole().getText().isBlank()){
            // delete because the user is in the users table but not in the employees table.
            userDAO.deleteUser(userID);

            JOptionPane.showMessageDialog(null, "Employee role must not be blank.");
            return;
        }

        String department = (String) addUserPanel.getDepartmentBox().getSelectedItem();
        Integer departmentID = deptDAO.getDepartmentIDByName(department);
        String role = addUserPanel.getEmployeeRole().getText().trim();
        Employees emp = new Employees(0, userID, lastName, firstName, departmentID, role, true);

        empDAO.insertEmployee(emp);
        JOptionPane.showMessageDialog(null, "Successfully inserted " + firstName + " " + lastName);
    }
}