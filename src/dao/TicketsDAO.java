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
                    rs.getString("resolve_date")
                );
                ticketsList.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all tickets.");
            return null;
        }
        
        return ticketsList;
    }
}
