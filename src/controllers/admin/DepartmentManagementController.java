package controllers.admin;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DepartmentDAO;
import models.Department;
import util.ButtonRenderer;
import view.admin.AdminDashboardPanel;
import view.admin.DepartmentManagementPanel;

public class DepartmentManagementController {
    private DepartmentManagementPanel panel;
    private DepartmentDAO deptDAO;

    public DepartmentManagementController(DepartmentManagementPanel panel, DepartmentDAO deptDAO){
        this.panel = panel;
        this.deptDAO = deptDAO;
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
            
            if (deptDAO.getDepartmentIDByName(deptName) != null){
                JOptionPane.showMessageDialog(null, "Department already exists!", "Input error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            deptDAO.insertDepartment(deptName);
            JOptionPane.showMessageDialog(null, "Inserted " + deptName);
        }        
    }
    
}
