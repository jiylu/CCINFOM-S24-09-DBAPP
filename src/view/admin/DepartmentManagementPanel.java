package view.admin;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Department;

public class DepartmentManagementPanel extends JPanel{
    private JTable table;
    private JScrollPane tableScrollPane;    
    private JButton addDepartmentButton;

    public DepartmentManagementPanel(){
        setLayout(null);
        setupAddDepartmentsButton();
    }

    private void setupAddDepartmentsButton(){
        addDepartmentButton = new JButton("Add Department");
        addDepartmentButton.setBounds(0, 20, 130, 25);
        add(addDepartmentButton);
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

    public void setupDeptTable(List<Department> deptList){
        String[] cols = {"Department ID", "Department Name","Active","Edit", "Deactivate"};
        DefaultTableModel model = setupTable(cols);
        for (Department d : deptList){
            String active = d.getActive() ? "Active" : "Inactive";
            Object[] row = {d.getDepartmentID(), d.getDepartmentName(), active,  "Edit", "Deactivate"};
            model.addRow(row);
        }

        table.setModel(model);
    }

    public JButton getAddDepartmentButton(){
        return addDepartmentButton;
    }

    public JTable getTable(){
        return table;
    }
}
