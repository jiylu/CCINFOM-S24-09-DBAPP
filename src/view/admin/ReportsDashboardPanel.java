package view.admin;


import java.awt.*;
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
        setBackground(new Color(245, 245, 245)); // Light gray background
        setupMainButtons();
        setupFilterButtons();
    }

    private void setupMainButtons(){
        technicianReport = createStyledButton("View Technician Workload Report", 10, 10, 270, 35);
        employeeReport = createStyledButton("View Employee Ticket Resolution Report", 285, 10, 320, 35);
        categoryReport = createStyledButton("View Category (Issue Type) Report", 610, 10, 275, 35);
        departmentReport = createStyledButton("View Department Issue Report", 890, 10, 260, 35);
        downloadButton = createStyledButton("Download", 1050, 50, 100, 35, new Color(0, 153, 0));

        add(technicianReport);
        add(employeeReport);
        add(categoryReport);
        add(departmentReport);
        add(downloadButton);
    }

    private void setupFilterButtons(){
        clearFilter = createStyledButton("Clear Filter", 335, 55, 140, 30, new Color(204, 0, 0));
        filterByCategory = createStyledButton("Filter by Category", 10, 55, 170, 30);
        filterByYear = createStyledButton("Filter by Year", 185, 55, 140, 30);
        filterByDepartment = createStyledButton("Filter by Department", 10, 55, 175, 30);
        filterByDepartmentYear = createStyledButton("Filter by Year", 190, 55, 140, 30);
        filterByTech = createStyledButton("Filter by Technician", 10, 55, 170, 30);
        filterByTechYear = createStyledButton("Filter by Year", 185, 55, 140, 30);
        filterByEmployee = createStyledButton("Filter by Employee", 10, 55, 170, 30);
        filterByEmployeeYear = createStyledButton("Filter by Year", 185, 55, 140, 30);

        clearFilter.setVisible(false);
        filterByCategory.setVisible(false);
        filterByYear.setVisible(false);
        filterByDepartment.setVisible(false);
        filterByDepartmentYear.setVisible(false);
        filterByTech.setVisible(false);
        filterByTechYear.setVisible(false);
        filterByEmployee.setVisible(false);
        filterByEmployeeYear.setVisible(false);

        add(clearFilter);
        add(filterByCategory);
        add(filterByYear);
        add(filterByDepartment);
        add(filterByDepartmentYear);
        add(filterByTech);
        add(filterByTechYear);
        add(filterByEmployee);
        add(filterByEmployeeYear);
    }

    private JButton createStyledButton(String text, int x, int y, int width, int height){
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        // hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 80, 160));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });
        return button;
    }
    // For Download and Cancel
    private JButton createStyledButton(String text, int x, int y, int width, int height, Color bgColor){
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }


    private DefaultTableModel setupTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0){
            @Override
            public boolean isCellEditable(int row, int column){ return false; }
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
        tableScrollPane.setBounds(20, 90, 1120, 500);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    private DefaultTableModel setupFilteredTechTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0){
            @Override
            public boolean isCellEditable(int row, int column){ return false; }
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
        tableScrollPane.setBounds(20, 90, 1120, 500); // proper spacing & alignment
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    private DefaultTableModel setupFilteredEmpTable(String[] cols){
        if (tableScrollPane != null){
            remove(tableScrollPane);
        }

        DefaultTableModel model = new DefaultTableModel(cols, 0){
            @Override
            public boolean isCellEditable(int row, int column){ return false; }
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
        tableScrollPane.setBounds(20, 90, 1120, 500); // proper spacing & alignment
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(tableScrollPane);

        revalidate();
        repaint();

        return model;
    }

    // ---------------------- Report Tables ---------------------------- //
    public void setupCategoryReportTable(List<CategoryReport> data){
        String[] cols = {"Category Name", "Year", "Number of Tickets", "Number of Resolved Tickets"};
        DefaultTableModel model = setupTable(cols);

        clearAllLabels();

        for (CategoryReport cr : data){
            Object[] row = new Object[] {cr.getCategory(), cr.getYear(), cr.getTotalSubmitted(), cr.getTotalResolved()};
            model.addRow(row);
        }
    }

    public void setupDepartmentReportTable(List<DepartmentReport> data){
        String[] cols = {"Department Name", "Year", "Category Name", "Number of Tickets"};
        DefaultTableModel model = setupTable(cols);

        clearAllLabels();

        for (DepartmentReport dr : data){
            Object[] row = new Object[] {dr.getDepartment(), dr.getYear(), dr.getCategory(), dr.getTotalTickets()};
            model.addRow(row);
        }
    }

    public void setupTechWorkloadReportTable(List<TechWorkloadReport> data){
        String[] cols = {"Year", "Technician Name", "Total Assigned Tickets", "Total Tickets Resolved", "Average Resolution Time (hours)"};
        DefaultTableModel model = setupTable(cols);

        clearAllLabels();

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

        clearAllLabels();

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
        String[] columns = {"Year", "Total Assigned", "Total Resolved", "Average Resolution Time (hours)"};

        DefaultTableModel model = setupFilteredTechTable(columns);

        clearAllLabels();

        this.technicianLabel = new JLabel("Technician: " + techNameString);
        this.technicianLabel.setBounds(20, 100, 400, 20);
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

        clearAllLabels();

        this.employeeLabel = new JLabel("Employee: " + empName);
        this.employeeLabel.setBounds(20, 100, 400, 20);
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
        String[] columns = {"Technician", "Total Assigned", "Total Resolved", "Average Resolution Time (hours)"};

        DefaultTableModel model = setupFilteredTechTable(columns);

        clearAllLabels();

        this.yearLabel = new JLabel("Year: " + year);
        this.yearLabel.setBounds(20, 100, 400, 20);
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

        clearAllLabels();
        this.empYearLabel = new JLabel("Year: " + year);
        this.empYearLabel.setBounds(20, 100, 400, 20);
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

    public void clearAllLabels() {
            if (technicianLabel != null) {
                remove(technicianLabel);
                technicianLabel = null;
            }

            if (yearLabel != null) {
                remove(yearLabel);
                yearLabel = null;
            }

            if (employeeLabel != null) {
                remove(employeeLabel);
                employeeLabel = null;
            }

            if (empYearLabel != null) {
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
