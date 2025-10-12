package view;

import javax.swing.*;

public class Frame extends JFrame{
    private JButton employeeLogin;
    private JButton technicianLogin;

    public Frame(){
        super("IT Helpdesk Ticketing System");
        setupFrameComponents();
        setupButtons();
    }

    private void setupFrameComponents(){
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);
    }

    private void setupButtons(){
        employeeLogin = new JButton("Employee Login");
        employeeLogin.setBounds(300, 200, 200, 50); 

        technicianLogin = new JButton("Technician Login");
        technicianLogin.setBounds(300, 260, 200, 50);

        add(employeeLogin);
        add(technicianLogin);
    }
}
