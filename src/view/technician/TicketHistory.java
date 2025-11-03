package view.technician;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TicketHistory extends JFrame {
    private JTable ticketTable;
    private DefaultTableModel tableModel;

    public TicketHistory() {
        setTitle("Ticket History");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeTable();
        setVisible(true);
    }

    private void initializeTable() {
        String[] columnNames = {"TicketID", "EmployeeID", "CategoryID", "TechnicianID", "LogNotes", "LogDate", "Status", "ResolveDate"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane);

        // Sample data for demonstration
        addTicketData("testID1", "sampleEMP1", "1", "Bonifacio", "Customer reported double charge on fare", "2025-10-20", "Resolved", "2025-10-21");
        addTicketData("testID2", "sampleEMP2", "3", "Gregorio", "TVM not dispensing tickets properly", "2025-10-21", "Resolved", "2025-10-22");
        addTicketData("testID3", "sampleEMP3", "2", "Rizal", "Login issue with employee terminal", "2025-10-22", "Resolved", "2025-10-23");
    }

    private void addTicketData(String ticket, String employee, String category, String technician, String notes, String logDate, String status, String resolveDate) {
        tableModel.addRow(new Object[]{ticket, employee, category, technician, notes, logDate, status, resolveDate});
    }
}
