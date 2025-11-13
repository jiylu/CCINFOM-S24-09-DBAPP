package controllers.admin;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.ReportDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import reports.CategoryReport;
import view.admin.AdminDashboardPanel;
import view.admin.ReportsDashboardPanel;

public class ReportsDashboardController {
    private ReportsDashboardPanel panel;    
    private ReportDAO reportDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private TicketsDAO ticketsDAO;

    // filters
    private Integer selectedYear;

    public ReportsDashboardController(ReportDAO reportDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO, ReportsDashboardPanel reportsDashboardPanel) {
        this.reportDAO = reportDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.ticketsDAO = ticketsDAO;
        this.panel = reportsDashboardPanel;
    }

    public void init(AdminDashboardPanel adminPanel){
        adminPanel.showPanel(AdminDashboardPanel.VIEW_REPORTS);
    }

    public void initListeners(){
        // panel.getFilterByCategoryButton().addActionListener(e->{

        // });

        panel.getFilterByYeaButton().addActionListener(e->{
            initFilterYearBehavior();
        });

        panel.getCategoryReportButton().addActionListener(e->{
            List<CategoryReport> cr = reportDAO.generateCategoryReport();
            panel.setupCategoryReportTable(cr);
            panel.showCategoryReportFilters();
        });
    }

    private void initFilterYearBehavior(){
        Integer[] years = ticketsDAO.getTicketYears().toArray(new Integer[0]);
        JDialog dialog = new JDialog();
        dialog.setTitle("Select Year");
        dialog.setModal(true); 
        dialog.setSize(250, 150);
        dialog.setLocationRelativeTo(null);
        JComboBox<Integer> yearCombo = new JComboBox<>(years);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(ev -> {
            selectedYear = (Integer) yearCombo.getSelectedItem();
            dialog.dispose();
        });

        JPanel content = new JPanel();
        content.add(new JLabel("Choose a year:"));
        content.add(yearCombo);
        content.add(okButton);

        dialog.add(content);
        dialog.setVisible(true);
    }
}
