package view.technician;

import javax.swing.*;
import java.awt.*;

public class ResolveTicketTechnicianPanel extends JPanel {
    private JComboBox<String> ticketsToResolve;
    private JComboBox<String> categories;
    private JTextArea logNotes;
    private JScrollPane logNotesScrollPane;
    private JComboBox<String> status;
    private JButton saveButton;
    private JLabel resolveDateLabel;
    private JComboBox<String> monthCombo;
    private JComboBox<String> dayCombo;
    private JComboBox<String> yearCombo;

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

        ticketsToResolve = new JComboBox<>(new String[] {"Wala", "Wala", "Wala"});      // TO BE UPDATED WITH REAL TICKET NAMES
        ticketsToResolve.setBounds(220,80,250,30);
        add(ticketsToResolve);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(120, 120, 120, 25);
        add(categoryLabel);

        categories = new JComboBox<>();
        categories.setBounds(220, 120, 250, 30);
        add(categories);
    }

    private void setupEmployeeID(){ // TO BE UPDATED WITH REAL EMPLOYEE TICKET HOLDER
        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(120, 160, 120, 25);
        add(employeeIDLabel);
    }

    private void setupLogDetails(){
        JLabel logNotesLabel = new JLabel("Log Notes:");
        logNotesLabel.setBounds(120, 200, 120, 25);
        add(logNotesLabel);

        logNotes = new JTextArea();
        logNotes.setLineWrap(true);
        logNotes.setWrapStyleWord(true);

        logNotesScrollPane = new JScrollPane(logNotes);
        logNotesScrollPane.setBounds(220, 200, 250, 100);
        logNotesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(logNotesScrollPane);

        resolveDateLabel = new JLabel("Resolve Date:");
        resolveDateLabel.setBounds(120, 310, 120, 25);
        add(resolveDateLabel);

        // Month dropdown (01-12)
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i + 1);
        }
        monthCombo = new JComboBox<>(months);
        monthCombo.setBounds(220, 310, 60, 30);
        monthCombo.setEnabled(false);
        add(monthCombo);

        // Day dropdown (01-31)
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        dayCombo = new JComboBox<>(days);
        dayCombo.setBounds(290, 310, 60, 30);
        dayCombo.setEnabled(false);
        add(dayCombo);

        // Year dropdown (2020-2035 example)
        String[] years = new String[16];
        for (int i = 0; i < 16; i++) {
            years[i] = String.valueOf(2020 + i);
        }
        yearCombo = new JComboBox<>(years);
        yearCombo.setBounds(360, 310, 80, 30);
        yearCombo.setEnabled(false);
        add(yearCombo);
    }

    private void setupStatus(){
        JLabel statusLabel = new JLabel("Ticket Status:");
        statusLabel.setBounds(120, 350, 120, 25);
        add(statusLabel);

        status = new JComboBox<>(new String[]{"Active", "In Progress", "Closed"});
        status.setBounds(220, 350, 250, 30);
        add(status);
    }

    private void setupStatusListener(){
        status.addActionListener(e -> {
            boolean isClosed = "Closed".equals(status.getSelectedItem());
            monthCombo.setEnabled(isClosed);
            dayCombo.setEnabled(isClosed);
            yearCombo.setEnabled(isClosed);
            resolveDateLabel.setEnabled(isClosed);
            resolveDateLabel.setEnabled(isClosed);
        });
    }

    private void setupButton(){
        saveButton = new JButton("Save");
        saveButton.setBounds(370, 400, 100, 35);
        add(saveButton);
    }

    public JTextArea getLogNotes(){
        return logNotes;
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

    public JComboBox<String> getCategories(){
        return categories;
    }

    public JComboBox<String> getStatus(){
        return status;
    }
}
