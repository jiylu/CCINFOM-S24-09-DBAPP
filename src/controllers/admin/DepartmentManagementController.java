package controllers.admin;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import java.util.List;
import javax.swing.*;
import models.Department;
import util.ButtonEditor;
import util.ButtonRenderer;
import view.admin.AdminDashboardPanel;
import view.admin.DepartmentManagementPanel;

public class DepartmentManagementController {
    private DepartmentManagementPanel panel;
    private DepartmentDAO deptDAO;
    private EmployeesDAO empDAO;
    public DepartmentManagementController(DepartmentManagementPanel panel, DepartmentDAO deptDAO, EmployeesDAO empDAO){
        this.panel = panel;
        this.deptDAO = deptDAO;
        this.empDAO = empDAO;
    }

    public void init(AdminDashboardPanel adminPanel){
        adminPanel.showPanel(AdminDashboardPanel.VIEW_DEPT);
        List<Department> deptList = deptDAO.getAllDepartments();        
        loadDeptTable(deptList);
    }

    public void initListener(){
        panel.getAddDepartmentButton().addActionListener(e->{
            initAddDepartment();
        });
    }

    private void loadDeptTable(List<Department> deptList){
        panel.setupDeptTable(deptList);
        JTable table = panel.getTable();
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        table.getColumn("Deactivate").setCellRenderer(new ButtonRenderer("Deactivate"));

        initEditDept(table);
        initDeactivateDept(table);
    }

    private void initAddDepartment(){
        JPanel panel = new JPanel();
        JLabel deptNameLabel = new JLabel("Enter department name");
        JTextField textField = new JTextField(20); 
        panel.add(deptNameLabel);
        panel.add(textField);

        int res = JOptionPane.showConfirmDialog(null, panel, "Add Department", JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.YES_OPTION){
            String deptName = textField.getText().trim();
            
            if (isExistingDepartment(deptName)){
                return;
            }

            deptDAO.insertDepartment(deptName);
            JOptionPane.showMessageDialog(null, "Inserted " + deptName);
            loadDeptTable(deptDAO.getAllDepartments());
        }        
    }

    private void initEditDept(JTable table){
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", row -> {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Edit department name");
            JTextField textField = new JTextField(20);
            int deptID = (int) table.getValueAt(row, 0);
            String currDeptName = table.getValueAt(row, 1).toString();

            textField.setText(currDeptName);
            panel.add(label);
            panel.add(textField);

            int res = JOptionPane.showConfirmDialog(null, panel, "Edit Department", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.YES_OPTION){
                String input = textField.getText().trim();
    
                if (isExistingDepartment(input)){
                    return;
                }

                deptDAO.editDepartment(deptID, input);
                JOptionPane.showMessageDialog(null, "Successfully edited DeptID: " + deptID);
                loadDeptTable(deptDAO.getAllDepartments());
            }
        }));
    }

    private void initDeactivateDept(JTable table){
        table.getColumn("Deactivate").setCellEditor(new ButtonEditor(new JCheckBox(), "Deactivate", row -> {
            int deptID = (int) table.getValueAt(row, 0);
            if (!empDAO.getEmployeesByDepartment(deptID).isEmpty()){
                JOptionPane.showMessageDialog(null, "This department has employees.", "Cannot deactivate department", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to deactivate this department", null, JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.YES_OPTION){
                deptDAO.deactivateDepartment(deptID);
                loadDeptTable(deptDAO.getAllDepartments());
            }
        }));
    }

    public boolean isExistingDepartment(String department){
        if (deptDAO.getDepartmentIDByName(department) != null){
            JOptionPane.showMessageDialog(null, "Department already exists!", "Input error", JOptionPane.WARNING_MESSAGE);
            return true;
        }        

        return false;
    }
    
}
