package view.technician;

import javax.swing.*;
import java.awt.*;
<<<<<<< Updated upstream
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import models.Tickets;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
    private JButton backButton;

    // Simulated active ticket info (replace with real data source)
    private String activeTicket = "Ticket ID: 105\nSubject: Printer not working\nEmployee: John Doe\nStatus: Active";

    public CancelTicketTechnicianPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
=======

    private String activeTicket = "No Active Ticket Found.";

    public CancelTicketTechnicianPanel() {
        setLayout(null);
>>>>>>> Stashed changes

        setupTitle();
        setupTicketDisplay();
        setupButtons();
    }

    /** Sets up the title label at the top. */
    private void setupTitle() {
<<<<<<< Updated upstream
        titleLabel = new JLabel("Cancel Ticket");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(250, 20, 500, 35);
=======
        titleLabel = new JLabel("Cancel Ticket", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        int titleWidth = 300;
        int panelWidth = 800;
        int xOffset = 50;   

        titleLabel.setBounds((panelWidth - titleWidth) / 2 + xOffset, 20, titleWidth, 40);
>>>>>>> Stashed changes
        add(titleLabel);
    }

    /** Displays the current active ticket information. */
    private void setupTicketDisplay() {
        currentTicketLabel = new JLabel("Current Active Ticket:");
        currentTicketLabel.setFont(new Font("Arial", Font.BOLD, 16));
<<<<<<< Updated upstream
        currentTicketLabel.setBounds(50, 80, 300, 25);
=======
        currentTicketLabel.setBounds(150, 80, 300, 25);
>>>>>>> Stashed changes
        add(currentTicketLabel);

        ticketDetailsArea = new JTextArea(activeTicket);
        ticketDetailsArea.setEditable(false);
        ticketDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(ticketDetailsArea);
<<<<<<< Updated upstream
        scrollPane.setBounds(50, 110, 500, 150);
=======
        int scrollWidth = 600;
        int scrollHeight = 180;
        int panelWidth = 800;
        scrollPane.setBounds((panelWidth - scrollWidth) / 2 + 50, 110, scrollWidth, scrollHeight);
>>>>>>> Stashed changes
        add(scrollPane);
    }

    /** Adds the cancel and back buttons. */
    private void setupButtons() {
        cancelTicketButton = new JButton("Cancel Ticket");
<<<<<<< Updated upstream
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
=======
        cancelTicketButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelTicketButton.setBounds((800 - 160) / 2 + 50, 320, 160, 45);
        cancelTicketButton.setBackground(new Color(220, 53, 69)); // red
        cancelTicketButton.setForeground(Color.WHITE);
        cancelTicketButton.setFocusPainted(false);
        cancelTicketButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(cancelTicketButton);
    }

>>>>>>> Stashed changes
    public void setActiveTicket(String ticketDetails) {
        this.activeTicket = ticketDetails;
        ticketDetailsArea.setText(ticketDetails);
        cancelTicketButton.setEnabled(true);
    }
<<<<<<< Updated upstream
=======

    public JButton getCancelTicketButton() {
        return cancelTicketButton;
    }
>>>>>>> Stashed changes
}