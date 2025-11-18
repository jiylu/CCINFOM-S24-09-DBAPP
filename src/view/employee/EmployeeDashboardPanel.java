package view.employee;

import javax.swing.*;
import java.awt.*;

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

        titleLabel = new JLabel("Employee Dashboard");
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
        navPanel.setBounds(10, 80, 280, 730);
        navPanel.setOpaque(false); // transparency handled in paintComponent
        add(navPanel);

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        createTicketButton = new JButton("Create New Ticket");
        createTicketButton.setFont(buttonFont);
        createTicketButton.setBounds(20, 30, 240, 50);
        styleNavButton(createTicketButton);
        navPanel.add(createTicketButton);

        ticketHistoryButton = new JButton("View Ticket History");
        ticketHistoryButton.setFont(buttonFont);
        ticketHistoryButton.setBounds(20, 100, 240, 50);
        styleNavButton(ticketHistoryButton);
        navPanel.add(ticketHistoryButton);
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

    private void initPanels() {
        createTicketPanel = new CreateTicketPanel();
        ticketHistoryPanel = new TicketHistoryPanel();
    }

    private void setupCardLayout() {
        JPanel emptyPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(300, 80, 950, 730); // right side of left panel

        cardPanel.setBackground(Color.WHITE);
        createTicketPanel.setBackground(Color.WHITE);
        ticketHistoryPanel.setBackground(Color.WHITE);

        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(createTicketPanel, CREATE_TICKET);
        cardPanel.add(ticketHistoryPanel, TICKET_HISTORY);

        add(cardPanel);
    }

    public void setEmployeeName(String firstName, String lastName) {
        titleLabel.setText("Welcome, " + firstName + " " + lastName + "!");
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
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
