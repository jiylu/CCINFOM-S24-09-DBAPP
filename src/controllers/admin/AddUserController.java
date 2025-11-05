package controllers.admin;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import javax.swing.*;

import models.Employees;
import models.User;
import view.admin.AddUserPanel;

public class AddUserController {
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private AddUserPanel addUserPanel;
    public AddUserController(UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO){
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.addUserPanel = new AddUserPanel();
    }

    public void init(){
        addUserPanel.showPanel();
        addUserPanel.reset();
    }

    public void initListeners(){
        dropBoxFunctionality();
        saveUserFunctionality();
    }

    private void dropBoxFunctionality(){
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
            Employees emp = new Employees(0, userID, lastName, firstName, 7, "Admin");
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
        Employees emp = new Employees(0, userID, lastName, firstName, departmentID, role);

        empDAO.insertEmployee(emp);
        JOptionPane.showMessageDialog(null, "Successfully inserted " + firstName + " " + lastName);
    }
}
