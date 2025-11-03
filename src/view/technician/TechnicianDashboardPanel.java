package view.technician;

import javax.swing.*;
import java.awt.*;

public class TechnicianDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String RESOLVE_TICKET = "resolveTicket";

    private JLabel titleLabel;
    private JButton resolveTicketButton;
    private JButton reassignTicketButton;
    private JButton cancelTicketButton;
    private JButton viewTicketHistoryButton;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;

    public TechnicianDashboardPanel(){
        setLayout(null);
        initPanels();
        setupCardLayout();
        initTitle();
        setupButtons();
    }

    private void initPanels(){
        resolveTicketTechnicianPanel = new ResolveTicketTechnicianPanel();
    }

    private void setupCardLayout(){
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(230,80,550,520);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(resolveTicketTechnicianPanel, RESOLVE_TICKET);
        add(cardPanel);
    }

    public void showPanel(String name){
        cardLayout.show(cardPanel, name);
    }

    private void initTitle(){
        titleLabel = new JLabel("Welcome, Technician!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(10, 10, 500, 50);

        add(titleLabel);
    }

    private void setupButtons(){
        resolveTicketButton = new JButton("Resolve Ticket");
        resolveTicketButton.setBounds(10, 80, 200, 40);
        add(resolveTicketButton);

        reassignTicketButton = new JButton("Reassign Ticket");
        reassignTicketButton.setBounds(10, 130, 200, 40);
        add(reassignTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setBounds(10, 180, 200, 40);
        add(cancelTicketButton);
        cancelTicketButton.addActionListener(e -> {
            CancelTicket cancelTicketFrame = new CancelTicket();
            cancelTicketFrame.setVisible(true);
        });

        viewTicketHistoryButton = new JButton("View Ticket History");
        viewTicketHistoryButton.setBounds(10, 230, 200, 40);
        add(viewTicketHistoryButton);
        viewTicketHistoryButton.addActionListener(e -> {
            TicketHistory ticketHistoryFrame = new TicketHistory();
            ticketHistoryFrame.setVisible(true);
        });
    }

    public JLabel getTitleLabel(){
        return titleLabel;
    }

    public JButton getResolveTicketButton(){
        return resolveTicketButton;
    }

    public JButton getReassignTicketButton(){
        return reassignTicketButton;
    }

    public JButton getCancelTicketButton(){
        return cancelTicketButton;
    }

    public JButton getViewTicketHistoryButton(){
        return viewTicketHistoryButton;
    }

    public ResolveTicketTechnicianPanel getResolveTicketTechnicianPanel(){
        return resolveTicketTechnicianPanel;
    }
}
