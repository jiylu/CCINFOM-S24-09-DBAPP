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


    private void setupEmployeeID(){
        employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setFont(new Font("Arial", Font.BOLD, 16));
        employeeIDLabel.setBounds(250, 160, 120, 25);
        add(employeeIDLabel);
    }

    private void setupLogDetails(){
        creationDateLabel = new JLabel("Creation Date:");
        creationDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        creationDateLabel.setBounds(250, 200, 120, 25);
        add(creationDateLabel);

        resolveDateLabel = new JLabel("Resolve Date:");
        resolveDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resolveDateLabel.setBounds(250, 240, 120, 25);
        add(resolveDateLabel);

        // Month dropdown (01-12)
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1);
        }
        monthCombo = new JComboBox<>(months);
        monthCombo.setBounds(370, 240, 60, 30);
        monthCombo.setEnabled(false);
        add(monthCombo);

        // Day dropdown (01-31)
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        dayCombo = new JComboBox<>(days);
        dayCombo.setBounds(440, 240, 60, 30);
        dayCombo.setEnabled(false);
        add(dayCombo);

        // Year dropdown (2020-2035 example)
        String[] years = new String[16];
        for (int i = 0; i < 16; i++) {
            years[i] = String.valueOf(2020 + i);
        }
        yearCombo = new JComboBox<>(years);
        yearCombo.setBounds(510, 240, 80, 30);
        yearCombo.setEnabled(false);
        add(yearCombo);
    }

    private void setupStatus(){
        JLabel statusLabel = new JLabel("Ticket Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBounds(250, 280, 120, 25);
        add(statusLabel);

        status = new JComboBox<>(new String[]{"Active", "Resolved", "Cancelled"});
        status.setBounds(370, 280, 250, 30);
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
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setBounds(520, 330, 100, 35);
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
