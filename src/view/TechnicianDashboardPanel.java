package view;

import javax.swing.*;
import java.awt.*;

public class TechnicianDashboardPanel extends JPanel {
    private JLabel titleLabel;
    private JButton resolveTicketButton;
    private JButton reassignTicketButton;
    private JButton cancelTicketButton;
    private JButton viewTicketHistoryButton;

    public TechnicianDashboardPanel(){
        setLayout(null);
        initTitle();
        setupButtons();
    }

    private void initTitle(){
        titleLabel = new JLabel("Welcome, Technician!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(10, 10, 500, 50);

        add(titleLabel);
    }

    private void setupButtons(){
        resolveTicketButton = new JButton("Select Ticket to Resolve");
        resolveTicketButton.setBounds(10, 80, 300, 40);
        add(resolveTicketButton);

        reassignTicketButton = new JButton("Reassign Ticket to Other Technician");
        reassignTicketButton.setBounds(10, 130, 300, 40);
        add(reassignTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setBounds(10, 180, 300, 40);
        add(cancelTicketButton);

        viewTicketHistoryButton = new JButton("View Ticket History");
        viewTicketHistoryButton.setBounds(10, 230, 300, 40);
        add(viewTicketHistoryButton);
    }
}
