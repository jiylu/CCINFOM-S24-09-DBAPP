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
    private ViewUsersPanel viewUsersPanel;

    public AdminDashboardPanel(){
        setLayout(null);
        initPanels();
        setupCardLayout();
        initTitle();
        setupButtons();
    }

    private void initPanels(){
        viewUsersPanel = new ViewUsersPanel();
    }

    private void setupCardLayout(){
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(10, 75, 760, 460);
        
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
        viewUsersButton = new JButton("View Users");
        viewUsersButton.setBounds(10, 60, 100, 25);
        add(viewUsersButton);

        // editUserButton = new JButton("Edit User");
        // editUserButton.setBounds(10, 130, 120, 40);
        // add(editUserButton);

        // deleteUserButton = new JButton("Delete User");
        // deleteUserButton.setBounds(10, 180, 120, 40);
        // add(deleteUserButton);
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

    public ViewUsersPanel getViewUsersPanel(){
        return viewUsersPanel;
    }

    public AddUserPanel getAddUserPanel(){
        return addUserPanel;
    }
}
