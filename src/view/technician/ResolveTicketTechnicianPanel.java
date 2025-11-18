package view.technician;

import models.Tickets;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResolveTicketTechnicianPanel extends JPanel {
    private JComboBox<String> ticketsToResolve;
    private JComboBox<CategoryItem> categories;
    private JTextArea ticketsSubjectLabel;
    private JTextArea ticketsDescriptionLabel;
    private JLabel employeeIDLabel;
    private JLabel creationDateLabel;
    private JLabel resolveDateLabel;
    private JComboBox<String> status;
    private JButton saveButton;
    private List<Tickets> ticketsList;

    public ResolveTicketTechnicianPanel() {
        setLayout(null);
        setBackground(new Color(230, 230, 230));

        setupFormBackground();
        setupStatusListener();
    }

    private void setupFormBackground() {
        JPanel formPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Shadow
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(5, 5, getWidth(), getHeight(), 20, 20);

                // White panel with slight transparency
                g2.setColor(new Color(255, 255, 255, 230));
                g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 20, 20);
            }
        };
        formPanel.setLayout(null);
        formPanel.setBounds(150, 30, 700, 550);
        formPanel.setOpaque(false);
        add(formPanel);

        // Title
        JLabel formTitle = new JLabel("Select Ticket to Resolve");
        formTitle.setFont(new Font("Arial", Font.BOLD, 28));
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);
        formTitle.setBounds(0, 20, 700, 40);
        formPanel.add(formTitle);

        // Tickets
        JLabel ticketsLabel = new JLabel("Tickets:");
        ticketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketsLabel.setBounds(50, 80, 120, 25);
        formPanel.add(ticketsLabel);

        ticketsToResolve = new JComboBox<>();
        ticketsToResolve.setBounds(200, 80, 300, 30);
        formPanel.add(ticketsToResolve);

        // Categories
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        categoryLabel.setBounds(50, 130, 120, 25);
        formPanel.add(categoryLabel);

        categories = new JComboBox<>();
        categories.setBounds(200, 130, 300, 30);
        formPanel.add(categories);

        // Ticket Subject
        JLabel subjectLabel = new JLabel("Ticket Subject:");
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subjectLabel.setBounds(50, 180, 150, 25);
        formPanel.add(subjectLabel);

        ticketsSubjectLabel = new JTextArea();
        ticketsSubjectLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ticketsSubjectLabel.setBounds(200, 180, 400, 50);
        ticketsSubjectLabel.setLineWrap(true);
        ticketsSubjectLabel.setWrapStyleWord(true);
        ticketsSubjectLabel.setEditable(false);
        ticketsSubjectLabel.setOpaque(false);
        formPanel.add(ticketsSubjectLabel);

        // Ticket Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel.setBounds(50, 240, 150, 25);
        formPanel.add(descriptionLabel);

        ticketsDescriptionLabel = new JTextArea();
        ticketsDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        ticketsDescriptionLabel.setBounds(200, 240, 400, 80);
        ticketsDescriptionLabel.setLineWrap(true);
        ticketsDescriptionLabel.setWrapStyleWord(true);
        ticketsDescriptionLabel.setEditable(false);
        ticketsDescriptionLabel.setOpaque(false);
        formPanel.add(ticketsDescriptionLabel);

        // Employee ID
        JLabel empLabel = new JLabel("Employee ID:");
        empLabel.setFont(new Font("Arial", Font.BOLD, 16));
        empLabel.setBounds(50, 320, 150, 25);
        formPanel.add(empLabel);

        employeeIDLabel = new JLabel("");
        employeeIDLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        employeeIDLabel.setBounds(200, 320, 300, 25);
        formPanel.add(employeeIDLabel);

        // Dates
        JLabel creationLabel = new JLabel("Creation Date:");
        creationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        creationLabel.setBounds(50, 360, 150, 25);
        formPanel.add(creationLabel);

        creationDateLabel = new JLabel("");
        creationDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        creationDateLabel.setBounds(200, 360, 300, 25);
        formPanel.add(creationDateLabel);

        JLabel resolveLabel = new JLabel("Resolve Date:");
        resolveLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resolveLabel.setBounds(50, 400, 150, 25);
        formPanel.add(resolveLabel);

        resolveDateLabel = new JLabel("");
        resolveDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resolveDateLabel.setBounds(200, 400, 200, 25);
        formPanel.add(resolveDateLabel);

        // Status
        JLabel statusLabel = new JLabel("Ticket Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBounds(50, 440, 150, 25);
        formPanel.add(statusLabel);

        status = new JComboBox<>(new String[]{"Mark Ticket Resolution Status", "Resolved"});
        status.setBounds(200, 440, 300, 30);
        formPanel.add(status);

        // Save button (below status)
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(0, 153, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.setBounds(362, 490, 140, 35); // centered below status
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(0, 115, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButton.setBackground(new Color(0, 153, 0));
            }
        });
        formPanel.add(saveButton);
    }

    private void setupStatusListener() {
        status.addActionListener(e -> {
            boolean isClosed = "Resolved".equals(status.getSelectedItem());
            if (isClosed) {
                java.time.LocalDateTime now = java.time.LocalDateTime.now();
                java.time.format.DateTimeFormatter formatter =
                        java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                resolveDateLabel.setText(now.format(formatter));
            } else {
                resolveDateLabel.setText("");
            }
        });
    }

    // Getters
    public JComboBox<String> getTicketsToResolve() { return ticketsToResolve; }
    public JComboBox<CategoryItem> getCategories() { return categories; }
    public JComboBox<String> getStatus() { return status; }
    public JLabel getEmployeeIDLabel() { return employeeIDLabel; }
    public JLabel getCreationDateLabel() { return creationDateLabel; }
    public JLabel getResolveDateLabel() { return resolveDateLabel; }
    public JButton getSaveButton() { return saveButton; }
    public JTextArea getTicketsSubjectLabel() { return ticketsSubjectLabel; }
    public JTextArea getTicketsDescriptionLabel() { return ticketsDescriptionLabel; }
    public List<Tickets> getTicketsList() { return ticketsList; }
    public void setCategory(String category) { categories.setSelectedItem(category); }
    public void setTicketsList(List<Tickets> ticketsList) { this.ticketsList = ticketsList; }
}
