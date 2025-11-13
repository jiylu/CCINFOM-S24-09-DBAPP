package view.technician;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that allows a technician to cancel the currently active ticket.
 * This panel should be connected to the controller or service layer to handle
 * the actual ticket cancellation logic.
 */
public class CancelTicketTechnicianPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel currentTicketLabel;
    private JTextArea ticketDetailsArea;
    private JButton cancelTicketButton;

    private String activeTicket = "No Active Ticket Found.";

    public CancelTicketTechnicianPanel() {
        setLayout(null);

        setupTitle();
        setupTicketDisplay();
        setupButtons();
    }

    /** Sets up the title label at the top. */
    private void setupTitle() {
        titleLabel = new JLabel("Cancel Ticket", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        int titleWidth = 300;
        int panelWidth = 800;
        int xOffset = 50;   

        titleLabel.setBounds((panelWidth - titleWidth) / 2 + xOffset, 20, titleWidth, 40);
        add(titleLabel);
    }

    /** Displays the current active ticket information. */
    private void setupTicketDisplay() {
        currentTicketLabel = new JLabel("Current Active Ticket:");
        currentTicketLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentTicketLabel.setBounds(150, 80, 300, 25);
        add(currentTicketLabel);

        ticketDetailsArea = new JTextArea(activeTicket);
        ticketDetailsArea.setEditable(false);
        ticketDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(ticketDetailsArea);
        int scrollWidth = 600;
        int scrollHeight = 180;
        int panelWidth = 800;
        scrollPane.setBounds((panelWidth - scrollWidth) / 2 + 50, 110, scrollWidth, scrollHeight);
        add(scrollPane);
    }

    /** Adds the cancel and back buttons. */
    private void setupButtons() {
        cancelTicketButton = new JButton("Cancel Ticket");

        cancelTicketButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelTicketButton.setBounds((800 - 160) / 2 + 50, 320, 160, 45);
        cancelTicketButton.setBackground(new Color(220, 53, 69)); // red
        cancelTicketButton.setForeground(Color.WHITE);
        cancelTicketButton.setFocusPainted(false);
        cancelTicketButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(cancelTicketButton);
    }

    public void setActiveTicket(String ticketDetails) {
        this.activeTicket = ticketDetails;
        ticketDetailsArea.setText(ticketDetails);
        cancelTicketButton.setEnabled(true);
    }

    public JButton getCancelTicketButton() {
        return cancelTicketButton;
    }
}