package view.admin;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Employees;
import models.Technicians;
import models.User;

public class UserManagementPanel extends JPanel{
    private JButton viewEmployees;
    private JButton viewTechnicians;
    private JButton addUser;
    private JButton viewByDepartment;

    private JTable table;
    private JScrollPane tableScrollPane;

    public UserManagementPanel(){
        setLayout(null);
        setupViewEmployeesButton();
        setupViewTechniciansButton();
        setupAddUserButton();
        setupViewByDepartmentButton();
    }

    private void setupViewEmployeesButton(){
        viewEmployees = new JButton("View Employees");
        viewEmployees.setBounds(0, 20, 130, 25);
        add(viewEmployees);
    }

    private void setupViewTechniciansButton(){
        viewTechnicians = new JButton("View Technicians");
        viewTechnicians.setBounds(135, 20, 135, 25);
        add(viewTechnicians);
    }

    private void setupAddUserButton(){
        addUser = new JButton("Add User");
        addUser.setBounds(275, 20, 100, 25);
        add(addUser);
    }

    private void setupViewByDepartmentButton(){
        viewByDepartment = new JButton("View By Departments");
        viewByDepartment.setBounds(390,20,160,25);
        add(viewByDepartment);
        viewByDepartment.setVisible(false);
    }


    public void shift(){
        viewByDepartment.setVisible(true);
    }

    public void revert(){
        viewByDepartment.setVisible(false);
    }

    private DefaultTableModel setupTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                String colName = getColumnName(column);
                return colName.equals("Edit") || colName.equals("Deactivate");
            }
        };

        table = new JTable(model);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0, 60, 1160, 630);
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    public void setupEmployeesTable(List<Employees> employeeList, List<User> users){
        String[] cols = {"User ID", "Emp ID", "Username", "Password", "Name", "Dept ID", "Job Title", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);
        
        // sorted by active users
        List<User> userList = users.stream()
                .filter(u -> u.getRole() == User.Role.EMPLOYEE || u.getRole() == User.Role.ADMIN)
                .sorted((u1, u2) -> Boolean.compare(u2.getIsActive(), u1.getIsActive()))
                .collect(Collectors.toList());
        
        for (User u : userList) {
            Employees e = employeeList.stream()
                            .filter(emp -> emp.getUserID() == u.getUserID())
                            .findFirst()
                            .orElse(null);

            if (e != null) {
                String fullName = e.getLastName() + ", " + e.getFirstName();
                String status = u.getIsActive() ? "Active" : "Inactive";
                Object[] row = { u.getUserID(), e.getEmpID(), u.getUsername(), u.getPassword(),
                                fullName, e.getDeptID(), e.getJobTitle(), status, "Edit", "Delete"};
                model.addRow(row);
            }
        }

        table.setModel(model);
    }

    public void setupTechniciansTable(List<Technicians> technicianList, List<User> users){
        String[] cols = {"User ID", "Tech ID", "Username", "Password", "Name", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        List<User> userList = users.stream()
                    .filter(u -> u.getRole() == User.Role.TECHNICIAN)
                    .sorted((u1, u2) -> Boolean.compare(u2.getIsActive(), u1.getIsActive()))
                    .collect(Collectors.toList());

        for (User u : userList){
            Technicians t = technicianList.stream()
                            .filter(tec -> tec.getUser_ID() == u.getUserID())
                            .findFirst()
                            .orElse(null);
            
            if (t != null){
                String fullName = t.getTech_lastName() + ", " + t.getTech_firstName();
                String status = u.getIsActive() ? "Active" : "Inactive";


                Object[] row = { u.getUserID(), t.getTechnician_id(), u.getUsername(), u.getPassword(), fullName, status };
                model.addRow(row);
            }

        }

        table.setModel(model);
    }

    public JTable getTable(){
        return table;
    }

    public JButton getViewEmployees() {
        return viewEmployees;
    }

    public JButton getViewTechnicians() {
        return viewTechnicians;
    }

    public JButton getViewByDepartment(){
        return viewByDepartment;
    }

    public JButton getAddUser() {
        return addUser;
    }
}