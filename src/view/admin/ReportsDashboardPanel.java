package view.admin;

import javax.swing.*;

public class ReportsDashboardPanel extends JPanel{
    private JButton technicianReport;
    private JButton employeeReport;
    private JButton categoryReport;
    private JButton departmentReport;

    public ReportsDashboardPanel(){
        setLayout(null);
        setupTechnicianReportButton();
        setupEmployeeReportButton();
        setupCategoryReportButton();
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
}
