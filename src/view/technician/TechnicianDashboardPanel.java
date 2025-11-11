package view.technician;

import javax.swing.*;
import java.awt.*;

public class TechnicianDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String RESOLVE_TICKET = "resolveTicket";

    private JLabel titleLabel;
    private JButton resolveTicketButton;
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
        cardPanel.setBounds(280,80,1200,800);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(resolveTicketTechnicianPanel, RESOLVE_TICKET);
        add(cardPanel);
    }

    public void showPanel(String name){
        cardLayout.show(cardPanel, name);
    }

    private void initTitle(){
        titleLabel = new JLabel("Welcome, Technician!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(10, 10, 600, 50);

        add(titleLabel);
    }

    private void setupButtons() {
        // Bigger font
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        resolveTicketButton = new JButton("Resolve Ticket");
        resolveTicketButton.setFont(buttonFont);
        resolveTicketButton.setBounds(20, 80, 250, 50); // increased width & height
        add(resolveTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setFont(buttonFont);
        cancelTicketButton.setBounds(20, 150, 250, 50); // increased width & height
        add(cancelTicketButton);
        cancelTicketButton.addActionListener(e -> {
            CancelTicket cancelTicketFrame = new CancelTicket();
            cancelTicketFrame.setVisible(true);
        });

        viewTicketHistoryButton = new JButton("View Ticket History");
        viewTicketHistoryButton.setFont(buttonFont);
        viewTicketHistoryButton.setBounds(20, 220, 250, 50); // increased width & height
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
