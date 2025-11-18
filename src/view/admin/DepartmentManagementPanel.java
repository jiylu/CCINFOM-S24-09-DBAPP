package view.admin;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import models.Department;

public class DepartmentManagementPanel extends JPanel {
    private JTable table;
    private JScrollPane tableScrollPane;
    private JButton addDepartmentButton;

    public DepartmentManagementPanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245)); // Light gray background

        setupAddDepartmentsButton();
    }

    private void setupAddDepartmentsButton() {
        addDepartmentButton = new JButton("Add Department");
        styleButton(addDepartmentButton);
        addDepartmentButton.setBounds(20, 20, 150, 35); // updated width & height
        add(addDepartmentButton);
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 102, 204)); // Blue button
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
        table.setAutoCreateRowSorter(true);
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

    public void setupDeptTable(List<Department> deptList) {
        String[] cols = {"Department ID", "Department Name", "Active", "Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);

        for (Department d : deptList) {
            String active = d.getActive() ? "Active" : "Inactive";
            Object[] row = {d.getDepartmentID(), d.getDepartmentName(), active, "Edit", "Deactivate"};
            model.addRow(row);
        }

        table.setModel(model);
    }

    public JButton getAddDepartmentButton() {
        return addDepartmentButton;
    }

    public JTable getTable() {
        return table;
    }
}
