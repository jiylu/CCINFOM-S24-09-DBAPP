package view.admin;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import models.EmpUser;
import models.Employees;
import models.TechUser;
import models.Technicians;
import models.UserAccount;

public class UserManagementPanel extends JPanel {
    private JButton viewEmployees;
    private JButton viewTechnicians;
    private JButton addUser;
    private JButton viewByDepartment;

    private JLabel searchLabel;
    private JTextField searchByUserIdField;
    private JButton searchButton;

    private JTable table;
    private JScrollPane tableScrollPane;

    public UserManagementPanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background

        setupViewEmployeesButton();
        setupViewTechniciansButton();
        setupAddUserButton();
        setupViewByDepartmentButton();
        setupSearchLabel();
        setupSearchByUserIdField();
        setupSearchButton();
    }

    // ================= BUTTON SETUP =================
    private void setupViewEmployeesButton() {
        viewEmployees = new JButton("View Employees");
        styleButton(viewEmployees);
        viewEmployees.setBounds(20, 20, 150, 35);
        add(viewEmployees);
    }

    private void setupViewTechniciansButton() {
        viewTechnicians = new JButton("View Technicians");
        styleButton(viewTechnicians);
        viewTechnicians.setBounds(180, 20, 160, 35);
        add(viewTechnicians);
    }

    private void setupAddUserButton() {
        addUser = new JButton("Add User");
        styleButton(addUser);
        addUser.setBounds(350, 20, 120, 35);
        add(addUser);
    }

    private void setupViewByDepartmentButton() {
        viewByDepartment = new JButton("View By Departments");
        styleButton(viewByDepartment);
        viewByDepartment.setBounds(480, 20, 200, 35); // increase width from 180 -> 200
        viewByDepartment.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10)); // reduce padding slightly
        viewByDepartment.setVisible(false);
        add(viewByDepartment);
    }

    private void setupSearchLabel() {
        searchLabel = new JLabel("User ID:");
        searchLabel.setBounds(925, 20, 100, 25); 
        add(searchLabel);
        searchLabel.setVisible(false);
    }

    private void setupSearchByUserIdField() {
        searchByUserIdField = new JTextField(10);
        searchByUserIdField.setBounds(980, 20, 100, 25);
        add(searchByUserIdField);
        searchByUserIdField.setVisible(false);
    }
    
    private void setupSearchButton() {
        searchButton = new JButton("Search");
        searchButton.setBounds(1085, 20, 80, 25);
        add(searchButton);
        searchButton.setVisible(false);

    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 102, 204)); // Blue buttons
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 80, 160));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });
    }

    public void shift() {
        viewByDepartment.setVisible(true);
    }

    public void revert() {
        viewByDepartment.setVisible(false);
    }

    public void showSearchUserId(){
        searchLabel.setVisible(true);
        searchByUserIdField.setVisible(true);
        searchButton.setVisible(true);
    }

    public void hideSearchUserId(){
        searchLabel.setVisible(false);
        searchByUserIdField.setVisible(false);
        searchButton.setVisible(false);
    }
    // ================= TABLE SETUP =================
    private DefaultTableModel setupTable(String[] cols) {
        if (tableScrollPane != null) {
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
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoCreateRowSorter(true); // Enable sorting
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(20, 70, 1120, 620);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    // ================= EMPLOYEES TABLE =================
    public void setupEmployeesTable(List<Employees> employeeList, List<UserAccount> users, List<EmpUser> empUsers) {
        String[] cols = {"User ID", "Emp ID", "Username", "Password", "Name", "Dept ID", "Job Title", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        List<Map.Entry<UserAccount, Integer>> mapped = empUsers.stream()
                .flatMap(eu -> users.stream()
                        .filter(u -> u.getUserID() == eu.getUserID() &&
                                (UserAccount.EMP_ROLE.equals(u.getRole()) || UserAccount.ADMIN_ROLE.equals(u.getRole())))
                        .map(u -> new AbstractMap.SimpleEntry<>(u, eu.getEmpID()))
                )
                .collect(Collectors.toList());

        mapped.sort((a, b) -> {
            Employees ea = employeeList.stream()
                    .filter(emp -> emp.getEmpID() == a.getValue())
                    .findFirst()
                    .orElse(null);

            Employees eb = employeeList.stream()
                    .filter(emp -> emp.getEmpID() == b.getValue())
                    .findFirst()
                    .orElse(null);

            if (ea == null || eb == null) return 0;
            return Boolean.compare(eb.isActive(), ea.isActive());
        });

        for (Map.Entry<UserAccount, Integer> entry : mapped) {
            UserAccount u = entry.getKey();
            int empID = entry.getValue();
            Employees e = employeeList.stream().filter(emp -> emp.getEmpID() == empID).findFirst().orElse(null);

            if (e != null) {
                String fullName = e.getLastName() + ", " + e.getFirstName();
                String status = e.isActive() ? "Active" : "Inactive";
                Object[] row = {u.getUserID(), e.getEmpID(), u.getUsername(), u.getPassword(), fullName, e.getDeptID(), e.getJobTitle(), status, "Edit", "Deactivate"};
                model.addRow(row);
            }
        }

        table.setModel(model);
    }

    // ================= TECHNICIANS TABLE =================
    public void setupTechniciansTable(List<Technicians> technicianList, List<UserAccount> users, List<TechUser> techUsers) {
        String[] cols = {"User ID", "Tech ID", "Username", "Password", "Name", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        List<Map.Entry<UserAccount, Integer>> mapped = techUsers.stream()
                .flatMap(tu -> users.stream()
                        .filter(u -> u.getUserID() == tu.getUserID() && u.getRole().contentEquals(UserAccount.TECH_ROLE))
                        .map(u -> new AbstractMap.SimpleEntry<>(u, tu.getTechID()))
                )
                .collect(Collectors.toList());

        mapped.sort((a,b) -> {
            Technicians ta = technicianList.stream().filter(t -> t.getTechnician_id() == a.getValue()).findFirst().orElse(null);
            Technicians tb = technicianList.stream().filter(t -> t.getTechnician_id() == b.getValue()).findFirst().orElse(null);
            if (ta == null || tb == null) return 0;
            return Boolean.compare(tb.isActive(), ta.isActive());
        });

        for (Map.Entry<UserAccount, Integer> entry : mapped) {
            UserAccount u = entry.getKey();
            int techID = entry.getValue();
            Technicians t = technicianList.stream().filter(tec -> tec.getTechnician_id() == techID).findFirst().orElse(null);

            if (t != null) {
                String fullName = t.getTech_lastName() + ", " + t.getTech_firstName();
                String status = t.isActive() ? "Active" : "Inactive";
                Object[] row = {u.getUserID(), t.getTechnician_id(), u.getUsername(), u.getPassword(), fullName, status, "Edit", "Deactivate"};
                model.addRow(row);
            }
        }

        table.setModel(model);
    }

    // ================= GETTERS =================
    public JTable getTable() {
        return table;
    }

    public JButton getViewEmployees() {
        return viewEmployees;
    }

    public JButton getViewTechnicians() {
        return viewTechnicians;
    }

    public JButton getViewByDepartment() {
        return viewByDepartment;
    }

    public JButton getAddUser() {
        return addUser;
    }

    public JTextField getSearchByUserIdField() {
        return searchByUserIdField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
