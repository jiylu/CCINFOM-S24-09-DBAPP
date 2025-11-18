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
        String[] columnNames = {"Ticket ID", "Category ID", "Ticket Subject", "Ticket Description", "Technician ID", "Creation Date", "Status"};

        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);
        ticketTable.setRowHeight(30);

        scrollPane = new JScrollPane(ticketTable);
        scrollPane.setBounds(30, 115, 750, 400);
        add(scrollPane);

        int[] columnWidths = {60, 60, 150, 250, 65, 87, 80};
        for (int i = 0; i < columnWidths.length; i++) {
            ticketTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
            ticketTable.getColumnModel().getColumn(i).setMinWidth(columnWidths[i]);
            ticketTable.getColumnModel().getColumn(i).setMaxWidth(columnWidths[i]);
        }
        }

    public void loadTickets(java.util.List<models.Tickets> tickets) {
        
        tableModel.setRowCount(0); 

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

}
