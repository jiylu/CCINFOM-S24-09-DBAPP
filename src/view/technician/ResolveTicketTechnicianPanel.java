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

    public ResolveTicketTechnicianPanel(){
        setLayout(null);
        setupTitle();
        setupTicketsAndCategories();
        setupTicketSubject();
        setupTicketDescription();
        setupEmployeeID();
        setupLogDetails();
        setupStatus();
        setupButton();
        setupStatusListener();
    }

    private void setupTitle(){
        JLabel titleLabel = new JLabel("Select Ticket to Resolve");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(250, 20, 500, 35);
        add(titleLabel);
    }

    private void setupTicketsAndCategories(){
        JLabel ticketsLabel = new JLabel("Tickets: ");
        ticketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketsLabel.setBounds(250,80,120,25);
        add(ticketsLabel);

        ticketsToResolve = new JComboBox<>(); // Filled dynamically by controller
        ticketsToResolve.setBounds(370,80,250,30);
        add(ticketsToResolve);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        categoryLabel.setBounds(250, 120, 120, 25);
        add(categoryLabel);

        categories = new JComboBox<CategoryItem>(); // Uses CategoryItem with ID + name
        categories.setBounds(370, 120, 250, 30);
        add(categories);
    }

    private void setupTicketSubject(){
        JLabel ticketSubject = new JLabel("Ticket Subject:");
        ticketSubject.setFont(new Font("Arial", Font.BOLD, 16));
        ticketSubject.setBounds(250, 160, 400, 25);
        add(ticketSubject);

        ticketsSubjectLabel = new JTextArea();
        ticketsSubjectLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ticketsSubjectLabel.setBounds(370, 160, 400, 50);
        ticketsSubjectLabel.setLineWrap(true);
        ticketsSubjectLabel.setWrapStyleWord(true);
        ticketsSubjectLabel.setEditable(false);
        ticketsSubjectLabel.setOpaque(false);
        add(ticketsSubjectLabel);
    }

    private void setupTicketDescription(){
        JLabel ticketDescription = new JLabel("Description:");
        ticketDescription.setFont(new Font("Arial", Font.BOLD, 16));
        ticketDescription.setBounds(250, 210, 400, 25);
        add(ticketDescription);

        ticketsDescriptionLabel = new JTextArea();
        ticketsDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        ticketsDescriptionLabel.setBounds(370, 210, 280, 80);
        ticketsDescriptionLabel.setLineWrap(true);
        ticketsDescriptionLabel.setWrapStyleWord(true);
        ticketsDescriptionLabel.setEditable(false);
        ticketsDescriptionLabel.setOpaque(false);
        add(ticketsDescriptionLabel);
    }

    private void setupEmployeeID(){
        JLabel employeeID = new JLabel("Employee ID:");
        employeeID.setFont(new Font("Arial", Font.BOLD, 16));
        employeeID.setBounds(250, 280, 400, 25);
        add(employeeID);

        employeeIDLabel = new JLabel("");
        employeeIDLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        employeeIDLabel.setBounds(370, 280, 400, 25);
        add(employeeIDLabel);
    }

    private void setupLogDetails(){
        JLabel creationDate = new JLabel("Creation Date:");
        creationDate = new JLabel("Creation Date:");
        creationDate.setFont(new Font("Arial", Font.BOLD, 16));
        creationDate.setBounds(250, 320, 400, 25);
        add(creationDate);

        creationDateLabel = new JLabel("");
        creationDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        creationDateLabel.setBounds(370, 320, 400, 25);
        add(creationDateLabel);

        JLabel resolveDateTitle = new JLabel("Resolve Date:");
        resolveDateTitle.setFont(new Font("Arial", Font.BOLD, 16));
        resolveDateTitle.setBounds(250, 360, 120, 25);
        add(resolveDateTitle);

        resolveDateLabel = new JLabel(""); // shows the date dynamically
        resolveDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resolveDateLabel.setBounds(370, 360, 200, 25);
        add(resolveDateLabel);
    }

    private void setupStatus(){
        JLabel statusLabel = new JLabel("Ticket Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBounds(250, 400, 120, 25);
        add(statusLabel);

        status = new JComboBox<>(new String[]{"Mark Ticket Resolution Status", "Resolved"});
        status.setBounds(370, 400, 250, 30);
        add(status);
    }

    private void setupStatusListener(){
        status.addActionListener(e -> {
            boolean isClosed = "Resolved".equals(status.getSelectedItem());

            if (isClosed) {
                java.time.LocalDateTime now = java.time.LocalDateTime.now();
                java.time.format.DateTimeFormatter formatter =
                        java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                String formattedDateTime = now.format(formatter);
                resolveDateLabel.setText(formattedDateTime);
            } else {
                resolveDateLabel.setText("—");
            }
        });
    }

    private void setupButton(){
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setBounds(520, 450, 100, 35);
        add(saveButton);
    }

    public JComboBox<String> getTicketsToResolve(){
        return ticketsToResolve;
    }

    public JComboBox<CategoryItem> getCategories(){
        return categories;
    }

    public JComboBox<String> getStatus(){
        return status;
    }

    public JLabel getEmployeeIDLabel() {
        return employeeIDLabel;
    }

    public JLabel getCreationDateLabel() {
        return creationDateLabel;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextArea getTicketsSubjectLabel() {
        return ticketsSubjectLabel;
    }

    public JTextArea getTicketsDescriptionLabel(){
        return ticketsDescriptionLabel;
    }

    public JLabel getResolveDateLabel() {
        return resolveDateLabel;
    }

    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setCategory(String category) {
        categories.setSelectedItem(category);
    }

    public void setTicketsList(List<Tickets> ticketsList){
        this.ticketsList = ticketsList;
    }
}
