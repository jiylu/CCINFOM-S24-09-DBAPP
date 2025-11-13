package view.employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.util.List;
//import models.Tickets;

public class TicketHistoryPanel extends JPanel {
    private JLabel titleLabel;
    private JTable ticketTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public TicketHistoryPanel() {
        setLayout(null);
        initTitle();
        initTable();
    }

    private void initTitle() {
        titleLabel = new JLabel("Your Ticket History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setBounds(240, 50, 500, 35);
        add(titleLabel);
    }

    private void initTable() {
        String[] columnNames = {"Ticket ID", "Category ID", "Department ID", "Employee ID", 
                                "Technician ID", "Creation Date", "Resolve Date", "Status"};

        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);
        scrollPane = new JScrollPane(ticketTable);
        scrollPane.setBounds(30, 115, 750, 400);
        add(scrollPane);
    }

    public void loadTickets(java.util.List<models.Tickets> tickets) {
        
        tableModel.setRowCount(0); 

        for (models.Tickets ticket : tickets) {
            tableModel.addRow(new Object[] {
                ticket.getTicket_id(),
                ticket.getCategory_id(),
                ticket.getEmployee_id(),
                ticket.getTechnician_id(),
                ticket.getCreation_date(),
                ticket.getResolve_date(),
                ticket.getStatus()
            });
        }
    }

}
