package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Technicians;

public class TechniciansDAO {
    private Connection conn;

    public TechniciansDAO(Connection conn){
        this.conn = conn;
    }

    public List<Technicians> getAllTechnicians() {
        List<Technicians> list = new ArrayList<>();
        String query = "SELECT * FROM technicians";

        try (Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                list.add(new Technicians(
                    rs.getInt("technician_id"),
                    rs.getInt("user_id"),
                    rs.getString("tech_lastName"),
                    rs.getString("tech_firstName")
                ));
            }

            rs.close();
            statement.close();

            return list;            
        } catch (SQLException e) {
            System.out.println("Error retrieving all technicians");
            return null;
        }
    }

    public void insertTechnician(Technicians tech){
        String query = "INSERT INTO Technicians(user_id, tech_lastName, tech_firstName, has_active_ticket) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Bind the values to the prepared statement
            pstmt.setInt(1, tech.getUser_ID());          // Set user_id
            pstmt.setString(2, tech.getTech_lastName()); // Set tech_lastName
            pstmt.setString(3, tech.getTech_firstName()); // Set tech_firstName

            // Execute the insertion query
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();  // Handle exceptions (logging or rethrow as needed)
        }
    }
}
