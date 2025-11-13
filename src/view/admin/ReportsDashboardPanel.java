package view.admin;


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
    }

    private void setupFilterButtons(){
        setupClearFilterButton();
        setupFilterByCategoryButton();
        setupFilterByCategoryYearButton();
        setupFilterByDepartmentButton();
        setupFilterByDepartmentYearButton();
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

    private void setupClearFilterButton(){
        clearFilter = new JButton("Clear Filter");
        clearFilter.setBounds(320, 55, 150, 25);
        clearFilter.setVisible(false);
        add(clearFilter);
    }

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
        tableScrollPane.setBounds(0, 90, 1160, 550);
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

        public void setupDepartmentReportTable(List<DepartmentReport> data){
        String[] cols = {"Department Name", "Year", "Category Name", "Number of Tickets"};
        DefaultTableModel model = setupTable(cols);

        for (DepartmentReport dr : data){
            Object[] row = new Object[] {dr.getDepartment(), dr.getYear(), dr.getCategory(), dr.getTotalTickets()};
            model.addRow(row);
        }
    }

    private void resetFilters(){
        clearFilter.setVisible(false);
        filterByCategory.setVisible(false);
        filterByYear.setVisible(false);
        filterByDepartment.setVisible(false);
        filterByDepartmentYear.setVisible(false);
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
}
