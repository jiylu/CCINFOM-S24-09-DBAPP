package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Tickets;

public class TicketsDAO {
    private Connection connection;

    public TicketsDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Tickets> getAllTickets() throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();
        String query = "SELECT * FROM Tickets";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Tickets ticket = new Tickets(
                    rs.getInt("ticket_id"),
                    rs.getInt("category_id"),
                    rs.getInt("department_id"),
                    rs.getInt("employee_id"),
                    rs.getInt("technician_id"),
                    rs.getString("creation_date"),
                    rs.getString("resolve_date"),
                    rs.getString("status")
                );
                ticketsList.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all tickets.");
            return null;
        }
        
        return ticketsList;
    }

    public List<Tickets> getTicketsByTechnician(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, category_id, department_id, employee_id, technician_id, creation_date, resolve_date, status " +
                "FROM Tickets WHERE technician_id = ? AND status = 'Active'";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tickets ticket = new Tickets(
                            rs.getInt("ticket_id"),
                            rs.getInt("category_id"),
                            rs.getInt("department_id"),
                            rs.getInt("employee_id"),
                            rs.getInt("technician_id"),
                            rs.getString("creation_date"),
                            rs.getString("resolve_date"),
                            rs.getString("status")
                    );
                    ticketsList.add(ticket);
                }
            }
        }

        return ticketsList;
    }
}
