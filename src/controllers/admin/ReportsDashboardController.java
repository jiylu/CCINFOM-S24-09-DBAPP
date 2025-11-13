package controllers.admin;

import java.util.List;

import dao.ReportDAO;
import reports.CategoryReport;
import view.admin.AdminDashboardPanel;
import view.admin.ReportsDashboardPanel;

public class ReportsDashboardController {
    private ReportsDashboardPanel panel;    
    private ReportDAO reportDAO;

    public ReportsDashboardController(ReportDAO reportDAO, ReportsDashboardPanel reportsDashboardPanel){
        this.panel = reportsDashboardPanel;
        this.reportDAO = reportDAO;
    }

    public void init(AdminDashboardPanel adminPanel){
        adminPanel.showPanel(AdminDashboardPanel.VIEW_REPORTS);
    }

    public void initListeners(){
        panel.getCategoryReportButton().addActionListener(e->{
            List<CategoryReport> cr = reportDAO.generateCategoryReport();
            panel.setupCategoryReportTable(cr);
        });
    }
}
