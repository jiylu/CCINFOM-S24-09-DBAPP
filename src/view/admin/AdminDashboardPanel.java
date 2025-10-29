package view.admin;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.*;

public class AdminDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String ADD_USER = "addUser";

    private JLabel titleLabel;
    private JButton addUserButton; 
    private JButton editUserButton; 
    private JButton deleteUserButton; 

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AddUserPanel addUserPanel;

    public AdminDashboardPanel(){
        setLayout(null);
        initPanels();
        setupCardLayout();
        initTitle();
        setupButtons();
    }

    private void initPanels(){
        addUserPanel = new AddUserPanel();
    }

    private void setupCardLayout(){
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(250,0,550,600);
        
        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(addUserPanel, ADD_USER);
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
        addUserButton = new JButton("Add User");
        addUserButton.setBounds(10, 80, 120, 40);
        add(addUserButton);

        editUserButton = new JButton("Edit User");
        editUserButton.setBounds(10, 130, 120, 40);
        add(editUserButton);

        deleteUserButton = new JButton("Delete User");
        deleteUserButton.setBounds(10, 180, 120, 40);
        add(deleteUserButton);
    }

    public JLabel getTitleLabel(){
        return titleLabel;
    }

    public JButton getAddUserButton(){
        return addUserButton;
    }

    public JButton getEditUserButton(){
        return editUserButton;
    }

    public JButton getDeleteUserButton(){
        return deleteUserButton;
    }

    public AddUserPanel getAddUserPanel(){
        return addUserPanel;
    }
}
