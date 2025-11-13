package controllers.admin;

import dao.CategoriesDAO;
import dao.DepartmentDAO;
import dao.EmployeesDAO;
import dao.ReportDAO;
import dao.TechniciansDAO;
import dao.TicketsDAO;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import reports.*;
import view.admin.AdminDashboardPanel;
import view.admin.ReportsDashboardPanel;

public class ReportsDashboardController {
    private ReportsDashboardPanel panel;    
    private ReportDAO reportDAO;
    private EmployeesDAO empDAO;
    private TechniciansDAO techDAO;
    private DepartmentDAO deptDAO;
    private TicketsDAO ticketsDAO;
    private CategoriesDAO categoriesDAO;

    public ReportsDashboardController(ReportDAO reportDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO, CategoriesDAO categoriesDAO, ReportsDashboardPanel reportsDashboardPanel) {
        this.reportDAO = reportDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.ticketsDAO = ticketsDAO;
        this.categoriesDAO = categoriesDAO;
        this.panel = reportsDashboardPanel;
    }

    public void init(AdminDashboardPanel adminPanel){
        adminPanel.showPanel(AdminDashboardPanel.VIEW_REPORTS);
    }

    public void initListeners(){
        initFilterListeners();

        panel.getCategoryReportButton().addActionListener(e->{
            List<CategoryReport> cr = reportDAO.generateCategoryReport();
            panel.setupCategoryReportTable(cr);
            panel.showCategoryReportFilters();
        });

        panel.getDepartmentReportButton().addActionListener(e->{
            List<DepartmentReport> dr = reportDAO.generateDepartmentReport();
            panel.setupDepartmentReportTable(dr);
            panel.showDepartmentReportFilters();
        });
    }

    private void initFilterListeners(){
        panel.getFilterByCategoryButton().addActionListener(e->{
            JComboBox<String> comboBox = new JComboBox<>(categoriesDAO.getAllCategoryNames().toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Category", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                String category = (String) comboBox.getSelectedItem();
                int categoryID = categoriesDAO.getCategoryIDByName(category);
                List<CategoryReport> cr = reportDAO.generateCategoryReportByCategory(categoryID);
                panel.setupCategoryReportTable(cr);
            }
        }); 

        panel.getFilterByYearButton().addActionListener(e->{
            JComboBox<Integer> comboBox = new JComboBox<>(ticketsDAO.getTicketYears().toArray(new Integer[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Year", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                Integer year = (Integer) comboBox.getSelectedItem();
                List<CategoryReport> cr = reportDAO.generateCategoryReportByYear(year);
                panel.setupCategoryReportTable(cr);
            }
        });

        panel.getFilterByDepartment().addActionListener(e -> {
            JComboBox<String> comboBox = new JComboBox<>(deptDAO.getAllDepartmentNames(true).toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Department", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                String dept = (String) comboBox.getSelectedItem();
                int deptID = deptDAO.getDepartmentIDByName(dept);
                List<DepartmentReport> dr = reportDAO.generateDeptReportByDept(deptID);
                panel.setupDepartmentReportTable(dr);
            }        
        });

        panel.getFilterByDepartmentYear().addActionListener(e -> {
            JComboBox<Integer> comboBox = new JComboBox<>(ticketsDAO.getTicketYears().toArray(new Integer[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Year", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                Integer year = (Integer) comboBox.getSelectedItem();
                List<DepartmentReport> dr = reportDAO.generateDepartmentReportByYear(year);
                panel.setupDepartmentReportTable(dr);
            }
        });
    }
}
