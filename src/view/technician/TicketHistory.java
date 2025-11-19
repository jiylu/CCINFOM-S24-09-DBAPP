package view.technician;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Tickets;

public class TicketHistory extends JPanel {
    private JLabel titleLabel;
    private JTable ticketTable;
    private JScrollPane scrollPane;

    public TicketHistory() {
        setLayout(null); // absolute layout for easy positioning
        setBackground(Color.WHITE);
        initTitle();
        initTable();
    }

    private void initTitle() {
        titleLabel = new JLabel("Resolved/Cancelled Ticket History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(20, 10, 600, 40);
        add(titleLabel);
    }

    private DefaultTableModel initTable() {
        if (scrollPane != null) {
            remove(scrollPane);
        }

        // Define columns
        String[] columnNames = {"Ticket Id", "Subject", "Category ID", "Employee ID", 
                                "Technician ID", "Creation Date", "Status"};

        // Create empty table model (editable = false)
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };

        // Create JTable and style it
        ticketTable = new JTable(tableModel);
        ticketTable.setFont(new Font("Arial", Font.PLAIN, 16));
        ticketTable.setRowHeight(28);
        ticketTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        ticketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add table to scroll pane
        scrollPane = new JScrollPane(ticketTable);
        scrollPane.setBounds(20, 70, 850, 700);
        add(scrollPane);

        return tableModel;
    }

    // Utility method to add a ticket row
    public void addTicket(String ticketId, String ticketSubject, String categoryId, String employeeId,
                          String technicianId, String creationDate, String status) {
        //tableModel.addRow(new Object[]{ticketId, ticketSubject, categoryId, employeeId, technicianId, creationDate, status});
    }

    public void loadTickets(List<Tickets> tickets){
        DefaultTableModel tableModel = initTable();

        for (models.Tickets ticket : tickets) {
            tableModel.addRow(new Object[] {
                ticket.getTicket_id(),
                ticket.getCategory_id(),
                ticket.getTicket_subject(),
                ticket.getTicket_description(),
                ticket.getTechnician_id(),
                ticket.getCreation_date(),
                ticket.getStatus()
            });
        }  
    }

    // Optional: clear table
    public void clearTickets() {
        tableModel.setRowCount(0);
    }

    public JTable getTicketTable() {
        return ticketTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
