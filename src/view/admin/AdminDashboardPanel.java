package view.admin;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.*;

public class AdminDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String VIEW_USERS = "viewUsers";
    public final static String VIEW_REPORTS = "viewReports";
    public final static String VIEW_DEPT = "viewDept";
    public final static String VIEW_CATEGORIES = "viewCategories";

    private JLabel titleLabel;
    private JButton viewUsersButton; 
    private JButton manageDepartmentsButton;
    private JButton manageCategoriesButton;
    private JButton reportsButton; 

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AddUserPanel addUserPanel;
    private UserManagementPanel viewUsersPanel;
    private ManageCategoriesPanel manageCategoriesPanel;
    private ReportsDashboardPanel reportsDashboardPanel;
    private DepartmentManagementPanel deptManagementPanel;

    public AdminDashboardPanel(){
        setLayout(null);
        initPanels();
        setupCardLayout();
        initTitle();
        setupButtons();
    }

    private void initPanels(){
        viewUsersPanel = new UserManagementPanel();
        manageCategoriesPanel = new ManageCategoriesPanel();
        reportsDashboardPanel = new ReportsDashboardPanel();
        deptManagementPanel = new DepartmentManagementPanel();
    }

    private void setupCardLayout(){
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(10, 85, 1180, 710);
        
        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(viewUsersPanel, VIEW_USERS);
        cardPanel.add(manageCategoriesPanel, VIEW_CATEGORIES);
        cardPanel.add(deptManagementPanel, VIEW_DEPT);
        cardPanel.add(reportsDashboardPanel, VIEW_REPORTS);
        add(cardPanel);
    }

    public void showPanel(String name){
        cardLayout.show(cardPanel, name);
    }

    private void initTitle(){
        titleLabel = new JLabel("Welcome, User!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32)); 
        titleLabel.setBounds(10, 10, 500, 50);

        add(titleLabel);
    }

    private void setupButtons(){
        viewUsersButton = new JButton("Manage Users");
        viewUsersButton.setBounds(10, 60, 150, 25);
        add(viewUsersButton);

        manageDepartmentsButton = new JButton("Manage Departments");
        manageDepartmentsButton.setBounds(170, 60, 180, 25);
        add(manageDepartmentsButton);

        manageCategoriesButton = new JButton("Manage Categories");
        manageCategoriesButton.setBounds(360, 60, 150, 25);
        add(manageCategoriesButton);

        reportsButton = new JButton("View Reports");
        reportsButton.setBounds(520, 60, 150, 25);
        add(reportsButton);


    }

    public JLabel getTitleLabel(){
        return titleLabel;
    }

    public JButton getViewUsersButton(){
        return viewUsersButton;
    }

    public JButton getReportsButton(){
        return reportsButton;
    }

    public JButton getManageDepartmentsButton(){
        return manageDepartmentsButton;
    }

    public JButton getManageCategoriesButton(){
        return manageCategoriesButton;
    }

    public UserManagementPanel getViewUsersPanel(){
        return viewUsersPanel;
    }

    public ReportsDashboardPanel getReportsDashboardPanel(){
        return reportsDashboardPanel;
    }

    public DepartmentManagementPanel getDeptManagementPanel(){
        return deptManagementPanel;
    }

    public AddUserPanel getAddUserPanel(){
        return addUserPanel;
    }

    public ManageCategoriesPanel getManageCategoriesPanel() {
        return manageCategoriesPanel;
    }
}
