package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.TicketLogs;

public class TicketLogsDAO {
    private Connection connection;

    public TicketLogsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TicketLogs> getAllTicketLogs() throws SQLException {
        List<TicketLogs> ticketLogsList = new ArrayList<>();
        String query = "SELECT * FROM TicketLogs";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
                
            while (rs.next()) {
                TicketLogs ticketLog = new TicketLogs(
                    rs.getInt("log_id"),
                    rs.getInt("ticket_id"),
                    rs.getInt("technician_id"),
                    rs.getString("log_date"),
                    rs.getString("log_activity")
                );
                ticketLogsList.add(ticketLog);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all ticket logs.");
            return null;
        }
        
        return ticketLogsList;
    }
}
