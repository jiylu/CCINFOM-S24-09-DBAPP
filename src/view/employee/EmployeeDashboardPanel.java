package view.employee;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.*;


public class EmployeeDashboardPanel extends JPanel {
    public final static String EMPTY_PANEL = "empty";
    public final static String CREATE_TICKET = "createTicket";
    public final static String CANCEL_TICKET = "cancelTickets";
    public final static String TICKET_HISTORY = "ticketHistory";

    private JLabel titleLabel;
    private JButton createTicketButton;
    private JButton cancelTicketButton;
    private JButton ticketHistoryButton;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private CreateTicketPanel createTicketPanel;
    private CancelTicketPanel cancelTicketPanel;
    private TicketHistoryPanel ticketHistoryPanel;

    public EmployeeDashboardPanel() {
        setLayout(null);
        initTitle();
        setupButtons();
        initPanels();
        setupCardLayout();
    }

    private void initTitle() {
        titleLabel = new JLabel("Welcome, Employee!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBounds(10, 10, 500, 50);

        add(titleLabel);
    }

    private void setupCardLayout() {
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(250, 0, 550, 600);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(createTicketPanel, CREATE_TICKET);
        cardPanel.add(cancelTicketPanel, CANCEL_TICKET);
        cardPanel.add(ticketHistoryPanel, TICKET_HISTORY);
        add(cardPanel);
    }

    private void initPanels() {
        createTicketPanel = new CreateTicketPanel();
        cancelTicketPanel = new CancelTicketPanel();
        ticketHistoryPanel = new TicketHistoryPanel();
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    private void setupButtons() {
        createTicketButton = new JButton("Create New Ticket");
        createTicketButton.setBounds(10, 80, 200, 40);
        add(createTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setBounds(10, 130, 200, 40);
        add(cancelTicketButton);

        ticketHistoryButton = new JButton("View Ticket History");
        ticketHistoryButton.setBounds(10, 180, 200, 40);
        add(ticketHistoryButton);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getCreateTicketButton() {
        return createTicketButton;
    }

    public JButton getCancelTicketButton() {
        return cancelTicketButton;
    }

    public JButton getTicketHistoryButton() {
        return ticketHistoryButton;
    }

    public CreateTicketPanel getCreateTicketPanel() {
        return createTicketPanel;
    }

    public CancelTicketPanel getCancelTicketPanel() {
        return cancelTicketPanel;
    }

    public TicketHistoryPanel getTicketHistoryPanel() {
        return ticketHistoryPanel;
    }

}