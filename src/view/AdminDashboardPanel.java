package view;

import java.awt.Font;
import javax.swing.*;

public class AdminDashboardPanel extends JPanel {
    private JLabel titleLabel;
    private JButton addUserButton; 
    private JButton editUserButton; 
    private JButton deleteUserButton; 

    public AdminDashboardPanel(){
        setLayout(null);
        initTitle();
        setupButtons();
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
}
