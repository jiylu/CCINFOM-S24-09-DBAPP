package view.technician;

import java.awt.*;
import javax.swing.*;

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
    private JButton logoutButton;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ResolveTicketTechnicianPanel resolveTicketTechnicianPanel;
    private TechnicianTicketQueue technicianTicketQueuePanel;
    private TicketHistory ticketHistoryPanel;
    private CancelTicketTechnicianPanel cancelTicketPanel;

    public TechnicianDashboardPanel() {
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        initHeaderBar();
        initNavigationPanel();
        initPanels();
        setupCardLayout();
    }

    // Dark green header bar
    private void initHeaderBar() {
        JPanel headerBar = new JPanel();
        headerBar.setBackground(new Color(0, 102, 0)); // dark green
        headerBar.setBounds(0, 0, 1500, 70);
        headerBar.setLayout(null);

        titleLabel = new JLabel("");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 10, 600, 50);
        headerBar.add(titleLabel);

        add(headerBar);
    }

    // Rounded left navigation panel with transparency & shadow
    private void initNavigationPanel() {
        JPanel navPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Shadow effect
                g2.setColor(new Color(0, 0, 0, 50)); // semi-transparent shadow
                g2.fillRoundRect(5, 5, getWidth(), getHeight(), 20, 20);

                // Transparent dark green panel
                g2.setColor(new Color(0, 102, 0, 200)); // dark green with alpha
                g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 20, 20);
            }
        };
        navPanel.setLayout(null);
        navPanel.setBounds(10, 80, 280, 800);
        navPanel.setOpaque(false); // transparency handled in paintComponent
        add(navPanel);

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        viewTicketQueueButton = new JButton("View Ticket Queue");
        viewTicketQueueButton.setFont(buttonFont);
        viewTicketQueueButton.setBounds(20, 30, 240, 50);
        styleNavButton(viewTicketQueueButton);
        navPanel.add(viewTicketQueueButton);

        resolveTicketButton = new JButton("Resolve Ticket");
        resolveTicketButton.setFont(buttonFont);
        resolveTicketButton.setBounds(20, 100, 240, 50);
        styleNavButton(resolveTicketButton);
        navPanel.add(resolveTicketButton);

        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setFont(buttonFont);
        cancelTicketButton.setBounds(20, 170, 240, 50);
        styleNavButton(cancelTicketButton);
        navPanel.add(cancelTicketButton);

        ticketHistoryButton = new JButton("View Ticket History");
        ticketHistoryButton.setFont(buttonFont);
        ticketHistoryButton.setBounds(20, 240, 240, 50);
        styleNavButton(ticketHistoryButton);
        navPanel.add(ticketHistoryButton);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(buttonFont);
        logoutButton.setBounds(20, 310, 240, 50);
        styleLogoutButton(logoutButton);
        navPanel.add(logoutButton);
    }

    private void styleNavButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 153, 0)); // green button
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 115, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 0));
            }
        });
    }

    private void styleLogoutButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(204, 0, 0)); // red button
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(153, 0, 0)); // darker red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(204, 0, 0));
            }
        });
    }

    private void initPanels() {
        resolveTicketTechnicianPanel = new ResolveTicketTechnicianPanel();
        technicianTicketQueuePanel = new TechnicianTicketQueue();
        ticketHistoryPanel = new TicketHistory();
        cancelTicketPanel = new CancelTicketTechnicianPanel();
    }

    private void setupCardLayout() {
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(300, 80, 1200, 800);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(resolveTicketTechnicianPanel, RESOLVE_TICKET);
        cardPanel.add(technicianTicketQueuePanel, TICKET_QUEUE);
        cardPanel.add(ticketHistoryPanel, TICKET_HISTORY);
        cardPanel.add(cancelTicketPanel, CANCEL_TICKET);

        add(cardPanel);
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getResolveTicketButton() {
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

    public JButton getLogoutButton(){
        return logoutButton;
    }

    public ResolveTicketTechnicianPanel getResolveTicketTechnicianPanel() {
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

    public void setTechnicianName(String firstName, String lastName) {
        titleLabel.setText("Welcome, Technician " + firstName + " " + lastName + "!");
    }
}
