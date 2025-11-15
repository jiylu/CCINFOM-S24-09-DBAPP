package view.admin;


import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import reports.*;

public class ReportsDashboardPanel extends JPanel{
    private JButton technicianReport;
    private JButton employeeReport;
    private JButton categoryReport;
    private JButton departmentReport;

    private JButton clearFilter;
    private JButton filterByCategory;
    private JButton filterByYear;
    private JButton filterByDepartment;
    private JButton filterByDepartmentYear;
    private JButton filterByTech;
    private JButton filterByTechYear;
    private JLabel technicianLabel;
    private JLabel yearLabel;

    private JButton filterByEmployee;
    private JButton filterByEmployeeYear;
    private JLabel employeeLabel;
    private JLabel empYearLabel;

    private JButton downloadButton;
    
    private JTable table;
    private JScrollPane tableScrollPane;
    
    public ReportsDashboardPanel(){
        setLayout(null);
        setupMainButtons();
        setupFilterButtons();
    }

    private void setupMainButtons(){
        setupTechnicianReportButton();
        setupEmployeeReportButton();
        setupCategoryReportButton();
        setupDepartmentIssueButton();
        setupDownloadButton();
    }

    private void setupFilterButtons(){
        setupClearFilterButton();
        setupFilterByCategoryButton();
        setupFilterByCategoryYearButton();
        setupFilterByDepartmentButton();
        setupFilterByDepartmentYearButton();
        setupFilterByTechnicianButton();
        setupFilterbyTechnicianYearButton();
        setupFilterByEmployeeButton();
        setupFilterByEmpYearButton();
    }

    //----------------Report Buttons-------------------//
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

    private void setupClearFilterButton(){
        clearFilter = new JButton("Clear Filter");
        clearFilter.setBounds(320, 55, 150, 25);
        clearFilter.setVisible(false);
        add(clearFilter);
    }

    //----------------- Category Filters -----------------//
    private void setupFilterByCategoryButton(){
        filterByCategory = new JButton("Filter by Category");
        filterByCategory.setBounds(0, 55, 150, 25);
        filterByCategory.setVisible(false);
        add(filterByCategory);
    }

    private void setupFilterByCategoryYearButton(){
        filterByYear = new JButton("Filter by Year");
        filterByYear.setBounds(160, 55, 150, 25);
        filterByYear.setVisible(false);
        add(filterByYear);
    }

    //---------------------- Department Filters ---------------------//
    private void setupFilterByDepartmentButton(){
        filterByDepartment = new JButton("Filter by Department");
        filterByDepartment.setBounds(0, 55, 150, 25);
        filterByDepartment.setVisible(false);
        add(filterByDepartment);
    }

    private void setupFilterByDepartmentYearButton(){
        filterByDepartmentYear = new JButton("Filter by Year");
        filterByDepartmentYear.setBounds(160, 55, 150, 25);
        filterByDepartmentYear.setVisible(false);
        add(filterByDepartmentYear);
    }

    //----------------- Technician Filters ----------------------// 
    private void setupFilterByTechnicianButton(){
        filterByTech = new JButton("Filter by Technician");
        filterByTech.setBounds(0, 55, 150, 25);
        filterByTech.setVisible(false);
        add(filterByTech);
    }

    private void setupFilterbyTechnicianYearButton(){
        filterByTechYear = new JButton("Filter by Year");
        filterByTechYear.setBounds(160, 55, 150, 25);
        filterByTechYear.setVisible(false);
        add(filterByTechYear);
    }

    //-------------------- Employee Filters --------------------//
    private void setupFilterByEmployeeButton() {
        filterByEmployee = new JButton("Filter by Employee");
        filterByEmployee.setBounds(0, 55, 150, 25);
        filterByEmployee.setVisible(false);
        add(filterByEmployee);
    }

    private void setupFilterByEmpYearButton() {
        filterByEmployeeYear = new JButton("Filter by Year");
        filterByEmployeeYear.setBounds(160, 55, 150, 25);
        filterByEmployeeYear.setVisible(false);
        add(filterByEmployeeYear);
    }

