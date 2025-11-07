package view.employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import models.Tickets;

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
        titleLabel = new JLabel("Ticket History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(10, 10, 300, 30);
        add(titleLabel);
    }

    private void initTable() {
        String[] columnNames = {"Ticket ID", "Category ID", "Department ID", "Employee ID", 
                                "Technician ID", "Creation Date", "Resolve Date", "Status"};

        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);
        scrollPane = new JScrollPane(ticketTable);
        scrollPane.setBounds(10, 60, 500, 400);
        add(scrollPane);
    }
}
