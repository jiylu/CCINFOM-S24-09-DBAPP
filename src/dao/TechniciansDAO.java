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

    public List<String> getAllTechnicianNames() {
        List<String> technicianNames = new ArrayList<>();
        
        String query = "SELECT CONCAT(tech_firstName, ' ', tech_lastName) AS full_name FROM technicians";

        try (Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                technicianNames.add(rs.getString("full_name"));
            }
            return technicianNames;
        } catch (SQLException e) {
            System.out.println("Error retrieving all technician names.");
            e.printStackTrace();
            return null;
        }
    }

    

    public void insertTechnician(Technicians tech){
        String query = "INSERT INTO Technicians(user_id, tech_lastName, tech_firstName) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, tech.getUser_ID());
            pstmt.setString(2, tech.getTech_lastName());
            pstmt.setString(3, tech.getTech_firstName());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTechnician(Technicians tech){
        String query = "UPDATE technicians SET tech_lastName = ?, tech_firstName = ? WHERE technician_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, tech.getTech_lastName());
            ps.setString(2, tech.getTech_firstName());
            ps.setInt(3, tech.getTechnician_id());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User " + tech.getUser_ID() + " updated successfully.");
            } else {
                System.out.println("No user found with ID " + tech.getUser_ID());
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTechnicianIdByUserId(int userId) {
        String sql = "SELECT technician_id FROM Technicians WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("technician_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getTechnicianIDByName(String name) {
        String sql = "SELECT technician_id FROM technicians" +
        " WHERE CONCAT(tech_firstName, ' ', tech_lastName) = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("technician_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return -1;
    }
}
