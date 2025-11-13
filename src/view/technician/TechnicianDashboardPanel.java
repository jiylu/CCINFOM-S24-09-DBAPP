package view.technician;

import javax.swing.*;
import java.awt.*;

public class TechnicianDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String RESOLVE_TICKET = "resolveTicket";
    public final static String TICKET_QUEUE = "ticketQueue";
    public final static String TICKET_HISTORY = "ticketHistory";
    public final static String CANCEL_TICKET = "cancelTicket";

    private JLabel titleLabel;
    private JButton resolveTicketButton;
    private JButton viewTicketQueueButton;
    private JButton ticketHistoryButton;
    private JButton cancelTicketButton;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;
    private TechnicianTicketQueue technicianTicketQueuePanel;
    private TicketHistory ticketHistoryPanel;
    private CancelTicketTechnicianPanel cancelTicketPanel;

    public TechnicianDashboardPanel(){
        setLayout(null);
        initPanels();
        setupCardLayout();
        initTitle();
        setupButtons();
    }

    private void initPanels(){
        resolveTicketTechnicianPanel = new ResolveTicketTechnicianPanel();
        technicianTicketQueuePanel = new TechnicianTicketQueue();
        ticketHistoryPanel = new TicketHistory();
        cancelTicketPanel = new CancelTicketTechnicianPanel();
    }

    private void setupCardLayout(){
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(280,80,1200,800);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(resolveTicketTechnicianPanel, RESOLVE_TICKET);
        cardPanel.add(technicianTicketQueuePanel, TICKET_QUEUE);
        cardPanel.add(ticketHistoryPanel, TICKET_HISTORY);
        cardPanel.add(cancelTicketPanel, CANCEL_TICKET);

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

        viewTicketQueueButton = new JButton("View Ticket Queue");
        viewTicketQueueButton.setFont(buttonFont);
        viewTicketQueueButton.setBounds(20, 80, 250, 50);
        add(viewTicketQueueButton);

        resolveTicketButton = new JButton("Resolve Ticket");
        resolveTicketButton.setFont(buttonFont);
        resolveTicketButton.setBounds(20, 150, 250, 50);
        add(resolveTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setFont(buttonFont);
        cancelTicketButton.setBounds(20, 220, 250, 50); // increased width & height
        add(cancelTicketButton);

        ticketHistoryButton = new JButton("View Ticket History");
        ticketHistoryButton.setFont(buttonFont);
        ticketHistoryButton.setBounds(20, 290, 250, 50);
        add(ticketHistoryButton);
    }

    public JLabel getTitleLabel(){
        return titleLabel;
    }

    public JButton getResolveTicketButton(){
        return resolveTicketButton;
    }

    public JButton getViewTicketQueueButton() {
        return viewTicketQueueButton;
    }

    public JButton getTicketHistoryButton() {
        return ticketHistoryButton;
    }

    public JButton getCancelTicketButton() {
        return cancelTicketButton;
    }

    public ResolveTicketTechnicianPanel getResolveTicketTechnicianPanel(){
        return resolveTicketTechnicianPanel;
    }

    public TechnicianTicketQueue getTechnicianTicketQueuePanel() {
        return technicianTicketQueuePanel;
    }

    public TicketHistory getTicketHistoryPanel() {
        return ticketHistoryPanel;
    }

    public CancelTicketTechnicianPanel getCancelTicketPanel() {
        return cancelTicketPanel;
    }
}
