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
                    rs.getString("ticket_subject"), 
                    rs.getInt("category_id"),
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

    public List<Tickets> getTicketsByTechninicianID(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                       "FROM Tickets WHERE technician_id = ?";
                       
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tickets ticket = new Tickets(
                            rs.getInt("ticket_id"),
                            rs.getString("ticket_subject"), 
                            rs.getInt("category_id"),
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

    public List<Tickets> getTicketsByTechnician(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                "FROM Tickets WHERE technician_id = ? AND status = 'Active'";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tickets ticket = new Tickets(
                            rs.getInt("ticket_id"),
                            rs.getString("ticket_subject"), 
                            rs.getInt("category_id"),
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

    public boolean insertTicket(Tickets ticket) {
        String sql = "INSERT INTO Tickets (ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ticket.getTicket_subject());
            stmt.setInt(2, ticket.getCategory_id());
            stmt.setInt(3, ticket.getEmployee_id());
            stmt.setInt(4, ticket.getTechnician_id());
            stmt.setString(5, ticket.getCreation_date());
            stmt.setString(6, ticket.getResolve_date());
            stmt.setString(7, ticket.getStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


}


    public boolean updateTicket(Tickets ticket) {
        String sql = "UPDATE tickets SET category_id = ?, status = ?, resolve_date = ? WHERE ticket_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ticket.getCategory_id());
            stmt.setString(2, ticket.getStatus());
            stmt.setString(3, ticket.getResolve_date());
            stmt.setInt(4, ticket.getTicket_id());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
