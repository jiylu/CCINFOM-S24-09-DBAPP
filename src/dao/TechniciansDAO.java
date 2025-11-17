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
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getBoolean(4)
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

    public Technicians getTechnicianByUserID(int userID) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT t.* FROM Technicians t ");
        query.append("JOIN TechnicianUsers tu ON tu.technician_id = t.technician_id ");
        query.append("WHERE tu.user_id = ? ");

        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Technicians(
                    rs.getInt("technician_id"),
                    rs.getString("tech_lastName"),
                    rs.getString("tech_firstName"),
                    rs.getBoolean("active")
                );
            }

            System.out.println("No technician found with userID " + userID);
            return null;
        } catch (SQLException e){
            System.out.println("getTechnicianByUserID error.");
            return null;
        }
    }
    

    public void insertTechnician(Technicians tech){
        String query = "INSERT INTO Technicians(tech_lastName, tech_firstName) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, tech.getTech_lastName());
            pstmt.setString(2, tech.getTech_firstName());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()){
                tech.setTechID(rs.getInt(1));
            }

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
                System.out.println("User " + tech.getTechnician_id() + " updated successfully.");
            } else {
                System.out.println("No user found with ID " + tech.getTechnician_id());
            }

            ps.close();
        } catch (Exception e) {
            System.out.println("updateTechnicianError.");
        }
    }

    public int getTechnicianIdByUserId(int userId) {    
        String sql = "SELECT technician_id FROM TechnicianUsers WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("technician_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Not found
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

    public void deactivateTechnician(int techID) {
        String query = "UPDATE technicians SET active = FALSE WHERE technician_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, techID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Technician " + techID + " deactivated successfully.");
            } else {
                System.out.println("No technician found with ID " + techID);
            }
        } catch (SQLException e) {
            System.out.println("Error deactivating technician.");
            e.printStackTrace();
        }
    }
}
