package view.admin;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.EmployeeUserTableModel;
import models.TechnicianUserTableModel;

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
        tableScrollPane.setBounds(20, 70, 1120, 500);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    // ================= EMPLOYEES TABLE =================
    public void setupEmployeesTable(List<EmployeeUserTableModel> empList) {
        String[] cols = {"User ID", "Emp ID", "Username", "Password", "Name", "Department", "Job Title", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        for (EmployeeUserTableModel e : empList) {
            if (e != null) {
                String status = e.isActive() ? "Active" : "Inactive";
                Object[] row = {e.getUserID(), e.getEmpID(), e.getUsername(), e.getPassword(), e.getName(), e.getDepartment(), e.getJobTitle(), status, "Edit", "Deactivate"};
                model.addRow(row);
            }
        }

        table.setModel(model);
    }

    // ================= TECHNICIANS TABLE =================
    public void setupTechniciansTable(List<TechnicianUserTableModel> technicianList) {
        String[] cols = {"User ID", "Tech ID", "Username", "Password", "Name", "Status", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        for (TechnicianUserTableModel t : technicianList) {
            if (t != null) {
                String status = t.isActive() ? "Active" : "Inactive";
                Object[] row = {t.getUserID(), t.getTechID(), t.getUsername(), t.getPassword(), t.getName(), status, "Edit", "Deactivate"};
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
