package view.admin;

import javax.swing.*;
import java.awt.*;

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
    private AddUserPanel addUserPanel; // kept for controller
    private UserManagementPanel viewUsersPanel;
    private DepartmentManagementPanel deptManagementPanel;
    private ManageCategoriesPanel manageCategoriesPanel;
    private ReportsDashboardPanel reportsDashboardPanel;

    public AdminDashboardPanel() {
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        initHeaderBar();
        setupButtons();
        initPanels();
        setupCardLayout();
    }

    // Dark green header bar
    private void initHeaderBar() {
        JPanel headerBar = new JPanel();
        headerBar.setBackground(new Color(0, 102, 0)); // dark green
        headerBar.setBounds(0, 0, 1500, 70);
        headerBar.setLayout(null);

        titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 10, 600, 50);
        headerBar.add(titleLabel);

        add(headerBar);
    }

    // Top buttons (modern green design)
    private void setupButtons() {
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        viewUsersButton = createGreenButton("Manage Users", 10, 80, 180, 40);
        add(viewUsersButton);

        manageDepartmentsButton = createGreenButton("Manage Departments", 200, 80, 200, 40);
        add(manageDepartmentsButton);

        manageCategoriesButton = createGreenButton("Manage Categories", 420, 80, 200, 40);
        add(manageCategoriesButton);

        reportsButton = createGreenButton("View Reports", 640, 80, 180, 40);
        add(reportsButton);
    }

    private JButton createGreenButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 153, 0));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Rounded effect
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 153, 0)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 115, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 0));
            }
        });

        return button;
    }

    private void initPanels() {
        viewUsersPanel = new UserManagementPanel();
        deptManagementPanel = new DepartmentManagementPanel();
        manageCategoriesPanel = new ManageCategoriesPanel();
        reportsDashboardPanel = new ReportsDashboardPanel();
        // addUserPanel is not initialized here, controller will handle it
    }

    private void setupCardLayout() {
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(10, 140, 1180, 710);

        cardPanel.setBackground(Color.WHITE);
        viewUsersPanel.setBackground(Color.WHITE);
        deptManagementPanel.setBackground(Color.WHITE);
        manageCategoriesPanel.setBackground(Color.WHITE);
        reportsDashboardPanel.setBackground(Color.WHITE);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(viewUsersPanel, VIEW_USERS);
        cardPanel.add(deptManagementPanel, VIEW_DEPT);
        cardPanel.add(manageCategoriesPanel, VIEW_CATEGORIES);
        cardPanel.add(reportsDashboardPanel, VIEW_REPORTS);

        add(cardPanel);
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    // Getters
    public JLabel getTitleLabel(){
        return titleLabel;
    }

    public JButton getViewUsersButton() {
        return viewUsersButton;
    }

    public JButton getManageDepartmentsButton() {
        return manageDepartmentsButton;
    }

    public JButton getManageCategoriesButton() {
        return manageCategoriesButton;
    }

    public JButton getReportsButton() {
        return reportsButton;
    }

    public UserManagementPanel getViewUsersPanel() {
        return viewUsersPanel;
    }

    public DepartmentManagementPanel getDeptManagementPanel() {
        return deptManagementPanel;
    }

    public ManageCategoriesPanel getManageCategoriesPanel() {
        return manageCategoriesPanel;
    }

    public ReportsDashboardPanel getReportsDashboardPanel() {
        return reportsDashboardPanel;
    }

    public AddUserPanel getAddUserPanel() {
        return addUserPanel;
    }

    public void setAdminName(String firstName, String lastName) {
        titleLabel.setText("Welcome, Admin " + firstName + " " + lastName + "!");
    }
}
