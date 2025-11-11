package view.admin;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.*;

public class AdminDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String VIEW_USERS = "viewUsers";

    private JLabel titleLabel;
    private JButton viewUsersButton; 
    // private JButton editUserButton; 
    // private JButton deleteUserButton; 

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AddUserPanel addUserPanel;
    private UserManagementPanel viewUsersPanel;

    public AdminDashboardPanel(){
        setLayout(null);
        initPanels();
        setupCardLayout();
        initTitle();
        setupButtons();
    }

    private void initPanels(){
        viewUsersPanel = new UserManagementPanel();
    }

    private void setupCardLayout(){
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(10, 75, 1180, 710);
        
        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(viewUsersPanel, VIEW_USERS);
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
    }

    public JLabel getTitleLabel(){
        return titleLabel;
    }

    public JButton getViewUsersButton(){
        return viewUsersButton;
    }

    // public JButton getEditUserButton(){
    //     return editUserButton;
    // }

    // public JButton getDeleteUserButton(){
    //     return deleteUserButton;
    // }

    public UserManagementPanel getViewUsersPanel(){
        return viewUsersPanel;
    }

    public AddUserPanel getAddUserPanel(){
        return addUserPanel;
    }
}
