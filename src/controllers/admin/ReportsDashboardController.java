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
import javax.swing.JTable;
import reports.*;
import util.PDFExport;
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
    
    // para sa filename ng pdf
    private String report;
    private String filter;
    private String pdfTitle;

    public ReportsDashboardController(ReportDAO reportDAO, EmployeesDAO empDAO, TechniciansDAO techDAO, DepartmentDAO deptDAO, TicketsDAO ticketsDAO, CategoriesDAO categoriesDAO, ReportsDashboardPanel reportsDashboardPanel) {
        this.reportDAO = reportDAO;
        this.empDAO = empDAO;
        this.techDAO = techDAO;
        this.deptDAO = deptDAO;
        this.ticketsDAO = ticketsDAO;
        this.categoriesDAO = categoriesDAO;
        this.panel = reportsDashboardPanel;
        this.pdfTitle = null;
        this.report = null;
        this.filter = null;
    }
    
    public void init(AdminDashboardPanel adminPanel){
        adminPanel.showPanel(AdminDashboardPanel.VIEW_REPORTS);
    }

    public void initListeners(){
        initFilterListeners();

        panel.getCategoryReportButton().addActionListener(e->{
            report = "category_report";
            pdfTitle = "Category Report";
            filter = null;
            List<CategoryReport> cr = reportDAO.generateCategoryReport();
            panel.setupCategoryReportTable(cr);
            panel.showCategoryReportFilters();
        });

        panel.getDepartmentReportButton().addActionListener(e->{
            report = "department_report";
            pdfTitle = "Department Report";
            filter = null;
            List<DepartmentReport> dr = reportDAO.generateDepartmentReport();
            panel.setupDepartmentReportTable(dr);
            panel.showDepartmentReportFilters();
        });

        panel.getTechnicianReportButton().addActionListener(e->{
            report = "technician_report";
            pdfTitle = "Technician Report";
            filter = null;
            panel.showTechWorkloadReportFilters();
            List<TechWorkloadReport> techWR = reportDAO.generateTechWorkloadReport();
            panel.setupTechWorkloadReportTable (techWR);
        });

        panel.getEmployeeReportButton().addActionListener(e -> {
            report = "employee_report";
            pdfTitle = "Employee Report";
            filter = null;
            panel.showEmpTicketResReportFilters();
            List<EmployeeTicketResolutionReport> empWR = reportDAO.generateEmpTicketResReport();
            panel.setupEmpTicketResReportTable(empWR);
        });

        panel.getDownloadButton().addActionListener(e->{
            JTable table = panel.getTable();

            if (table == null){
                JOptionPane.showMessageDialog(null, "No table found");
                return;
            }

            String filename = "reports/" + report;

            if (filter != null){
                filename = filename.concat(filter);
            }

            PDFExport.exportTableToPDF(table, filename + ".pdf", pdfTitle);
        });
    }

    private void initFilterListeners(){
        panel.getClearFilterButton().addActionListener(e -> {
            filter = null;
            
            panel.clearLabels(); //for technician report labels
            panel.clearEmpLabels(); // for employee report labels

            if (panel.getFilterByCategoryButton().isVisible()) {
                List<CategoryReport> cr = reportDAO.generateCategoryReport();
                panel.setupCategoryReportTable(cr);
            } else if (panel.getFilterByDepartment().isVisible()) {
                List<DepartmentReport> dr = reportDAO.generateDepartmentReport();
                panel.setupDepartmentReportTable(dr);
            } else if (panel.getFilterByTechButton().isVisible()) {
                List<TechWorkloadReport> TechWR = reportDAO.generateTechWorkloadReport();
                panel.setupTechWorkloadReportTable(TechWR);
            } else if (panel.getFilterByEmployeeButton().isVisible()) {
                List<EmployeeTicketResolutionReport> emp = reportDAO.generateEmpTicketResReport();
                panel.setupEmpTicketResReportTable(emp);
            }
            panel.getClearFilterButton().setVisible(false);
        });

        panel.getFilterByCategoryButton().addActionListener(e->{
            JComboBox<String> comboBox = new JComboBox<>(categoriesDAO.getAllCategoryNames().toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Category", JOptionPane.OK_CANCEL_OPTION);
            
            if (res == JOptionPane.OK_OPTION){
                String category = (String) comboBox.getSelectedItem();
                filter = "_filter_category_" + category;
                int categoryID = categoriesDAO.getCategoryIDByName(category);
                List<CategoryReport> cr = reportDAO.generateCategoryReportByCategory(categoryID);
                panel.setupCategoryReportTable(cr);
            }
            panel.getClearFilterButton().setVisible(true);
        }); 

        panel.getFilterByYearButton().addActionListener(e->{
            JComboBox<Integer> comboBox = new JComboBox<>(ticketsDAO.getTicketYears().toArray(new Integer[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Year", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                Integer year = (Integer) comboBox.getSelectedItem();
                filter = "_filter_year_ " + year.toString();
                List<CategoryReport> cr = reportDAO.generateCategoryReportByYear(year);
                panel.setupCategoryReportTable(cr);
            }
            panel.getClearFilterButton().setVisible(true);
        });

        panel.getFilterByDepartment().addActionListener(e -> {
            JComboBox<String> comboBox = new JComboBox<>(deptDAO.getAllDepartmentNames(true).toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Department", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                String dept = (String) comboBox.getSelectedItem();
                filter = "_filter_department_" + dept;
                int deptID = deptDAO.getDepartmentIDByName(dept);
                List<DepartmentReport> dr = reportDAO.generateDeptReportByDept(deptID);
                panel.setupDepartmentReportTable(dr);
            }
            panel.getClearFilterButton().setVisible(true);
        });

        panel.getFilterByDepartmentYear().addActionListener(e -> {
            JComboBox<Integer> comboBox = new JComboBox<>(ticketsDAO.getTicketYears().toArray(new Integer[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Year", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                Integer year = (Integer) comboBox.getSelectedItem();
                filter = "_filter_year_" + year.toString().trim();
                List<DepartmentReport> dr = reportDAO.generateDepartmentReportByYear(year);
                panel.setupDepartmentReportTable(dr);
            }
            panel.getClearFilterButton().setVisible(true);
        });

        panel.getFilterByTechButton().addActionListener(e -> {
            JComboBox<String> comboBox = new JComboBox<>(techDAO.getAllTechnicianNames().toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Technician", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                String techName = (String) comboBox.getSelectedItem();
                Integer techID = techDAO.getTechnicianIDByName(techName);
                filter = "_filter_techID_" + techID.toString().trim();
                List<TechWorkloadReport> list = reportDAO.generateTechYearsSummary(techID);
                panel.setupTechnicianSummaryTable(list, techName);
            }

            panel.getClearFilterButton().setVisible(true);
        });

        panel.getFilterByTechYearButton().addActionListener(e -> {
            JComboBox<Integer> comboBox = new JComboBox<>(ticketsDAO.getTicketYears().toArray(new Integer[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Year", JOptionPane.OK_CANCEL_OPTION);

            if (res == JOptionPane.OK_OPTION){
                Integer year = (Integer) comboBox.getSelectedItem();
                List<TechWorkloadReport> list = reportDAO.generateYearTechsSummary(year);
                filter = "_filter_year_" + year.toString().trim();
                panel.setupYearSummaryTable(list, year);
            }

            panel.getClearFilterButton().setVisible(true);
        });

        panel.getFilterByEmployeeButton().addActionListener(e -> {
            JComboBox<String> comboBox = new JComboBox<>(empDAO.getAllEmployeeNames().toArray(new String[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Employee", JOptionPane.OK_CANCEL_OPTION);

            if(res == JOptionPane.OK_OPTION) {
                String empName = (String) comboBox.getSelectedItem();
                Integer empID = empDAO.getEmployeeIDByName(empName);
                filter = "_filter_empID_" + empID.toString().trim() + "_" + empName;
                List<EmployeeTicketResolutionReport> list = reportDAO.generateEmpYearsSummary(empID);
                panel.setupEmpSummaryTable(list, empName);
            }

            panel.getClearFilterButton().setVisible(true);
        });

        panel.getFilterByEmployeeYearButton().addActionListener(e -> {
            JComboBox<Integer> comboBox = new JComboBox<>(ticketsDAO.getTicketYears().toArray(new Integer[0]));
            int res = JOptionPane.showConfirmDialog(null, comboBox, "Select Year", JOptionPane.OK_CANCEL_OPTION);

            if(res == JOptionPane.OK_OPTION) {
                Integer year = (Integer) comboBox.getSelectedItem();
                List<EmployeeTicketResolutionReport> list = reportDAO.generateYearEmpsSummary(year);
                filter = "_filter_year_" + year.toString().trim();
                panel.setupEmpYearSummaryTable(list, year);
            }
        });
    }
}