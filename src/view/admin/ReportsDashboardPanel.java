package view.admin;


import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import reports.CategoryReport;

public class ReportsDashboardPanel extends JPanel{
    private JButton technicianReport;
    private JButton employeeReport;
    private JButton categoryReport;
    private JButton departmentReport;

    private JTable table;
    private JScrollPane tableScrollPane;
    
    public ReportsDashboardPanel(){
        setLayout(null);
        setupTechnicianReportButton();
        setupEmployeeReportButton();
        setupCategoryReportButton();
        setupDepartmentIssueButton();
    }

    private void setupTechnicianReportButton() {
        technicianReport = new JButton("View Technician Workload Report");
        technicianReport.setBounds(0, 20, 240, 25);
        add(technicianReport);
    }

    private void setupEmployeeReportButton() {
        employeeReport = new JButton("View Employee Ticket Resolution Report");
        employeeReport.setBounds(250, 20, 270, 25);
        add(employeeReport);
    }

    private void setupCategoryReportButton() {
        categoryReport = new JButton("View Category (Issue Type) Report");
        categoryReport.setBounds(530, 20, 230, 25);
        add(categoryReport);
    }

    private void setupDepartmentIssueButton(){
        departmentReport = new JButton("View Department Issue Report");
        departmentReport.setBounds(770, 20, 220, 25);
        add(departmentReport);
    }

    private DefaultTableModel setupTable(String[] cols){
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0, 60, 1160, 550);
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    public void setupCategoryReportTable(List<CategoryReport> data){
        String[] cols = {"Category Name", "Year", "Number of Tickets", "Number of Resolved Tickets"};
        DefaultTableModel model = setupTable(cols);

        for (CategoryReport cr : data){
            Object[] row = new Object[] {cr.getCategory(), cr.getYear(), cr.getTotalSubmitted(), cr.getTotalResolved()};
            model.addRow(row);
        }
    }

    public JButton getTechnicianReportButton() {
        return technicianReport;
    }

    public JButton getEmployeeReportButton() {
        return employeeReport;
    }

    public JButton getCategoryReportButton() {
        return categoryReport;
    }

    public JButton getDepartmentReportButton() {
        return departmentReport;
    }
}
