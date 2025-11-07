package view.admin;

import java.util.List;
import java.util.Map;
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
    private JRadioButton showDeactivated;

    private JTable table;
    private JScrollPane tableScrollPane;

    public UserManagementPanel(){
        setLayout(null);
        setupViewEmployeesButton();
        setupViewTechniciansButton();
        setupAddUserButton();
        setupViewByDepartmentButton();
        setupShowDeactivatedButton();
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

    private void setupShowDeactivatedButton(){
        showDeactivated = new JRadioButton("Show deactivated users");
        showDeactivated.setBounds(390, 20, 210, 25);
        add(showDeactivated);
    }

    public void shift(){
        viewByDepartment.setVisible(true);
        showDeactivated.setBounds(565,20,200,25);
    }

    public void revert(){
        viewByDepartment.setVisible(false);
        showDeactivated.setBounds(390, 20, 200, 25);
    }


    private DefaultTableModel setupTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0, 60, 760, 460);
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    public void setupEmployeesTable(List<Employees> employeeList, List<User> users){
        String[] cols = {"User ID", "Emp ID", "Username", "Password", "Name", "Dept ID", "Role", "Status"};
        DefaultTableModel model = setupTable(cols);

        // extrazct user accounts that are employees, map by userid
        Map<Integer, User> userMap = users.stream()
                                        .filter(u -> u.getRole() == User.Role.EMPLOYEE || u.getRole() == User.Role.ADMIN)
                                        .collect(Collectors.toMap(User::getUserID, u -> u));

        for (Employees e : employeeList) {
            User u = userMap.get(e.getUserID());

            if (u != null){
                String fullName = e.getLastName() + ", " + e.getFirstName();
                String status = u.getIsActive() ? "Active" : "Inactive";
                Object[] row = { u.getUserID(), e.getEmpID(), u.getUsername(), u.getPassword(), fullName, e.getDeptID(), e.getRole(), status };
                model.addRow(row);
            }
        }

        table.setModel(model);
    }

    public void setupTechniciansTable(List<Technicians> technicianList, List<User> users){
        String[] cols = {"User ID", "Tech ID", "Username", "Password", "Name", "HasActiveTicket", "Status"};
        DefaultTableModel model = setupTable(cols);


        // extract user accounts that are technicians, map by userid
        Map<Integer, User> userMap = users.stream()
                                        .filter(u -> u.getRole() == User.Role.TECHNICIAN)
                                        .collect(Collectors.toMap(User::getUserID, u -> u));
    
        for (Technicians t : technicianList){
            User u = userMap.get(t.getUser_ID());

            if (u != null){
                String fullName = t.getTech_lastName() + ", " + t.getTech_firstName();
                String hasActiveTicket = t.isHasActiveTicket() ? "True" : "False";
                String status = u.getIsActive() ? "Active" : "Inactive";


                Object[] row = { u.getUserID(), t.getTechnician_id(), u.getUsername(), u.getPassword(), fullName, hasActiveTicket, status };
                model.addRow(row);
            }
        }

        table.setModel(model);
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

    public JRadioButton getShowDeactivated() {
        return showDeactivated;
    }
}
