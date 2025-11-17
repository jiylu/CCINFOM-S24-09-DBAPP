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

    // INSERTS

    public void insertToTicketsTable(Tickets ticket){
        String query = "INSERT INTO Tickets (ticket_subject, ticket_description, category_id, creation_date, emp_id, tech_id, status) VALUES = (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, ticket.getTicket_subject());
            ps.setString(2, ticket.getTicket_description());
            ps.setInt(3, ticket.getCategory_id());
            ps.setString(4, ticket.getCreation_date());
            ps.setInt(5, ticket.getEmployee_id());
            ps.setInt(6, ticket.getTechnician_id());
            ps.setString(7, ticket.getStatus());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("insertToTicketsTable error");
            e.printStackTrace();
        }
    }

    public void insertToCancelledTickets(int ticketID, String cancelDate, String cancelReason){
        String query = "INSERT INTO CancelledTickets (ticket_id, cancel_date, cancel_reason) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, ticketID);
            ps.setString(2, cancelDate);
            ps.setString(3, cancelReason);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("insertToCancelledTickets error");
            e.printStackTrace();
        }
    }

    public void insertToResolvedTickets(int ticketID, String resolveDate){
        String query = "INSERT INTO ResolvedTickets (ticket_id, resolve_date) VALUES (?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, ticketID);
            ps.setString(2, resolveDate);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("insertToResolvedTickets error");
            e.printStackTrace();
        }
    }


    public List<Tickets> getAllTickets() {
        List<Tickets> ticketsList = new ArrayList<>();
        String query = "SELECT * FROM Tickets";

        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Tickets ticket = new Tickets(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDate(5).toString(),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getString(8)
                );

                ticketsList.add(ticket);
            }

            return ticketsList;
        } catch (SQLException e) {
            System.out.println("Error retrieving all tickets.");
            return null;
        }
    }

    public List<Tickets> getTicketsByCategoryID(int categoryID) throws SQLException {
        List<Tickets> list = new ArrayList<>();
        String query = "SELECT * FROM tickets t WHERE t.category_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, categoryID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Tickets(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing getTicketsByCategoryID(): " + e.getMessage());
            throw e;
        }

        return list;
    }

    public List<Tickets> getResolvedTickets(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, ticket_description, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                       "FROM Tickets WHERE technician_id = ? AND status IN ('Resolved', 'Cancelled')";
                       
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tickets ticket = new Tickets(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDate(5).toString(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(8)
                    );
                    ticketsList.add(ticket);
                }
            }
        }
        return ticketsList;
    }

    public List<Tickets> getTicketsByTechninicianID(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, ticket_description, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                       "FROM Tickets WHERE technician_id = ? AND status NOT IN ('Resolved', 'Cancelled')";
                       
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tickets ticket = new Tickets(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDate(5).toString(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(8)
                    );
                    ticketsList.add(ticket);
                }
            }
        }
        return ticketsList;
    }

    public List<Tickets> getTicketsByTechnician(int technicianId) throws SQLException {
        List<Tickets> ticketsList = new ArrayList<>();

        String query = "SELECT ticket_id, ticket_subject, ticket_description, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                "FROM Tickets WHERE technician_id = ? AND status = 'Active'";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tickets ticket = new Tickets(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDate(5).toString(),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(8)
                    );
                    ticketsList.add(ticket);
                }
            }
        }

        return ticketsList;
    }

    public Tickets getActiveTicketByTechnicianID(int technicianId) throws SQLException {
        String query = "SELECT * FROM Tickets WHERE technician_id = ? AND status = 'Active' LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, technicianId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Tickets(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8) 
                );
            }    
        }
        return null;
    }

    // public boolean insertTicket(Tickets ticket) {
        
    //     String sql = "INSERT INTO Tickets (ticket_subject, ticket_description, category_id, employee_id, technician_id, creation_date, resolve_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    //         stmt.setString(1, ticket.getTicket_subject());
    //         stmt.setString(2, ticket.getTicket_description()); 
    //         stmt.setInt(3, ticket.getCategory_id());
    //         stmt.setInt(4, ticket.getEmployee_id());
    //         stmt.setInt(5, ticket.getTechnician_id());
    //         stmt.setString(6, ticket.getCreation_date());
    //         stmt.setString(7, ticket.getResolve_date());
    //         stmt.setString(8, ticket.getStatus());

    //         return stmt.executeUpdate() > 0;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

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
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
                    );
                    empTicketList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empTicketList;
    }




    // public boolean updateTicket(Tickets ticket) {

    //     String sql = "UPDATE tickets SET category_id = ?, status = ?, resolve_date = ? WHERE ticket_id = ?";

    //     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    //         stmt.setInt(1, ticket.getCategory_id());
    //         stmt.setString(2, ticket.getStatus());
    //         stmt.setString(3, ticket.getResolve_date());
    //         stmt.setInt(4, ticket.getTicket_id());

    //         return stmt.executeUpdate() > 0;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

    public List<Tickets> getEnqueuedTicketsByTechnician(int technicianID) throws SQLException {
        List<Tickets> tickets = new ArrayList<>();

        String sql = "SELECT ticket_id, ticket_subject, ticket_description, category_id, employee_id, technician_id, creation_date, resolve_date, status " +
                "FROM Tickets WHERE technician_id = ? AND status = 'Enqueued' ORDER BY creation_date ASC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, technicianID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tickets t = new Tickets(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5).toString(),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
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

    public boolean hasActiveOrEnqueuedTickets(int technicianId) {
        String query = "SELECT COUNT(*) FROM tickets WHERE technician_id = ? AND status IN ('Active', 'Enqueued')";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, technicianId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
