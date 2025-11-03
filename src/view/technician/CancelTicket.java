package view.technician;

import javax.swing.*;
import java.awt.*;

public class CancelTicket extends JFrame {
    
    private JTextField ticketIdField;
    private JButton cancelButton, clearButton;
    private JLabel messageLabel;

    public CancelTicket() {
        setTitle("Cancel Ticket");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Cancel Ticket");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Ticket ID:"), gbc);

        ticketIdField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(ticketIdField, gbc);

        JPanel buttonPanel = new JPanel();
        cancelButton = new JButton("Cancel Ticket");
        clearButton = new JButton("Clear");
        buttonPanel.add(cancelButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gbc.gridy = 3;
        panel.add(messageLabel, gbc);

        add(panel);

        cancelButton.addActionListener(e -> onCancelTicket());
        clearButton.addActionListener(e -> ticketIdField.setText(""));
    }

    private void onCancelTicket() {
        String ticketId = ticketIdField.getText().trim();

        if (ticketId.isEmpty()) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Please enter a Ticket ID.");
        } else {
            // Dito lalagay dapat yung actual cancellation logic
            messageLabel.setForeground(Color.BLUE);
            messageLabel.setText("Attempting to cancel Ticket ID: " + ticketId);
        }
    }
}