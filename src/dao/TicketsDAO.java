package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;
import db.DBConnection;
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

        public List<Tickets> getResolvedTickets(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                       "FROM Tickets WHERE technician_id = ? AND status IN ('Resolved', 'Cancelled')";
                       
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

    public List<Tickets> getTicketsByTechninicianID(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                       "FROM Tickets WHERE technician_id = ? AND status NOT IN ('Resolved', 'Cancelled')";
                       
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

    public int getAvailableTechnicianId() throws SQLException {
        String sql = "SELECT technician_id FROM Technicians WHERE technician_id NOT IN (SELECT technician_id FROM Tickets WHERE status = 'Active') LIMIT 1";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("technician_id");
            }
        }
        return -1; // No available technician
    }

    public List<Tickets> getTicketsByEmployeeId(int employeeId) {
        List<Tickets> empTicketList = new ArrayList<>();
        String query = "SELECT * FROM Tickets WHERE employee_id = ? ORDER BY creation_date ASC";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

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
                    empTicketList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empTicketList;
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

    public List<Tickets> getEnqueuedTicketsByTechnician(int technicianID) throws SQLException {
        List<Tickets> tickets = new ArrayList<>();

        String sql = "SELECT ticket_id, ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                "FROM Tickets WHERE technician_id = ? AND status = 'Enqueued' ORDER BY creation_date ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, technicianID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tickets t = new Tickets(
                            rs.getInt("ticket_id"),
                            rs.getString("ticket_subject"),
                            rs.getInt("category_id"),
                            rs.getInt("employee_id"),
                            rs.getInt("technician_id"),
                            rs.getString("creation_date"),
                            rs.getString("resolve_date"),
                            rs.getString("status")
                    );
                    tickets.add(t);
                }
            }
        }

        return tickets;
    }

    public List<Integer> getTicketYears(){
        List<Integer> years = new ArrayList<>();

        String query = "SELECT DISTINCT YEAR(creation_date) AS year FROM tickets ORDER BY year ASC";

        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                years.add(rs.getInt(1));
            }

            stmt.close();
            rs.close();
            
            return years;
        } catch (SQLException e){
            System.out.println("Cannot retrieve ticket years.");
            return null;
        }
    }
}
