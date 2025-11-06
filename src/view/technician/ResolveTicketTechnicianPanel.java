package view.technician;

import models.Tickets;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResolveTicketTechnicianPanel extends JPanel {
    private JComboBox<String> ticketsToResolve;
    private JComboBox<CategoryItem> categories;
    private JLabel employeeIDLabel;
    private JLabel creationDateLabel;
    private JLabel resolveDateLabel;
    private JComboBox<String> status;
    private JButton saveButton;
    private JComboBox<String> monthCombo;
    private JComboBox<String> dayCombo;
    private JComboBox<String> yearCombo;
    private List<Tickets> ticketsList;

    public ResolveTicketTechnicianPanel(){
        setLayout(null);
        setupTitle();
        setupTicketsAndCategories();
        setupEmployeeID();
        setupLogDetails();
        setupStatus();
        setupButton();
        setupStatusListener();
    }

    private void setupTitle(){
        JLabel titleLabel = new JLabel("Select Ticket to Resolve");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 300, 35);
        add(titleLabel);
    }

    private void setupTicketsAndCategories(){
        JLabel ticketsLabel = new JLabel("Tickets: ");
        ticketsLabel.setBounds(120,80,120,25);
        add(ticketsLabel);

        ticketsToResolve = new JComboBox<>(); // Filled dynamically by controller
        ticketsToResolve.setBounds(220,80,250,30);
        add(ticketsToResolve);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(120, 120, 120, 25);
        add(categoryLabel);

        categories = new JComboBox<CategoryItem>(); // Uses CategoryItem with ID + name
        categories.setBounds(220, 120, 250, 30);
        add(categories);
    }


    private void setupEmployeeID(){ // TO BE UPDATED WITH REAL EMPLOYEE TICKET HOLDER
        employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(120, 160, 120, 25);
        add(employeeIDLabel);
    }

    private void setupLogDetails(){
        creationDateLabel = new JLabel("Creation Date:");
        creationDateLabel.setBounds(120, 200, 120, 25);
        add(creationDateLabel);

        resolveDateLabel = new JLabel("Resolve Date:");
        resolveDateLabel.setBounds(120, 240, 120, 25);
        add(resolveDateLabel);

        // Month dropdown (01-12)
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1);
        }
        monthCombo = new JComboBox<>(months);
        monthCombo.setBounds(220, 240, 60, 30);
        monthCombo.setEnabled(false);
        add(monthCombo);

        // Day dropdown (01-31)
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        dayCombo = new JComboBox<>(days);
        dayCombo.setBounds(290, 240, 60, 30);
        dayCombo.setEnabled(false);
        add(dayCombo);

        // Year dropdown (2020-2035 example)
        String[] years = new String[16];
        for (int i = 0; i < 16; i++) {
            years[i] = String.valueOf(2020 + i);
        }
        yearCombo = new JComboBox<>(years);
        yearCombo.setBounds(360, 240, 80, 30);
        yearCombo.setEnabled(false);
        add(yearCombo);
    }

    private void setupStatus(){
        JLabel statusLabel = new JLabel("Ticket Status:");
        statusLabel.setBounds(120, 280, 120, 25);
        add(statusLabel);

        status = new JComboBox<>(new String[]{"Active", "Resolved", "Cancelled"});
        status.setBounds(220, 280, 250, 30);
        add(status);
    }

    private void setupStatusListener(){
        status.addActionListener(e -> {
            boolean isClosed = "Resolved".equals(status.getSelectedItem()) || "Cancelled".equals(status.getSelectedItem());
            monthCombo.setEnabled(isClosed);
            dayCombo.setEnabled(isClosed);
            yearCombo.setEnabled(isClosed);
            resolveDateLabel.setEnabled(isClosed);
            resolveDateLabel.setEnabled(isClosed);

            if(isClosed){
                java.time.LocalDate today = java.time.LocalDate.now();

                // Auto-set Month, Day, Year based on current date
                monthCombo.setSelectedItem(String.format("%02d", today.getMonthValue()));
                dayCombo.setSelectedItem(String.format("%02d", today.getDayOfMonth()));
                yearCombo.setSelectedItem(String.valueOf(today.getYear()));
            }
        });
    }

    private void setupButton(){
        saveButton = new JButton("Save");
        saveButton.setBounds(370, 330, 100, 35);
        add(saveButton);
    }

    public String getResolveDate() {
        if (!monthCombo.isEnabled()) {
            return null; // No date selected
        }
        return monthCombo.getSelectedItem() + "-" +
                dayCombo.getSelectedItem() + "-" +
                yearCombo.getSelectedItem();
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

    public JComboBox<String> getDayCombo() {
        return dayCombo;
    }

    public JComboBox<String> getMonthCombo() {
        return monthCombo;
    }

    public JComboBox<String> getYearCombo() {
        return yearCombo;
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
