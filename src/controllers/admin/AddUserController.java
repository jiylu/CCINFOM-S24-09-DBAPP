package controllers.admin;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import javax.swing.*;
import models.Employees;
import models.Technicians;
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
            String[] departmentList = deptDAO.getAllDepartmentNames(true).toArray(new String[0]);
            
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
                insertToTechnicians(errors);
            } else {
                insertToEmployee(selectedRole,errors);
            }

        });
    }

    private Integer insertToUsers(){
        String username = addUserPanel.getUsernameField().getText().trim();
        String password = addUserPanel.getPasswordField().getText().trim();
        String selectedRole = (String) addUserPanel.getRoles().getSelectedItem();
        User.Role role = User.Role.valueOf(selectedRole.toUpperCase());
        
        User u = new User(0,username,password,role);
        return userDAO.insertUser(u);
    }

    private void insertToEmployee(String selectedRole, StringBuilder errors){
        if (!validateInputFields(errors)){
            return;
        }
        
        Integer userID = insertToUsers();
        String lastName = addUserPanel.getLastNameField().getText();
        String firstName = addUserPanel.getFirstNameField().getText();

        if (selectedRole.contentEquals("Admin")){
            Employees emp = new Employees(0, userID, lastName, firstName, 7, "Admin");
            empDAO.insertEmployee(emp);
            JOptionPane.showMessageDialog(null, "Successfully inserted " + firstName + " " + lastName);
            return;
        }
        
        String department = (String) addUserPanel.getDepartmentBox().getSelectedItem();
        Integer departmentID = deptDAO.getDepartmentIDByName(department);
        String role = addUserPanel.getEmployeeRole().getText().trim();
        Employees emp = new Employees(0, userID, lastName, firstName, departmentID, role);

        empDAO.insertEmployee(emp);
        JOptionPane.showMessageDialog(null, "Successfully inserted " + firstName + " " + lastName + " to the Employees Table.");
    }

    private void insertToTechnicians(StringBuilder errors){
        if (!validateInputFields(errors)){
            return;
        }

        Integer userID = insertToUsers();
        String lastName = addUserPanel.getLastNameField().getText();
        String firstName = addUserPanel.getFirstNameField().getText();

        Technicians tech = new Technicians(0, userID, lastName, firstName);
        techDAO.insertTechnician(tech);
        JOptionPane.showMessageDialog(null, "Successfuully inserted " + firstName + " " + lastName + " to the Technicians Table.");
    }

    private boolean validateInputFields(StringBuilder errors){
        String selectedRole = (String) addUserPanel.getRoles().getSelectedItem();
        
        if (!isValidUserFieldInput(errors)){
            JOptionPane.showMessageDialog(null, errors.toString(),"Input Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (selectedRole.contentEquals("Employee") && !isValidEmpFieldInput(errors)){
            JOptionPane.showMessageDialog(null, errors.toString(),"Input Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidUserFieldInput(StringBuilder errors){        
        String username = addUserPanel.getUsernameField().getText().trim();    
        String password = addUserPanel.getPasswordField().getText().trim();
        String lastName = addUserPanel.getLastNameField().getText().trim();
        String firstName = addUserPanel.getFirstNameField().getText().trim();

        if (username.length() < 3 || username.length() > 20){
            errors.append("Username must be 3-20 characters long.\n");
        }

        if (password.length() < 3 || password.length() > 15){
            errors.append("Password must be 3-15 characters long.\n");
        }

        if (lastName.isEmpty() || firstName.isEmpty()){
            errors.append("Name fields should not be empty.\n");
        }

        if (lastName.length() > 50 || firstName.length() > 50){
            errors.append("Name fields should not exceed 50 characters.\n");
        }

        if (isUsernameExisting(username)){
            errors.append("Username already exists.\n");
        }

        return errors.length() == 0;
    }

    private boolean isUsernameExisting(String username){
        return userDAO.getUserByUsername(username) != null;
    }

    private boolean isValidEmpFieldInput(StringBuilder errors){
        String jobTitle = addUserPanel.getEmployeeRole().getText().trim();
        
        if (jobTitle.isEmpty()){
            errors.append("Job Title field cannot be empty.\n");
            return false;
        }

        if (jobTitle.length() > 50){
            errors.append("Job Title cannot exceed 50 characters.\n");
            return false;
        }

        return true;
    }
    


}
