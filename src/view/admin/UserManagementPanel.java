package view.admin;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.EmpUser;
import models.Employees;
import models.TechUser;
import models.Technicians;
import models.UserAccount;

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
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    public void setupEmployeesTable(List<Employees> employeeList, List<UserAccount> users, List<EmpUser> empUsers){
        String[] cols = {"User ID", "Emp ID", "Username", "Password", "Name", "Dept ID", "Job Title", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);
        

        // map natin user to empid
        List<Map.Entry<UserAccount, Integer>> mapped = empUsers.stream()
            .flatMap(eu -> users.stream()
                .filter(u -> u.getUserID() == eu.getUserID() &&
                            (UserAccount.EMP_ROLE.equals(u.getRole()) || UserAccount.ADMIN_ROLE.equals(u.getRole())))
                .map(u -> new AbstractMap.SimpleEntry<>(u, eu.getEmpID()))
            )
            .collect(Collectors.toList());

        
        for (Map.Entry<UserAccount, Integer> entry : mapped) {
            UserAccount u = entry.getKey();
            int empID = entry.getValue();

            Employees e = employeeList.stream()
                            .filter(emp -> emp.getEmpID() == empID)
                            .findFirst()
                            .orElse(null);

            if (e != null) {
                String fullName = e.getLastName() + ", " + e.getFirstName();
                String status = e.isActive() ? "Active" : "Inactive";
                Object[] row = {u.getUserID(), e.getEmpID(), u.getUsername(), u.getPassword(), fullName, e.getDeptID(), e.getJobTitle(), status, "Edit", "Deactivate"};
                model.addRow(row);
            }
        }


        table.setModel(model);
    }

    public void setupTechniciansTable(List<Technicians> technicianList, List<UserAccount> users, List<TechUser> techUsers) {
        String[] cols = {"User ID", "Tech ID", "Username", "Password", "Name", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        List<Map.Entry<UserAccount, Integer>> mapped = techUsers.stream()
            .flatMap(tu -> users.stream()
                .filter(u -> u.getUserID() == tu.getUserID() && u.getRole().contentEquals(UserAccount.TECH_ROLE))
                .map(u -> new AbstractMap.SimpleEntry<>(u, tu.getTechID()))
            )
            .collect(Collectors.toList()); 

        for (Map.Entry<UserAccount, Integer> entry : mapped) {
            UserAccount u = entry.getKey();
            int techID = entry.getValue();

            Technicians t = technicianList.stream()
                                    .filter(tec -> tec.getTechnician_id() == techID)
                                    .findFirst()
                                    .orElse(null);

            if (t != null) {
                String fullName = t.getTech_lastName() + ", " + t.getTech_firstName();
                String status = t.isActive() ? "Active" : "Inactive";

                Object[] row = {u.getUserID(), t.getTechnician_id(), u.getUsername(), u.getPassword(), fullName, status, "Edit", "Deactivate"};
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