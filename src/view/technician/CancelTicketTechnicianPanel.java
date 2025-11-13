package view.technician;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton backButton;

    // Simulated active ticket info (replace with real data source)
    private String activeTicket = "Ticket ID: 105\nSubject: Printer not working\nEmployee: John Doe\nStatus: Active";

    public CancelTicketTechnicianPanel() {
        setLayout(null);
        setBackground(Color.WHITE);

        setupTitle();
        setupTicketDisplay();
        setupButtons();
    }

    /** Sets up the title label at the top. */
    private void setupTitle() {
        titleLabel = new JLabel("Cancel Ticket");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(250, 20, 500, 35);
        add(titleLabel);
    }

    /** Displays the current active ticket information. */
    private void setupTicketDisplay() {
        currentTicketLabel = new JLabel("Current Active Ticket:");
        currentTicketLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentTicketLabel.setBounds(50, 80, 300, 25);
        add(currentTicketLabel);

        ticketDetailsArea = new JTextArea(activeTicket);
        ticketDetailsArea.setEditable(false);
        ticketDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(ticketDetailsArea);
        scrollPane.setBounds(50, 110, 500, 150);
        add(scrollPane);
    }

    /** Adds the cancel and back buttons. */
    private void setupButtons() {
        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setBounds(150, 300, 150, 40);
        cancelTicketButton.setBackground(new Color(220, 53, 69)); // red button
        cancelTicketButton.setForeground(Color.WHITE);
        cancelTicketButton.setFocusPainted(false);
        add(cancelTicketButton);

        backButton = new JButton("Back");
        backButton.setBounds(350, 300, 100, 40);
        backButton.setFocusPainted(false);
        add(backButton);

        // Event: Cancel button clicked
        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        CancelTicketTechnicianPanel.this,
                        "Are you sure you want to cancel this ticket?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    cancelActiveTicket();
                }
            }
        });

        // Event: Back button clicked
        backButton.addActionListener(e -> {
            Container parent = CancelTicketTechnicianPanel.this.getParent();
            if (parent.getLayout() instanceof CardLayout layout) {
                layout.show(parent, TechnicianDashboardPanel.EMPTY_PANEL); // switch back
            }
        });
    }

    /** Handles the ticket cancellation logic (can be connected to controller). */
    private void cancelActiveTicket() {
        // Here, you would call your controller or model to cancel the ticket.
        JOptionPane.showMessageDialog(
                this,
                "Ticket has been successfully cancelled.",
                "Ticket Cancelled",
                JOptionPane.INFORMATION_MESSAGE
        );

        // After cancellation, clear display (optional)
        activeTicket = "No active ticket.";
        ticketDetailsArea.setText(activeTicket);
        cancelTicketButton.setEnabled(false);
    }

    /** Optional: Use this method to set the actual active ticket dynamically. */
    public void setActiveTicket(String ticketDetails) {
        this.activeTicket = ticketDetails;
        ticketDetailsArea.setText(ticketDetails);
        cancelTicketButton.setEnabled(true);
    }
}