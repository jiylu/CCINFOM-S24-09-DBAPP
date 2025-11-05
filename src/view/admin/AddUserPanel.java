package view.admin;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

public class AddUserPanel{
    private JPanel panel;
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

    public AddUserPanel() {
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setPreferredSize(new Dimension(500, 450));

        setupTitle();
        setupUsernameField();
        setupPasswordField();
        setupNameField();
        setupRoleBox();
        setupButton();
    }

    public void showPanel() {
        JOptionPane.showMessageDialog(null, panel, "Add New User", JOptionPane.PLAIN_MESSAGE);
    }

    private void setupTitle(){
        JLabel titleLabel = new JLabel("Add New User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(20, 20, 300, 40);
        panel.add(titleLabel);
    }

    private void setupUsernameField(){
        JLabel usernameLabel = new JLabel("Enter username:");
        usernameLabel.setBounds(20, 80, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 80, 200, 25);
        panel.add(usernameField);
    }

    private void setupPasswordField(){
        JLabel passwordLabel = new JLabel("Enter password:");
        passwordLabel.setBounds(20, 120, 200, 25);
        panel.add(passwordLabel);

        passwordField = new JTextField();
        passwordField.setBounds(130, 120, 200, 25);
        panel.add(passwordField);
    }

    private void setupNameField(){
        JLabel nameLabel = new JLabel("First Name:");
        nameLabel.setBounds(20, 160, 100, 25);
        panel.add(nameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(130, 160, 120, 25);
        panel.add(firstNameField);

        JLabel toLabel = new JLabel("Last Name:");
        toLabel.setBounds(260, 160, 80, 25);
        panel.add(toLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(330, 160, 120, 25);
        panel.add(lastNameField);
    }

    private void setupRoleBox(){
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(20, 200, 200, 25);
        panel.add(roleLabel);

        roles = new JComboBox<>(new String[] {"Admin", "Employee", "Technician"});
        roles.setBounds(130, 200, 200, 25);
        panel.add(roles);
    }

    private void setupButton(){
        saveButton = new JButton("Save");
        saveButton.setBounds(130, 240, 100, 30);
        panel.add(saveButton);
    }

    private void setupDepartmentField(String[] departmentList){
        departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(20, 230, 200, 25);
        panel.add(departmentLabel);

        departments = new JComboBox<>(departmentList);
        departments.setBounds(130, 230, 200,25);
        panel.add(departments);
    }

    private void setupEmployeeRoleField(){
        employeeRoleLabel = new JLabel("Employee Role:");
        employeeRoleLabel.setBounds(20, 260, 200, 25);
        panel.add(employeeRoleLabel);

        employeeRole = new JTextField();
        employeeRole.setBounds(130, 260, 200, 25);
        panel.add(employeeRole);
    }

    public void transformToEmployeeFields(String[] departmentList){
        setupDepartmentField(departmentList);
        setupEmployeeRoleField();
        saveButton.setBounds(130, 360, 100, 30);
        panel.revalidate();
        panel.repaint();
    }

    public void revert(){
        if (departmentLabel != null && departments != null && employeeRoleLabel != null && employeeRole != null){
            departmentLabel.setVisible(false);
            departments.setVisible(false);
            employeeRoleLabel.setVisible(false);
            employeeRole.setVisible(false);
            saveButton.setBounds(130, 240, 100, 30);

            departments.setSelectedIndex(0);
            employeeRole.setText("");
        }
    }

    public void reset(){
        usernameField.setText("");
        passwordField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        roles.setSelectedIndex(0);

        // if employee is previously selected.
        revert();  
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
