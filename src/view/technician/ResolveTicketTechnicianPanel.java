package view.technician;

import javax.swing.*;
import java.awt.*;

public class ResolveTicketTechnicianPanel extends JPanel {
    private JComboBox<String> ticketsToResolve;
    private JComboBox<String> categories;
    private JTextField logNotes;
    private JTextField logDate;
    private JComboBox<String> status;
    private JButton saveButton;

    public ResolveTicketTechnicianPanel(){
        setLayout(null);
        setupTitle();
        setupTickets();
        setupEmployeeID();
        setupLogDetails();
        setupStatus();
        setupButton();
    }

    private void setupTitle(){
        JLabel titleLabel = new JLabel("Select Ticket to Resolve");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(20, 80, 300, 40);
        add(titleLabel);
    }

    private void setupTickets(){
        JLabel ticketsLabel = new JLabel("Tickets: ");
        ticketsLabel.setBounds(20,140,200,25);
        add(ticketsLabel);

        ticketsToResolve = new JComboBox<>(new String[] {"Wala", "Wala", "Wala"});      // TO BE UPDATED WITH REAL TICKET NAMES
        ticketsToResolve.setBounds(130,250,200,25);
        add(ticketsToResolve);
    }

    private void setupEmployeeID(){ // TO BE UPDATED WITH REAL EMPLOYEE TICKET HOLDER
        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(20, 180, 200, 25);
        add(employeeIDLabel);
    }

    private void setupLogDetails(){
        JLabel logNotesLabel = new JLabel("Log Notes: ");
        logNotesLabel.setBounds(20, 220, 100, 25);
        add(logNotesLabel);

        logNotes = new JTextField();
        logNotes.setBounds(130, 220, 200, 100);
        add(logNotes);

        JLabel logDateLabel = new JLabel("Log Date: ");
        logDateLabel.setBounds(20, 300, 100, 25);
        add(logDateLabel);

        logDate = new JTextField();
        logDate.setBounds(130, 300, 120, 25);
        add(logDate);
    }

    private void setupStatus(){
        JLabel statusLabel = new JLabel("Ticket Status: ");
        statusLabel.setBounds(20,340,200,25);
        add(statusLabel);

        status = new JComboBox<>(new String[] {"Active", "In Progress", "Closed"});      // TO BE UPDATED WITH REAL TICKET NAMES
        status.setBounds(130,450,200,25);
        add(status);
    }

    private void setupButton(){
        saveButton = new JButton("Save");
        saveButton.setBounds(130, 480, 100, 30);
        add(saveButton);
    }
}
