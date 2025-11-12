package controllers.admin;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.TechniciansDAO;
import dao.UserDAO;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.Employees;
import models.Technicians;
import models.User;
import util.ButtonEditor;
import view.admin.AddUserPanel;

public class EditUserController {
    private Runnable onSaveCallback;
    private UserDAO userDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private AddUserPanel editPanel;

    private int userID;
    private int specID;
    private User.Role userRole;
    private String username;
    private String password;
    private String lastName;
    private String firstName; 
    private int deptID;

    public EditUserController (UserDAO userDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO){
        this.userDAO = userDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
    }

    public void setOnSaveCallback(Runnable callback){
        this.onSaveCallback = callback;
    }

    public void initListener(JTable table){
        editPanel = new AddUserPanel();

        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", row -> {
            extractRowData(table, row);
            determineRole(table, row);
            setupFields(table, row);
            editPanel.showPanel();
        }));

        editPanel.getSaveButton().addActionListener(e -> {
            StringBuilder errors = new StringBuilder();
            
            if (!validateInputFields(errors)){
                return;
            }

            User user = updateUserData();

            switch (user.getRole()){
                case ADMIN -> updateAdminData(user);
                case EMPLOYEE -> updateEmployeeData(user);
                case TECHNICIAN -> updateTechnicianData(user);
            }

            JOptionPane.showMessageDialog(null, "Sucessfuly Updated " + user.getUserID());

            if (onSaveCallback != null){
                onSaveCallback.run();
            }
        });
    }

    private void extractRowData(JTable table, int row){
        userID = (int) table.getValueAt(row, 0);
        specID = (int) table.getValueAt(row, 1);

        username = table.getValueAt(row, 2).toString();
        password = table.getValueAt(row, 3).toString();
        String fullName = table.getValueAt(row, 4).toString();
        String[] nameParts = fullName.split(",\\s*");
        lastName = nameParts[0];
        firstName = nameParts.length > 1 ? nameParts[1] : "";
    }

    private void determineRole(JTable table, int row) {
        Object roleValue = table.getValueAt(row, 6);

        if (roleValue != null && roleValue.toString().equalsIgnoreCase("Admin")) {
            userRole = User.Role.ADMIN;
            deptID = (int) table.getValueAt(row, 5);
        } else if (table.getValueAt(row, 5) instanceof Integer) { 
            userRole = User.Role.EMPLOYEE;
            deptID = (int) table.getValueAt(row, 5);
        } else {
            userRole = User.Role.TECHNICIAN;
        }
    }

    private void setupFields(JTable table, int row){
        editPanel.getTitleLabel().setText("Edit User");
        editPanel.getUsernameField().setText(username);
        editPanel.getPasswordField().setText(password);
        editPanel.getFirstNameField().setText(firstName);
        editPanel.getLastNameField().setText(lastName);

        String role = userRole.name().toLowerCase();
        editPanel.getRoles().setSelectedItem(Character.toUpperCase(role.charAt(0)) + role.substring(1));

        dropBoxFunctionality();

        if (userRole == User.Role.EMPLOYEE) {
            String[] departments = deptDAO.getAllDepartmentNames(false).toArray(new String[0]);
            editPanel.transformToEmployeeFields(departments);

            String currDept = deptDAO.getDepartmentByID(deptID).getDepartmentName();
            editPanel.getDepartmentBox().setSelectedItem(currDept);
            editPanel.getEmployeeRole().setText(table.getValueAt(row, 6).toString());
        } 


        if (userRole == User.Role.EMPLOYEE || userRole == User.Role.ADMIN){
            editPanel.getRoles().removeItem("Technician");
        } else {
            editPanel.getRoles().setEnabled(false);
        }
    }

    private void dropBoxFunctionality(){
        editPanel.getRoles().addActionListener(e->{
            String selectedRole = (String) editPanel.getRoles().getSelectedItem();
            String[] departmentList = deptDAO.getAllDepartmentNames(true).toArray(new String[0]);
            
            if (selectedRole.contentEquals("Employee")){
                userRole = User.Role.EMPLOYEE;
                editPanel.transformToEmployeeFields(departmentList);
            } else {
                userRole = User.Role.ADMIN;
                editPanel.revert();
            }
        });
    }

    private User updateUserData(){
        String newUsername = editPanel.getUsernameField().getText();
        String newPassword = editPanel.getPasswordField().getText();

        User user = new User(userID, newUsername, newPassword, userRole);
        userDAO.editUser(user);

        return user;
    }

    private void updateEmployeeData(User user){
        String newLastName = editPanel.getLastNameField().getText().trim();
        String newFirstName = editPanel.getLastNameField().getText().trim();
        String newRole = editPanel.getEmployeeRole().getText().trim();
        String newDept = editPanel.getDepartmentBox().getSelectedItem().toString();
        int newDeptID = deptDAO.getDepartmentIDByName(newDept);

        Employees emp = new Employees(specID, user.getUserID(), newLastName, newFirstName, newDeptID, newRole);
        empDAO.updateEmployee(emp);
    }

    private void updateAdminData(User user){
        String newLastName = editPanel.getLastNameField().getText().trim();
        String newFirstName = editPanel.getLastNameField().getText().trim();
        int fieldDeptID = deptDAO.getDepartmentIDByName("Administration");

        Employees emp = new Employees(specID, user.getUserID(), newLastName, newFirstName, fieldDeptID, "Admin");
        empDAO.updateEmployee(emp);
    }

    private void updateTechnicianData(User user){
        String newLastName = editPanel.getLastNameField().getText().trim();
        String newFirstName = editPanel.getFirstNameField().getText().trim();

        Technicians tech = new Technicians(specID, user.getUserID(), newLastName, newFirstName);
        techDAO.updateTechnician(tech);
    }

    private boolean validateInputFields(StringBuilder errors){
        if (!isValidUserFieldInput(errors)){
            JOptionPane.showMessageDialog(null, errors.toString(),"Input Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (userRole == User.Role.EMPLOYEE && !isValidEmpFieldInput(errors)){
            JOptionPane.showMessageDialog(null, errors.toString(),"Input Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidUserFieldInput(StringBuilder errors){        
        String newUsername = editPanel.getUsernameField().getText();
        String newPassword = editPanel.getPasswordField().getText();
        String fieldLastName = editPanel.getLastNameField().getText().trim();
        String fieldFirstName = editPanel.getFirstNameField().getText().trim();
        
        if (newUsername.length() < 3 || newUsername.length() > 20){
            errors.append("Username must be 3-20 characters long.\n");
        }

        if (newPassword.length() < 3 || newPassword.length() > 15){
            errors.append("Password must be 3-15 characters long.\n");
        }

        if (fieldLastName.isEmpty() || fieldFirstName.isEmpty()){
            errors.append("Name fields should not be empty.\n");
        }

        if (fieldLastName.length() > 50 || fieldFirstName.length() > 50){
            errors.append("Name fields should not exceed 50 characters.\n");
        }

        if (isUsernameExisting(newUsername)){
            errors.append("Username already exists.");
            return false;
        }

        return errors.length() == 0;
    }

    private boolean isValidEmpFieldInput(StringBuilder errors){
        String jobTitle = editPanel.getEmployeeRole().getText().trim();
        
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

    private boolean isUsernameExisting(String newUsername){
        if (newUsername.contentEquals(username)){
            return false;
        }

        return userDAO.getUserByUsername(newUsername) != null;
    }

    
}