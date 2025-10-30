package view.admin;

import java.awt.Font;
import javax.swing.*;

public class AddUserPanel extends JPanel {
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> roles;
    private JComboBox<String> departments;
    private JTextField employeeRole;
    private JButton saveButton;

    private JLabel departmentLabel;
    private JLabel employeeRoleLabel;

    public AddUserPanel(){
        setLayout(null);
        setupTitle();
        setupUsernameField();
        setupPasswordField();
        setupNameField();
        setupRoleBox();
        setupButton();
    }

    private void setupTitle(){
        JLabel titleLabel = new JLabel("Add New User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(20, 80, 300, 40);
        add(titleLabel);
    }

    private void setupUsernameField(){
        JLabel usernameLabel = new JLabel("Enter username: ");
        usernameLabel.setBounds(20, 140, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 140, 200, 25);
        add(usernameField);
    }

    private void setupPasswordField(){
        JLabel passwordLabel = new JLabel("Enter password: ");
        passwordLabel.setBounds(20, 180, 200, 25);
        add(passwordLabel);

        passwordField = new JTextField();
        passwordField.setBounds(130, 180, 200, 25);
        add(passwordField);
    }

    private void setupNameField(){
        JLabel nameLabel = new JLabel("First Name:");
        nameLabel.setBounds(20, 220, 100, 25);
        add(nameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(130, 220, 120, 25);
        add(firstNameField);

        JLabel toLabel = new JLabel("Last Name:");
        toLabel.setBounds(260, 220, 80, 25);
        add(toLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(330, 220, 120, 25);
        add(lastNameField);
    }

    private void setupRoleBox(){
        JLabel roleLabel = new JLabel("Role: ");
        roleLabel.setBounds(20,250,200,25);
        add(roleLabel);

        roles = new JComboBox<>(new String[] {"Admin", "Employee", "Technician"});
        roles.setBounds(130,250,200,25);
        add(roles);
    }

    private void setupButton(){
        saveButton = new JButton("Save");
        saveButton.setBounds(130, 280, 100, 30);
        add(saveButton);
    }

    private void setupDepartmentField(String[] departmentList){
        departmentLabel = new JLabel("Department: ");
        departmentLabel.setBounds(20, 280, 200, 25);
        add(departmentLabel);

        departments = new JComboBox<>(departmentList);
        departments.setBounds(130, 280, 200,25);
        add(departments);
    }

    private void setupEmployeeRoleField(){
        employeeRoleLabel = new JLabel("Employee Role: ");
        employeeRoleLabel.setBounds(20, 310, 200, 25);
        add(employeeRoleLabel);

        employeeRole = new JTextField();
        employeeRole.setBounds(130, 310, 200, 25);
        add(employeeRole);
    }

    public void transformToEmployeeFields(String[] departmentList){
        setupDepartmentField(departmentList);
        setupEmployeeRoleField();
        saveButton.setBounds(130, 340, 100, 30);
        revalidate();
        repaint();
    }

    public void revert(){
        if (departmentLabel != null && departments != null && employeeRoleLabel != null && employeeRole != null){
            departmentLabel.setVisible(false);
            departments.setVisible(false);
            employeeRoleLabel.setVisible(false);
            employeeRole.setVisible(false);
            saveButton.setBounds(130, 280, 100, 30);
        }
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getRoles() {
        return roles;
    }

    public  JComboBox<String> getDepartmentBox(){
        return departments;
    }

    public JTextField getEmployeeRole(){
        return employeeRole;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
