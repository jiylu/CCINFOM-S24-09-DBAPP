package view.technician;

import javax.swing.*;
import java.awt.*;

public class CancelTicketTechnicianPanel extends JPanel {

    private JLabel titleLabel;
    private JLabel currentTicketLabel;
    private JTextArea ticketDetailsArea;

    private JLabel reasonLabel;
    private JTextArea reasonTextArea;

    private JButton cancelTicketButton;

    private String activeTicket = "No Active Ticket Found.";

    public CancelTicketTechnicianPanel() {
        setLayout(null);

        setupTitle();
        setupTicketDisplay();
        setupReasonInput();
        setupButtons();
    }

    private void setupTitle() {
        titleLabel = new JLabel("Cancel Ticket", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        int titleWidth = 300;
        int panelWidth = 800;
        int xOffset = 50;

        titleLabel.setBounds((panelWidth - titleWidth) / 2 + xOffset, 20, titleWidth, 40);
        add(titleLabel);
    }

    private void setupTicketDisplay() {
        currentTicketLabel = new JLabel("Current Active Ticket:");
        currentTicketLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentTicketLabel.setBounds(150, 80, 300, 25);
        add(currentTicketLabel);

        ticketDetailsArea = new JTextArea(activeTicket);
        ticketDetailsArea.setEditable(false);
        ticketDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ticketDetailsArea.setLineWrap(true);
        ticketDetailsArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(ticketDetailsArea);

        int scrollWidth = 600;
        int scrollHeight = 200;
        int panelWidth = 800;

        scrollPane.setBounds((panelWidth - scrollWidth) / 2 + 50, 110, scrollWidth, scrollHeight);
        add(scrollPane);
    }

    private void setupReasonInput() {
        reasonLabel = new JLabel("Reason for Cancellation:");
        reasonLabel.setFont(new Font("Arial", Font.BOLD, 16));
        reasonLabel.setBounds(150, 320, 300, 25);
        add(reasonLabel);

        reasonTextArea = new JTextArea();
        reasonTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        reasonTextArea.setLineWrap(true);
        reasonTextArea.setWrapStyleWord(true);

        JScrollPane reasonScrollPane = new JScrollPane(reasonTextArea);
        reasonScrollPane.setBounds(150, 350, 500, 80);

        add(reasonScrollPane);
    }

    private void setupButtons() {
        cancelTicketButton = new JButton("Cancel Ticket");
        cancelTicketButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelTicketButton.setBounds((800 - 160) / 2 + 50, 450, 160, 45);
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

    public String getCancelReason() {
        return reasonTextArea.getText().trim();
    }
}
