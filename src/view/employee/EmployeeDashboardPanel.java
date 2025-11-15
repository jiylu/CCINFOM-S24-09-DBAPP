package view.employee;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;


public class EmployeeDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String CREATE_TICKET = "createTicket";
    public final static String TICKET_HISTORY = "ticketHistory";

    private JLabel titleLabel;
    private JButton createTicketButton;
    private JButton ticketHistoryButton;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private CreateTicketPanel createTicketPanel;
    private TicketHistoryPanel ticketHistoryPanel;

    public EmployeeDashboardPanel() {
        setLayout(null);
        initTitle();
        setupButtons();
        initPanels();
        setupCardLayout();
    }

    private void initTitle() {
        titleLabel = new JLabel("Employee Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(10, 10, 500, 50);
        add(titleLabel);
    }

    private void setupCardLayout() {
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(370, 10, 800, 750);
        
        cardPanel.setBackground(Color.WHITE);
        createTicketPanel.setBackground(Color.WHITE); 
        ticketHistoryPanel.setBackground(Color.WHITE); 

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(createTicketPanel, CREATE_TICKET);
        cardPanel.add(ticketHistoryPanel, TICKET_HISTORY);
        add(cardPanel);
    }

    private void initPanels() {
        
        createTicketPanel = new CreateTicketPanel();
        ticketHistoryPanel = new TicketHistoryPanel();
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    private void setupButtons() {

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        createTicketButton = new JButton("Create New Ticket");
        createTicketButton.setFont(buttonFont);
        createTicketButton.setBounds(40, 80, 250, 50);
        add(createTicketButton);

        ticketHistoryButton = new JButton("View Ticket History");
        ticketHistoryButton.setFont(buttonFont);
        ticketHistoryButton.setBounds(40, 150, 250, 50);
        add(ticketHistoryButton);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getCreateTicketButton() {
        return createTicketButton;
    }


    public JButton getTicketHistoryButton() {
        return ticketHistoryButton;
    }

    public CreateTicketPanel getCreateTicketPanel() {
        return createTicketPanel;
    }

    public TicketHistoryPanel getTicketHistoryPanel() {
        return ticketHistoryPanel;
    }

}