    private void setupDownloadButton(){
        downloadButton = new JButton("Download");
        downloadButton.setBounds(0, 600, 150, 25);
        add(downloadButton);
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
        tableScrollPane.setBounds(0, 90, 1160, 500);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    private DefaultTableModel setupFilteredTechTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        clearLabels();
        
        DefaultTableModel filteredTechModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(filteredTechModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0, 130, 1160, 400);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScrollPane);

        revalidate();
        repaint();

        return filteredTechModel;
    }

    private DefaultTableModel setupFilteredEmpTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        clearEmpLabels();

        DefaultTableModel filteredEmpModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(filteredEmpModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(0, 130, 1160, 400);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(tableScrollPane);

        revalidate();
        repaint();

        return filteredEmpModel;
    }
    

    // ---------------------- Report Tables ---------------------------- //
    public void setupCategoryReportTable(List<CategoryReport> data){
        String[] cols = {"Category Name", "Year", "Number of Tickets", "Number of Resolved Tickets"};
        DefaultTableModel model = setupTable(cols);

        clearLabels();
        
        for (CategoryReport cr : data){
            Object[] row = new Object[] {cr.getCategory(), cr.getYear(), cr.getTotalSubmitted(), cr.getTotalResolved()};
            model.addRow(row);
        }
    }

    public void setupDepartmentReportTable(List<DepartmentReport> data){
        String[] cols = {"Department Name", "Year", "Category Name", "Number of Tickets"};
        DefaultTableModel model = setupTable(cols);

        clearLabels();

        for (DepartmentReport dr : data){
            Object[] row = new Object[] {dr.getDepartment(), dr.getYear(), dr.getCategory(), dr.getTotalTickets()};
            model.addRow(row);
        }
    }

    public void setupTechWorkloadReportTable(List<TechWorkloadReport> data){
        String[] cols = {"Year", "Technician Name", "Total Assigned Tickets", "Total Tickets Resolved", "Average Resolution Time"};
        DefaultTableModel model = setupTable(cols);

        clearLabels();

        for (TechWorkloadReport techWR : data){
            Object[] row = new Object[] {
                techWR.getYear(),
                techWR.getTechnician(),
                techWR.getAssignedTickets(),
                techWR.getResolvedTickets(),
                techWR.getAverageTime()};
            model.addRow(row);
        }
    }

    public void setupEmpTicketResReportTable(List<EmployeeTicketResolutionReport> data) {
        String[] cols = {"Year", "Employee Name", "Total Submitted Tickets", "Total Tickets Resolved", "Total Cancelled Tickets"};
        DefaultTableModel model = setupTable(cols);

        for (EmployeeTicketResolutionReport empWR : data) {
            Object[] row = new Object[] {
                    empWR.getYear(),
                    empWR.getEmployee(),
                    empWR.getSubmittedTickets(),
                    empWR.getResolvedTickets(),
                    empWR.getCancelledTickets(),
            };
            model.addRow(row);
        }
    }

    public void setupTechnicianSummaryTable(List<TechWorkloadReport> data, String techNameString) {
        String[] columns = {"Year", "Total Assigned", "Total Resolved", "Average Resolution Time"};

        DefaultTableModel model = setupFilteredTechTable(columns); 

        clearLabels();
        this.technicianLabel = new JLabel("Technician: " + techNameString);
        this.technicianLabel.setBounds(0, 100, 400, 20); 
        this.technicianLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(technicianLabel);

        for (TechWorkloadReport TWReport : data) {
            Object[] row = new Object[] {
                TWReport.getYear(),
                TWReport.getAssignedTickets(),
                TWReport.getResolvedTickets(),
                TWReport.getAverageTime()};
            model.addRow(row);
        }
    }

    public void setupEmpSummaryTable(List<EmployeeTicketResolutionReport> data, String empName) {
        String[] columns = {"Year", "Total Submitted", "Total Resolved", "Total Cancelled"};

        DefaultTableModel model = setupFilteredEmpTable(columns);

        clearEmpLabels();
        this.employeeLabel = new JLabel("Employee: " + empName);
        this.employeeLabel.setBounds(0, 100, 400, 20);
        this.employeeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(employeeLabel);

        for (EmployeeTicketResolutionReport report : data) {
            Object[] row = new Object[] {
                    report.getYear(),
                    report.getSubmittedTickets(),
                    report.getResolvedTickets(),
                    report.getCancelledTickets()
            };

            model.addRow(row);
        }
    }


    public void setupYearSummaryTable(List<TechWorkloadReport> data, int year) {
        String[] columns = {"Technician", "Total Assigned", "Total Resolved", "Average Resolution Time"};
        
        DefaultTableModel model = setupFilteredTechTable(columns); 
        
        clearLabels();
        this.yearLabel = new JLabel("Year: " + year);
        this.yearLabel.setBounds(0, 100, 400, 20);
        this.yearLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(yearLabel);

        for (TechWorkloadReport TWReport : data) {
            model.addRow(new Object[]{
                TWReport.getTechnician(),
                TWReport.getAssignedTickets(),
                TWReport.getResolvedTickets(),
                TWReport.getAverageTime()
            });
        }
    }

    public void setupEmpYearSummaryTable(List<EmployeeTicketResolutionReport> data, int year) {
        String[] columns = {"Employee", "Total Assigned", "Total Resolved", "Total Cancelled"};

        DefaultTableModel model = setupFilteredEmpTable(columns);

        clearEmpLabels();
        this.empYearLabel = new JLabel("Year: " + year);
        this.empYearLabel.setBounds(0, 100, 400, 20);
        this.empYearLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(empYearLabel);

        for (EmployeeTicketResolutionReport report : data) {
            model.addRow(new Object[]{
                    report.getEmployee(),
                    report.getSubmittedTickets(),
                    report.getResolvedTickets(),
                    report.getCancelledTickets()
            });
        }
    }

   public void clearLabels() {
        if (technicianLabel != null) {
            remove(technicianLabel);
            technicianLabel = null;
        }
        if (yearLabel != null) {
            remove(yearLabel);
            yearLabel = null;
        }
        
        revalidate(); 
        repaint();
    }

    public void clearEmpLabels() {
        if (employeeLabel != null) {
            remove(employeeLabel);
            employeeLabel = null;
        }

        if(empYearLabel != null) {
            remove(empYearLabel);
            empYearLabel = null;
        }

        revalidate();
        repaint();
    }

    private void resetFilters(){
        clearFilter.setVisible(false);
        filterByCategory.setVisible(false);
        filterByYear.setVisible(false);
        filterByDepartment.setVisible(false);
        filterByDepartmentYear.setVisible(false);
        filterByTech.setVisible(false);
        filterByTechYear.setVisible(false);
        filterByEmployee.setVisible(false);
        filterByEmployeeYear.setVisible(false);
    }

    public void showCategoryReportFilters(){
        resetFilters();
        filterByCategory.setVisible(true);
        filterByYear.setVisible(true);
    }

    public void showDepartmentReportFilters(){
        resetFilters();
        filterByDepartment.setVisible(true);
        filterByDepartmentYear.setVisible(true);
    }

    public void showTechWorkloadReportFilters(){
        resetFilters();
        filterByTech.setVisible(true);
        filterByTechYear.setVisible(true);
    }

    public void showEmpTicketResReportFilters() {
        resetFilters();
        filterByEmployee.setVisible(true);
        filterByEmployeeYear.setVisible(true);
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

    public JButton getClearFilterButton() {
        return clearFilter;
    }

    public JButton getFilterByCategoryButton(){
        return filterByCategory;
    }

    public JButton getFilterByYearButton(){
        return filterByYear;
    }

    public JButton getFilterByDepartment() {
        return filterByDepartment;
    }

    public JButton getFilterByDepartmentYear() {
        return filterByDepartmentYear;
    }

    public JButton getDownloadButton(){
        return downloadButton;
    }

    public JTable getTable(){
        return table;
    }
    
    public JButton getFilterByTechButton() {
        return filterByTech;
    }

    public JButton getFilterByTechYearButton() {
        return filterByTechYear;
    }

    public JButton getFilterByEmployeeButton() {
        return filterByEmployee;
    }

    public JButton getFilterByEmployeeYearButton() {
        return filterByEmployeeYear;
    }
}
